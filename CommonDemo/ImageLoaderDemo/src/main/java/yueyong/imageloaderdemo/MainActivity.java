package yueyong.imageloaderdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import yueyong.imageloaderdemo.view.ImageLoader;


public class MainActivity extends ActionBarActivity {


    private ListView mListView;
    private List<String> picLists = new ArrayList<String>();
    private static final String PRE = "http://pic.bugua.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.list_view);
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");
        picLists.add("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE&pn=3&spn=0&di=95393988690&pi=&rn=1&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=577333074%2C577007802&os=3064089731%2C3932183095&adpicid=0&ln=21&fr=ala&sme=0&cg=&objurl=http%3A%2F%2Fpica.nipic.com%2F2007-11-16%2F20071116174041795_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FcAzdH3F0AzdH3Fjkbn80a0cl8vc1w8_z%26e3Bip4s");


        mListView.setAdapter(mAdapter);
       /* new HttpUtils().send(HttpRequest.HttpMethod.GET,"http://mobile.test.bugua.com/page/",new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                String result = objectResponseInfo.result;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.getJSONArray("p_list");
                    for(int i=0;i<array.length();i++) {
                        picLists.add(array.getJSONObject(i).getString("avatar"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return picLists.size();
        }

        @Override
        public String getItem(int position) {
            return picLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = new ImageView(MainActivity.this);
            }
            ImageLoader.load((ImageView)convertView,getItem(position));
            return convertView;
        }
    };
}
