package joshua.cloudtv.utils;

/**
 * Created by JoshuaShaw on 2016/12/6.
 */
import java.net.InetAddress;

/**
 * @author michael <br>
 *         blog: http://sjsky.iteye.com <br>
 *         mail: sjsky007@gmail.com
 */
public class IPv4Util {

    private final static int INADDRSZ = 4;

    private static byte[] ipToBytesByInet(String ipAddr) {
        try {
            return InetAddress.getByName(ipAddr).getAddress();
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    private static long bytesToLong(byte[] bytes) {
        long addr = bytes[3] & 0xFF;
        addr |= ((overflow(bytes[2]) << 8) & 0xFF00);
        addr |= ((overflow(bytes[1]) << 16) & 0xFF0000);
        addr |= ((overflow(bytes[0]) << 24) & 0xFF000000);
        return addr;
    }

    private static long overflow(byte b) {
        return b<0?b+256:b;
    }

    public static long ipToLong(String ipAddr) {
        try {
            return bytesToLong(ipToBytesByInet(ipAddr));
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    public static String longToIp(long ipLong) {
        return new StringBuilder().append(((ipLong >> 24) & 0xff)).append('.')
                .append((ipLong >> 16) & 0xff).append('.').append(
                        (ipLong >> 8) & 0xff).append('.').append((ipLong & 0xff))
                .toString();
    }

    public static void main(String arg[]) {
        long iplong = IPv4Util.ipToLong("172.1.216.231");
        long temp = IPv4Util.overflow((byte)-84);
        String str = IPv4Util.longToIp(iplong);
        System.out.println(iplong);
    }
}
