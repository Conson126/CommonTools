package yueyong.jniexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    static {
        System.loadLibrary("JniTest");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.txt)).setText(getStringFromJni());
    }


    public native String getStringFromJni();

}

//javah -d jni -classpath D:\IDE\sdk\platforms\android-21\android.jar;F:/Repository/CommonTools/CommonDemo/diyToast/build/intermediates/exploded-aar/com.android.support/support-v4/21.0.3/classes.jar;F:/Repository/CommonTools/CommonDemo/diyToast/build/intermediates/exploded-aar/com.android.support/appcompat-v7/21.0.3/classes.jar;..\..\build\intermediates\classes\debug yueyong.jniexample.MainActivity


//F:/Repository/CommonTools/CommonDemo/diyToast/build/intermediates/exploded-aar/com.android.support/appcompat-v7/21.0.3/classes.jar

//F:/Repository/CommonTools/CommonDemo/diyToast/build/intermediates/exploded-aar/com.android.support/support-v4/21.0.3/classes.jar