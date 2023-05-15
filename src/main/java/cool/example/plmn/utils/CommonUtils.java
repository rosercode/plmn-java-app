package cool.example.plmn.utils;

import java.awt.*;
import java.net.URL;

/**
 * @author wangshuo
 * @date 2023/5/7 18:14
 */

public class CommonUtils {

    /**
     * 设置主窗口和托盘的 logo
     */
    public static void setLogo(Frame frame, URL url) {
        if (url == null){
            return;
        }
        // 获取图片
        Image logo = Toolkit.getDefaultToolkit().getImage(url);
        // 获取图片的路径
        frame.setIconImage(logo);
        // 创建一个托盘图标(任务栏右下角显示图标)
        TrayIcon icon = new TrayIcon(logo, null, null);
        //关键点，设置托盘图标的自适应属性，这样才能在系统显示托盘处正常显示出需要的图片
        icon.setImageAutoSize(true);
        //获取表示桌面托盘区的 SystemTray 实例
        SystemTray systemTray = SystemTray.getSystemTray();
        try {
            //将 icon 添加到 SystemTray
            systemTray.add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于判断一个字符串是否为中文
     * @param str
     * @return
     */
    public static boolean isChineseString(String str) {
        String regex = "^[\\u4E00-\\u9FA5]+$";
        return str.matches(regex);
    }

    /**
     * 检查字符串中是否包含中文字符：
     * @param str
     * @return
     */
    public static boolean containsChinese(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.UnicodeScript.of(str.charAt(i)) == Character.UnicodeScript.HAN) {
                return true;
            }
        }
        return false;
    }

    /**
     * 用于判断一个字符串是否为英文：
     * @param str
     * @return
     */
    public static boolean isEnglishString(String str) {
        String regex = "^[a-zA-Z]+$";
        return str.matches(regex);
    }

}
