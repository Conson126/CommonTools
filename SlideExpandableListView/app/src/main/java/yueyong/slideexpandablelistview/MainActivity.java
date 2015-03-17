package yueyong.slideexpandablelistview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tjerkw.slideexpandable.library.ActionSlideExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {


    private Map<String,List<String>> mData = new HashMap<String,List<String>>();

    List<String> keys = new ArrayList<>(); //存储键的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        ActionSlideExpandableListView list = (ActionSlideExpandableListView) this.findViewById(R.id.list);

        //假数据
        List<String> list1 = new ArrayList<String>();
        list1.add("One--item1");
        list1.add("One--item2");
        list1.add("One--item3");
        list1.add("One--item4");

        List<String> list2 = new ArrayList<String>();
        list2.add("Two--item1");
        list2.add("Two--item2");
        list2.add("Two--item3");

        List<String> list3 = new ArrayList<String>();
        list3.add("Three--item1");
        list3.add("Three--item2");
        list3.add("Three--item3");
        list3.add("Three--item4");
        list3.add("Three--item5");
        list3.add("Three--item6");
        list3.add("Three--item7");

        mData.put("One",list1);
        mData.put("Two",list2);
        mData.put("Three",list3);


        for(String key : mData.keySet()) {
            keys.add(key);
        }

        list.setAdapter(buildDummyData());

        list.setItemActionListener(new ActionSlideExpandableListView.OnActionClickListener() {

            @Override
            public void onClick(View listView, View buttonview, int position) {

                String actionName = (String)buttonview.getTag();
                //这里面带过了了条目的名字，可以根据名字跳转到相应的页面

                switch (buttonview.getId()) {
                    case R.id.text1:

                        break;

                    case R.id.text2:

                        break;

                    case R.id.text3:

                        break;

                    case R.id.text4:

                        break;

                    case R.id.text5:

                        break;

                    case R.id.text6:

                        break;

                    case R.id.text7:

                        break;
                }

                Toast.makeText(
                        MainActivity.this,
                        actionName + "--->"+position,
                        Toast.LENGTH_SHORT
                ).show();
            }

        }, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7);

    }

    public ListAdapter buildDummyData() {
        return new MyAdapter();
    }

    int[] itemIds = new int[]{R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7};
    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return keys.get(position); //返回的是key
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null) {
                holder = new ViewHolder();
                holder.arrays = new TextView[itemIds.length];
                convertView = View.inflate(MainActivity.this, R.layout.expandable_list_item, null);
                holder.title = (TextView)convertView.findViewById(R.id.text);
                for(int i=0;i<itemIds.length;i++) {
                    holder.arrays[i] = (TextView)convertView.findViewById(itemIds[i]);
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            //拿到的是map集合的key
            String title = (String)getItem(position);
            holder.title.setText(title);

            List<String> items = (List<String>)mData.get(title);
            for(int i=0;i<itemIds.length;i++) {
                if(i < items.size()) {
                    holder.arrays[i].setVisibility(View.VISIBLE);
                    holder.arrays[i].setText(items.get(i));
                    holder.arrays[i].setTag(items.get(i));
                } else {
                    holder.arrays[i].setVisibility(View.GONE);
                }
            }
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView title;
            TextView[] arrays;
        }
    }
}
