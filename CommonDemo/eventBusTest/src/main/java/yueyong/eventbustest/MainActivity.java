package yueyong.eventbustest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import yueyong.eventbustest.event.FirstEvent;


public class MainActivity extends ActionBarActivity {

    private TextView textContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        textContent = (TextView)findViewById(R.id.text_content);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FirstEvent("send first text"));
            }
        });
    }

    //订阅第一个事件
    public void onEventMainThread(FirstEvent event) {
        textContent.setText(event.getSomeText());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
