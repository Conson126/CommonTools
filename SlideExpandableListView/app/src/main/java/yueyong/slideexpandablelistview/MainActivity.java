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

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        toast = Toast.makeText(this,"123",Toast.LENGTH_SHORT);
        ActionSlideExpandableListView list = (ActionSlideExpandableListView) this.findViewById(R.id.list);

        //这个数据最好抽到string.xml中去，不要硬编码
        List<String> list1 = new ArrayList<String>();
        list1.add("个人资料设置");
        list1.add("修改头像");
        list1.add("修改密码");
        list1.add("申请密码保护");
        list1.add("修改密码保护资料");

        List<String> list2 = new ArrayList<String>();
        list2.add("我的账户");
        list2.add("我的保险箱");
        list2.add("账户明细");
        list2.add("在线充值");
        list2.add("充值记录");
        list2.add("消费记录");

        List<String> list3 = new ArrayList<String>();
        list3.add("我的族谱");
        list3.add("我创建的用户");
        list3.add("我创建的纪念馆");
        list3.add("我加入的亲友馆");

        List<String> list4 = new ArrayList<String>();
        list4.add("录入姓氏");
        list4.add("录入任务");
        list4.add("创建休闲吧");
        list4.add("创建族谱");
        list4.add("创建纪念馆");

        List<String> list5 = new ArrayList<String>();
        list5.add("我的相册");
        list5.add("上传图片");

        mData.put("个人资料",list1);
        mData.put("个人账户",list2);
        mData.put("创建列表",list3);
        mData.put("操作菜单",list4);
        mData.put("个人相册",list5);


        for(String key : mData.keySet()) {
            keys.add(key);
        }

        list.setAdapter(buildDummyData());

        list.setItemActionListener(new ActionSlideExpandableListView.OnActionClickListener() {

            @Override
            public void onClick(View listView, View buttonview, int position) {

                String actionName = (String)buttonview.getTag();

                //你这数据本来就是死，没必要想复杂的算法来做，就这样比较简单易懂

                //注意硬编码，自己改一下
                switch (buttonview.getId()) {

                    case R.id.text1:     //TODO：看这个注释：text1对应的是每个一级标题对应的二级标题的第一个，写错字了可能会出错，不要写错字就行了
                        if(actionName.equals("我的族谱")) {
                            //在这里跳转到我的族谱页面，下面是一样的
                           showText(actionName);
                        } else if(actionName.equals("我的相册")) {
                            //在这里跳转到我的我的相册页面，下面是一样的，就不注释了
                            showText(actionName);
                        } else if(actionName.equals("个人资料设置")) {
                            showText(actionName);
                        } else if(actionName.equals("录入姓氏")) {
                            showText(actionName);
                        } else if(actionName.equals("我的账户")) {
                            showText(actionName);
                        }
                        break;

                    case R.id.text2:  //TODO：看这个注释：text2对应的是每个一级标题对应的二级标题的第二个

                        if(actionName.equals("我创建的用户")) {
                            //在这里跳转到我的族谱页面，下面是一样的
                            showText(actionName);
                        } else if(actionName.equals("上传图片")) {
                            //在这里跳转到我的我的相册页面，下面是一样的，就不注释了
                            showText(actionName);
                        } else if(actionName.equals("修改头像")) {
                            showText(actionName);
                        } else if(actionName.equals("录入任务")) {
                            showText(actionName);
                        } else if(actionName.equals("我的保险箱")) {
                            showText(actionName);
                        }
                        break;

                    case R.id.text3:   //TODO：看这个注释：text3对应的是每个一级标题对应的二级标题的第三个，没有就不写
                        if(actionName.equals("我创建的纪念馆")) {
                            //在这里跳转到我的族谱页面，下面是一样的
                            showText(actionName);
                        } else if(actionName.equals("修改密码")) {
                            //在这里跳转到我的我的相册页面，下面是一样的，就不注释了
                            showText(actionName);
                        } else if(actionName.equals("创建休闲吧")) {
                            showText(actionName);
                        } else if(actionName.equals("账户明细")) {
                            showText(actionName);
                        }
                        break;

                    case R.id.text4:
                        if(actionName.equals("我加入的亲友馆")) {
                            //在这里跳转到我的族谱页面，下面是一样的
                            showText(actionName);
                        } else if(actionName.equals("申请密码保护")) {
                            //在这里跳转到我的我的相册页面，下面是一样的，就不注释了
                            showText(actionName);
                        } else if(actionName.equals("创建族谱")) {
                            showText(actionName);
                        } else if(actionName.equals("在线充值")) {
                            showText(actionName);
                        }
                        break;

                    case R.id.text5:
                        if("修改密码保护资料".equals(actionName)) {
                            showText(actionName);
                        } else if("创建纪念馆".equals(actionName)) {
                            showText(actionName);
                        } else if("充值记录".equals(actionName)) {
                            showText(actionName);
                        }
                        break;

                    case R.id.text6:
                        if("消费记录".equals(actionName)) {
                            showText(actionName);
                        }
                        break;

                }

            }

        }, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6);

    }

    private void showText(String text) {
        toast.setText(text);
        toast.show();
    }

    public ListAdapter buildDummyData() {
        return new MyAdapter();
    }

    //这个itemIds的总数是根据二级菜单最多的那个为准，这里最多的是list2，个人账户，有6个，所以总数为6个
    int[] itemIds = new int[]{R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6};
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
