package yueyong.eventbustest.event;

import android.os.SystemClock;

/**
 * Created by YueYong on 2015/3/19.
 */
public class SecondEvent {

    public String getData() {
        SystemClock.sleep(3000);
        return "second long time ";
    }
}
