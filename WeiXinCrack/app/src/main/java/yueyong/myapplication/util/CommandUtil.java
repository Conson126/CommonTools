package yueyong.myapplication.util;

import android.util.Log;

/**
 * Created by YueYong on 2015/4/2.
 * 修改文件权限的工具类
 */
public class CommandUtil {

    public static boolean runCommand(String command) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            Log.i("command", "The Command is : " + command);
            int state = process.waitFor();
            if(state == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.w("Exception ", "Unexpected error - " + e.getMessage());
            return false;
        } /*finally {
            try {
                process.destroy();
            } catch (Exception e) {
                Log.w("Exception ", "Unexpected error - " + e.getMessage());
            }
        }*/
    }
}
