#include "yueyong_jniexample_MainActivity.h"

JNIEXPORT jstring JNICALL Java_yueyong_jniexample_MainActivity_getStringFromJni
  (JNIEnv * env, jobject obj) {

         char* text = "Hello jni,good a ha";
         return (**env).NewStringUTF(env, text);
  }