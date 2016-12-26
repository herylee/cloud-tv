package joshua.cloudtv.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by JoshuaShaw on 2016/12/4.
 */
public class Encryptor {
    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String encrypt(String str, long salt)  {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // 先这样做
            str = str + salt;

            // 计算sha1函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return "00000000";
        }
    }
}
