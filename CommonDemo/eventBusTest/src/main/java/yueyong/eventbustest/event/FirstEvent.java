package yueyong.eventbustest.event;

/**
 * Created by YueYong on 2015/3/19.
 */
public class FirstEvent {

    private String someText;
    public FirstEvent(String someText) {
        this.someText = someText;
    }

    public String getSomeText() {
        return someText;
    }
}
