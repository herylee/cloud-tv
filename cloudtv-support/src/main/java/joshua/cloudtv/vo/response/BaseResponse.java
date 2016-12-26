package joshua.cloudtv.vo.response;

import joshua.cloudtv.utils.JsonKit;

/**
 * Created by JoshuaShaw on 2016/11/30.
 */
public class BaseResponse {

    /**
     * 返回码
     */
    private int retCode;

    /**
     * 返回说明信息
     */
    private String message;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        return JsonKit.toJson(this);
    }

    public void printJson() {
        JsonKit.printJson(this);
    }
}
