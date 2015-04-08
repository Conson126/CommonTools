package yueyong.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import yueyong.myapplication.util.MD5Util;

public class SQLDemoActivity extends Activity {

    private SharedPreferences sp;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String apkRoot="chmod 777 "+getPackageCodePath();
        boolean root = SystemManager.RootCommand(apkRoot);
        Log.i("root-->",root + "");

        //you must set Context on SQLiteDatabase first
        new Thread() {
            @Override
            public void run() {
                //修改文件的权限
                boolean shell = SystemManager.RootCommand("chmod 777 /data/data/com.tencent.mm");
                Log.i("shell:",shell + "");
                boolean b = SystemManager.RootCommand("chmod 777 /data/data/com.tencent.mm/MicroMsg");
                Log.i("b:",b + "");
                boolean c = SystemManager.RootCommand("chmod 777 /data/data/com.tencent.mm/MicroMsg/systemInfo.cfg");
                Log.i("c:",c + "");
                boolean d = SystemManager.RootCommand("chmod 777 /data/data/com.tencent.mm/MicroMsg/CompatibleInfo.cfg");
                Log.i("d:",d + "");
                boolean e = SystemManager.RootCommand("chmod 777 /data/data/com.tencent.mm/MicroMsg/e54ec7609d105546fa788f172a91aafb");
                Log.i("e:",e + "");
                boolean f = SystemManager.RootCommand("chmod 777 /data/data/com.tencent.mm/MicroMsg/e54ec7609d105546fa788f172a91aafb/EnMicroMsg.db");
                Log.i("f:",f + "");


//                String password = MD5Util.getWeiXinPassword(SQLDemoActivity.this);
//                Log.i("the password:",password);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        unLockWeiXinDatabase();
                    }
                });
            }
        }.start();
    }

    private void unLockWeiXinDatabase() {
        sp = getSharedPreferences("WEI_XIN_KEY", MODE_PRIVATE);
        String password = sp.getString("password","");
        if(TextUtils.isEmpty(password)) {
            password = MD5Util.getWeiXinPassword(SQLDemoActivity.this);
            sp.edit().putString("password", password).apply();
        }

        if(!TextUtils.isEmpty(password)) {
            accessWeiXinDatabase(password);
        }

        Log.i("--->",password);
//        String password = "b4eecf1";
    }

    private void accessWeiXinDatabase(String password) {
        //        File databaseFile = getDatabasePath("/data/data/com.tencent.mm/MicroMsg/e54ec7609d105546fa788f172a91aafb/EnMicroMsg.db");
//        String dir = Environment.getExternalStorageDirectory().toString();
//        Log.i("sd-->",dir);
//        File databaseFile = getDatabasePath(dir + "/EnMicroMsg.db");

        SQLiteDatabase.loadLibs(SQLDemoActivity.this);
        SQLiteDatabaseHook hook = new SQLiteDatabaseHook(){
            public void preKey(SQLiteDatabase database){

            }
            public void postKey(SQLiteDatabase database){
                database.rawExecSQL("PRAGMA cipher_migrate;");
            }
        };

        try {
//            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(databaseFile, password,null , hook);
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.tencent.mm/MicroMsg/e54ec7609d105546fa788f172a91aafb/EnMicroMsg.db",password.toCharArray(),null,SQLiteDatabase.NO_LOCALIZED_COLLATORS,hook);
//            int svrid = 12345;
//            String md5 = "c0059fa4f781a2a500ec03fade10e9b1";
//            db.execSQL("update EmojiInfo set svrid=" + svrid + " where md5='" + md5 + "'");

            db.delete("EmojiInfo","md5=?",new String[]{"a357eb701e35ab835059573625508467"});


            ContentValues contentValues = new ContentValues();
            contentValues.put("md5","a357eb701e35ab835059573625508467");
            contentValues.put("catalog",81);
            contentValues.put("type",1);
            contentValues.put("size",28276);
            contentValues.put("start",0);
            contentValues.put("state",3);
            contentValues.put("reserved3",0);
            contentValues.put("reserved4",0);
            contentValues.put("lastUseTime",0);
            contentValues.put("idx",0);
            contentValues.put("temp",0);
            contentValues.put("source",0);
            contentValues.put("needupload",0);

            db.insert("EmojiInfo",null,contentValues);

            Cursor c = db.query("EmojiInfo", null, null, null, null, null, null);
            while (c.moveToNext()) {
                String md5 = c.getString(c.getColumnIndex("md5"));
                int catalog = c.getInt(c.getColumnIndex("catalog"));
                int type = c.getInt(c.getColumnIndex("type"));
                int svrId = c.getInt(c.getColumnIndex("svrid"));
                Log.i("db", "md5=>" + md5 + "catalog=>" + catalog + ", type=>" + type + ",svrid=>" + svrId);
            }

//            db.delete("EmojiInfo","md5=?",new String[]{"a357eb701e35ab835059573625508461"});
            Toast.makeText(SQLDemoActivity.this, "success", Toast.LENGTH_SHORT).show();
            c.close();
            db.close();
        } catch (Exception e) {

        }
    }

    public void encrypByMd5Jar(String context) {
        String md5Str = DigestUtils.md5Hex(context);
        System.out.println("32result: " + md5Str);
    }

    public void encrypByMd5(String context) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes());//update处理
            byte [] encryContext = md.digest();//调用该方法完成计算

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < encryContext.length; offset++) {//做相应的转化（十六进制）
                i = encryContext[offset];
                if (i < 0) i += 256;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            System.out.println("32result: " + buf.toString());// 32位的加密
            System.out.println("16result: " + buf.toString().substring(8, 24));// 16位的加密
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block   a51662fefd6640b0a9f4811e98274d36   a51662fefd6640b0a9f4811e98274d36
            e.printStackTrace();
        }
    }
}