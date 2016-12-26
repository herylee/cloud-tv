package joshua.cloudtv.constant;

/**
 * 服务接口返回码
 */
public enum RetCode {
    SUCCESS(200), // 成功
    INPUT_ERROR(400), NOT_FOUND(404), // 客户端错误
    SERVER_ERROR(500); // 服务器端错误

    public final int retCode;

    RetCode(int retCode) {
        this.retCode = retCode;
    }
}
