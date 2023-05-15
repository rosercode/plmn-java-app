package cool.example.plmn.utils;

/**
 * @author wangshuo
 * @date 2023/5/16 1:38
 */

public class StringUtils {

    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
