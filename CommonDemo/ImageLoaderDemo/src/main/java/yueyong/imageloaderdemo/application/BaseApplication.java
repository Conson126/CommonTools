package yueyong.imageloaderdemo.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

public class BaseApplication extends Application {
    //获取到主线程的上下文
	private static BaseApplication mContext;
	//获取到主线程的handler;
	private static Handler mMainThreadHandler;
	//获取到主线程的looper
	private static Looper mMainThreadLooper;
	//获取到主线程
	private static Thread mMainThread;
	//获取到主线程的id
	private static int mMainThreadId;
	@Override
	public void onCreate() {
		
		super.onCreate();
		this.mContext = this;
		this.mMainThreadHandler = new Handler();
		this.mMainThreadLooper = getMainLooper();
		this.mMainThread = Thread.currentThread();
		this.mMainThreadId = android.os.Process.myTid();
		
	}
	
	
	public static BaseApplication getApplication(){
		return mContext;
	}
	
	public static Handler getMainThreadHandler(){
		return mMainThreadHandler;
	}
	
	
	public static Looper getMainThreadLooper(){
		return mMainThreadLooper;
	}
	
	public static Thread getMainThread(){
		return mMainThread;
	}
	
	public static int getMainThreadId(){
		return mMainThreadId;
	}
	
	
	
	
	
	
	
	
	
	
}
