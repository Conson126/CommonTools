package yueyong.fragmenttest.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import yueyong.fragmenttest.R;
import yueyong.fragmenttest.fragment.FragmentOne;
import yueyong.fragmenttest.fragment.FragmentTwo;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public static final String FRAGMENT_ONE_TAG = "fragment_one";
    public static final String FRAGMENT_TWO_TAG = "fragment_two";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.replace).setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.single_fragment, FragmentOne.newInstance("abc","def"),FRAGMENT_ONE_TAG).commit();
    }

    @Override
    public void onClick(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.single_fragment, FragmentTwo.newInstance("1", "2"),FRAGMENT_TWO_TAG).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
