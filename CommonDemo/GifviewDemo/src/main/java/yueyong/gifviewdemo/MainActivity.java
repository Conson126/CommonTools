package yueyong.gifviewdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;

    private BitmapUtils bitmapUtils;

    private List<String> lists = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.list_view);

        bitmapUtils = new BitmapUtils(MainActivity.this);

        String pre = "http://pic.bugua.com";

        lists.add(pre + "/6829c71bb4e2688323d07dfbb30f7ad7.gif");
        lists.add(pre + "/6829c71bb4e2688323d07dfbb30f7ad7.gif");
        lists.add(pre + "/5ced7816c0068de66ffd034520fdbed8.gif");
        lists.add(pre + "/fe497069d01a19a0fdff3fd9505c82fd.gif");
        lists.add(pre + "/007e5cb0480985a005cf39fcdef16b77.gif");
        lists.add(pre + "/5a9c472bd7105f92da8663a50d23ebcc.gif");
        lists.add(pre + "/2b620afbf2291e75f4e083c1f0e431ce.gif");
        lists.add(pre + "/2de48fe32efb84d9ca7c653bf4af07b0.gif");
        lists.add(pre + "/f4a4659503213009e8008582bf4ec1e0.gif");
        lists.add(pre + "/0f0c65131ea716c6ea33532e3c26e9a0.gif");
        lists.add(pre + "/21519c3b870c87b20f51184330db53e2.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");
        lists.add(pre + "/36ef94482b52aa18cb09b02a40e37ffc.gif");

        mListView.setAdapter(new MyAdapter());
    }





    class MyAdapter extends BaseAdapter {

        private HttpUtils httpUtils;

        public MyAdapter() {
            super();
            httpUtils = new HttpUtils();
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final GifImageView gifView;
            if(convertView == null) {
                convertView = View.inflate(MainActivity.this,R.layout.layout_gif,null);
                gifView = (GifImageView)convertView.findViewById(R.id.gif_view);
                convertView.setTag(gifView);
            } else {
                gifView = (GifImageView)convertView.getTag();
            }
            //开始显示动图
            gifView.setImageResource(R.drawable.a);
            return convertView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
