package yueyong.myapplication.util;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.util.HashMap;

/**
 * Created by YueYong on 2015/4/2.
 */
public class MD5Util {

    public static String getWeiXinPassword(Context context) {
        String password = "";
        try {
            String sysName = "/data/data/com.tencent.mm/MicroMsg/systemInfo.cfg";
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream("C://systemInfo.cfg"));
            FileInputStream fis = new FileInputStream(sysName);
            ObjectInputStream in = new ObjectInputStream(fis);
            Object DL = in.readObject();
            HashMap hashWithOutFormat = (HashMap) DL;
            String compName = "/data/data/com.tencent.mm/MicroMsg/CompatibleInfo.cfg";
//            ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("C://CompatibleInfo.cfg"));
            FileInputStream fis2 = new FileInputStream(compName);
            ObjectInputStream in1 = new ObjectInputStream(fis2);
            Object DJ = in1.readObject();
            HashMap hashWithOutFormat1 = (HashMap) DJ;
            String s = String.valueOf(hashWithOutFormat1.get(Integer
                    .valueOf(258))); // 取手机的IMEI
            s = s + hashWithOutFormat.get(Integer.valueOf(1)); //合并到一个字符串
            s = encode(s); // hash
            System.out.println("The Key is : " + s.substring(0, 7));
            password = s.substring(0, 7);
            in.close();
            in1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public static String encode(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getEncode32(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getEncode32(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }

    public static String encode(byte[] bytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(bytes);
            String str = getEncode32(digest);
            Log.i("mmm-->", str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}