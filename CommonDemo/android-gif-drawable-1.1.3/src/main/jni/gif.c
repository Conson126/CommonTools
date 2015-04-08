#include "gif.h"

/**
* Global VM reference, initialized in JNI_OnLoad
*/
static JavaVM *g_jvm;

static inline JNIEnv *getEnv(void) {
    JNIEnv *env;
    if ((*g_jvm)->AttachCurrentThread(g_jvm, &env, NULL) == JNI_OK)
        return env;
    return NULL;
}

static int fileRead(GifFileType *gif, GifByteType *bytes, int size) {
    FILE *file = (FILE *) gif->UserData;
    return (int) fread(bytes, 1, (size_t) size, file);
}

static int directByteBufferReadFun(GifFileType *gif, GifByteType *bytes, int size) {
    DirectByteBufferContainer *dbbc = gif->UserData;
    if (dbbc->pos + size > dbbc->capacity)
        size -= dbbc->pos + size - dbbc->capacity;
    memcpy(bytes, dbbc->bytes + dbbc->pos, (size_t) size);
    dbbc->pos += size;
    return size;
}

static int byteArrayReadFun(GifFileType *gif, GifByteType *bytes, int size) {
    ByteArrayContainer *bac = gif->UserData;
    JNIEnv *env;
    (*g_jvm)->AttachCurrentThread(g_jvm, &env, NULL);
    if (bac->pos + size > bac->arrLen)
        size -= bac->pos + size - bac->arrLen;
    (*env)->GetByteArrayRegion(env, bac->buffer, (jsize) bac->pos, size, (jbyte *) bytes);
    bac->pos += size;
    return size;
}

static int streamReadFun(GifFileType *gif, GifByteType *bytes, int size) {
    StreamContainer *sc = gif->UserData;
    JNIEnv *env = getEnv();
    if (env == NULL)
        return 0;

    (*env)->MonitorEnter(env, sc->stream);

    if (sc->buffer == NULL) {
        jbyteArray buffer = (*env)->NewByteArray(env, size < 256 ? 256 : size);
        sc->buffer = (*env)->NewGlobalRef(env, buffer);
    }
    else {
        jsize bufLen = (*env)->GetArrayLength(env, sc->buffer);
        if (bufLen < size) {
            (*env)->DeleteGlobalRef(env, sc->buffer);
            sc->buffer = NULL;

            jbyteArray buffer = (*env)->NewByteArray(env, size);
            sc->buffer = (*env)->NewGlobalRef(env, buffer);
        }
    }

    int len = (*env)->CallIntMethod(env, sc->stream, sc->readMID, sc->buffer, 0, size);
    if ((*env)->ExceptionCheck(env)) {
        (*env)->ExceptionClear(env);
        len = 0;
    }
    else if (len > 0) {
        (*env)->GetByteArrayRegion(env, sc->buffer, 0, len, (jbyte *) bytes);
    }

    (*env)->MonitorExit(env, sc->stream);

    return len >= 0 ? len : 0;
}

static int fileRewind(GifInfo *info) {
    return fseek(info->gifFilePtr->UserData, info->startPos, SEEK_SET);
}

static int streamRewind(GifInfo *info) {
    GifFileType *gif = info->gifFilePtr;
    StreamContainer *sc = gif->UserData;
    JNIEnv *env = getEnv();
    if (env == NULL)
        return -1;
    (*env)->CallVoidMethod(env, sc->stream, sc->resetMID);
    if ((*env)->ExceptionOccurred(env)) {
        (*env)->ExceptionClear(env);
        return -1;
    }
    return 0;
}

static int byteArrayRewind(GifInfo *info) {
    GifFileType *gif = info->gifFilePtr;
    ByteArrayContainer *bac = gif->UserData;
    bac->pos = info->startPos;
    return 0;
}

static int directByteBufferRewindFun(GifInfo *info) {
    GifFileType *gif = info->gifFilePtr;
    DirectByteBufferContainer *dbbc = gif->UserData;
    dbbc->pos = info->startPos;
    return 0;
}

__unused JNIEXPORT jobject JNICALL
Java_pl_droidsonroids_gif_GifInfoHandle_openFile(JNIEnv *env, jclass __unused class,
        jstring jfname, jboolean justDecodeMetaData) {
    if (isSourceNull(jfname, env)) {
        return NULL;
    }

    const char *const filename = (*env)->GetStringUTFChars(env, jfname, 0);
    FILE *file = fopen(filename, "rb");
    (*env)->ReleaseStringUTFChars(env, jfname, filename);
    if (file == NULL) {
        throwGifIOException(D_GIF_ERR_OPEN_FAILED, env);
        return NULL;
    }
    GifSourceDescriptor descriptor;
    descriptor.GifFileIn = DGifOpen(file, &fileRead, &descriptor.Error);
    descriptor.rewindFunc = fileRewind;
    descriptor.startPos = ftell(file);
    struct stat st;
    descriptor.sourceLength = stat(filename, &st) == 0 ? st.st_size : -1;
    return createGifHandle(&descriptor, env, justDecodeMetaData);
}

__unused JNIEXPORT jobject JNICALL
Java_pl_droidsonroids_gif_GifInfoHandle_openByteArray(JNIEnv *env, jclass __unused class,
        jbyteArray bytes, jboolean justDecodeMetaData) {
    if (isSourceNull(bytes, env)) {
        return NULL;
    }
    ByteArrayContainer *container = malloc(sizeof(ByteArrayContainer));
    if (container == NULL) {
        throwGifIOException(D_GIF_ERR_NOT_ENOUGH_MEM, env);
        return NULL;
    }
    container->buffer = (*env)->NewGlobalRef(env, bytes);
    container->arrLen = (*env)->GetArrayLength(env, container->buffer);
    container->pos = 0;

    GifSourceDescriptor descriptor;
    descriptor.GifFileIn = DGifOpen(container, &byteArrayReadFun, &descriptor.Error);
    descriptor.rewindFunc = byteArrayRewind;
    descriptor.startPos = container->pos;
    descriptor.sourceLength = container->arrLen;

    jobject gifInfoHandle = createGifHandle(&descriptor, env, justDecodeMetaData);

    if (gifInfoHandle == NULL) {
        (*env)->DeleteGlobalRef(env, container->buffer);
        free(container);
    }
    return gifInfoHandle;
}

__unused JNIEXPORT jobject JNICALL
Java_pl_droidsonroids_gif_GifInfoHandle_openDirectByteBuffer(JNIEnv *env,
        jclass __unused class, jobject buffer, jboolean justDecodeMetaData) {
    jbyte *bytes = (*env)->GetDirectBufferAddress(env, buffer);
    jlong capacity = (*env)->GetDirectBufferCapacity(env, buffer);
    if (bytes == NULL || capacity <= 0) {
        if (!isSourceNull(buffer, env))
            throwGifIOException(D_GIF_ERR_INVALID_BYTE_BUFFER, env);
        return NULL;
    }
    DirectByteBufferContainer *container = malloc(sizeof(DirectByteBufferContainer));
    if (container == NULL) {
        throwGifIOException(D_GIF_ERR_NOT_ENOUGH_MEM, env);
        return NULL;
    }
    container->bytes = bytes;
    container->capacity = capacity;
    container->pos = 0;

    GifSourceDescriptor descriptor;
    descriptor.GifFileIn = DGifOpen(container, &directByteBufferReadFun, &descriptor.Error);
    descriptor.rewindFunc = directByteBufferRewindFun;
    descriptor.startPos = container->pos;
    descriptor.sourceLength = container->capacity;

    jobject gifInfoHandle = createGifHandle(&descriptor, env, justDecodeMetaData);

    if (gifInfoHandle == NULL) {
        free(container);
    }
    return gifInfoHandle;
}

__unused JNIEXPORT jobject JNICALL
Java_pl_droidsonroids_gif_GifInfoHandle_openStream(JNIEnv *env, jclass __unused class,
        jobject stream, jboolean justDecodeMetaData) {
    jclass streamCls = (*env)->NewGlobalRef(env,
            (*env)->GetObjectClass(env, stream));
    jmethodID mid = (*env)->GetMethodID(env, streamCls, "mark", "(I)V");
    jmethodID readMID = (*env)->GetMethodID(env, streamCls, "read", "([BII)I");
    jmethodID resetMID = (*env)->GetMethodID(env, streamCls, "reset", "()V");

    if (mid == 0 || readMID == 0 || resetMID == 0) {
        (*env)->DeleteGlobalRef(env, streamCls);
        throwGifIOException(D_GIF_ERR_OPEN_FAILED, env);
        return NULL;
    }

    StreamContainer *container = malloc(sizeof(StreamContainer));
    if (container == NULL) {
        throwGifIOException(D_GIF_ERR_NOT_ENOUGH_MEM, env);
        return NULL;
    }
    container->readMID = readMID;
    container->resetMID = resetMID;

    container->stream = (*env)->NewGlobalRef(env, stream);
    container->streamCls = streamCls;
    container->buffer = NULL;

    GifSourceDescriptor descriptor;
    descriptor.GifFileIn = DGifOpen(container, &streamReadFun, &descriptor.Error);
    descriptor.startPos = 0;
    descriptor.rewindFunc = streamRewind;
    descriptor.sourceLength = -1;

    (*env)->CallVoidMethod(env, stream, mid, LONG_MAX); //TODO better length?

    jobject gifInfoHandle = createGifHandle(&descriptor, env, justDecodeMetaData);
    if (gifInfoHandle == NULL) {
        (*env)->DeleteGlobalRef(env, streamCls);
        (*env)->DeleteGlobalRef(env, container->stream);
        free(container);
    }
    return gifInfoHandle;
}

__unused JNIEXPORT jobject JNICALL
Java_pl_droidsonroids_gif_GifInfoHandle_openFd(JNIEnv *env, jclass __unused handleClass,
        jobject jfd, jlong offset, jboolean justDecodeMetaData) {
    if (isSourceNull(jfd, env)) {
        return NULL;
    }
    jclass fdClass = (*env)->GetObjectClass(env, jfd);
    jfieldID fdClassDescriptorFieldID = (*env)->GetFieldID(env, fdClass, "descriptor", "I");
    if (fdClassDescriptorFieldID == NULL) {
        throwGifIOException(D_GIF_ERR_OPEN_FAILED, env);
        return NULL;
    }
    const int fd = dup((*env)->GetIntField(env, jfd, fdClassDescriptorFieldID));
    FILE *file = fdopen(fd, "rb");
    if (file == NULL || fseek(file, offset, SEEK_SET) != 0) {
        throwGifIOException(D_GIF_ERR_OPEN_FAILED, env);
        return NULL;
    }

    GifSourceDescriptor descriptor;
    descriptor.GifFileIn = DGifOpen(file, &fileRead, &descriptor.Error);
    descriptor.rewindFunc = fileRewind;
    descriptor.startPos = ftell(file);
    struct stat st;
    descriptor.sourceLength = fstat(fd, &st) == 0 ? st.st_size : -1;
    return createGifHandle(&descriptor, env, justDecodeMetaData);
}

__unused JNIEXPORT void JNICALL
Java_pl_droidsonroids_gif_GifInfoHandle_free(JNIEnv *env, jclass __unused handleClass,  jlong gifInfo) {
    GifInfo *info = (GifInfo *) (intptr_t) gifInfo;
    if (info == NULL)
        return;
    if (info->rewindFunction == streamRewind) {
        StreamContainer *sc = info->gifFilePtr->UserData;
        jmethodID closeMID = (*env)->GetMethodID(env, sc->streamCls, "close", "()V");
        if (closeMID != NULL)
            (*env)->CallVoidMethod(env, sc->stream, closeMID);
        if ((*env)->ExceptionCheck(env))
            (*env)->ExceptionClear(env);

        (*env)->DeleteGlobalRef(env, sc->streamCls);
        (*env)->DeleteGlobalRef(env, sc->stream);

        if (sc->buffer != NULL) {
            (*env)->DeleteGlobalRef(env, sc->buffer);
        }

        free(sc);
    }
    else if (info->rewindFunction == fileRewind) {
        fclose(info->gifFilePtr->UserData);
    }
    else if (info->rewindFunction == byteArrayRewind) {
        ByteArrayContainer *bac = info->gifFilePtr->UserData;
        if (bac->buffer != NULL) {
            (*env)->DeleteGlobalRef(env, bac->buffer);
        }
        free(bac);
    }
    else if (info->rewindFunction == directByteBufferRewindFun) {
        free(info->gifFilePtr->UserData);
    }
    info->gifFilePtr->UserData = NULL;
    cleanUp(info);
}

__unused JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM *vm, void *__unused reserved) {
    JNIEnv *env;
    if ((*vm)->GetEnv(vm, (void **) (&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    g_jvm = vm;
    defaultCmap = genDefColorMap();
    if (defaultCmap == NULL)
        return -1;
    return JNI_VERSION_1_6;
}

__unused JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *__unused vm, void *__unused reserved) {
    GifFreeMapObject(defaultCmap);
}