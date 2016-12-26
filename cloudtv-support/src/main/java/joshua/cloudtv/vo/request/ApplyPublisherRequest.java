package joshua.cloudtv.vo.request;

/**
 * Created by JoshuaShaw on 2016/12/1.
 */
public class ApplyPublisherRequest {

    /**
     * 用户会话凭证
     */
    private String uuid;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 证件类型
     */
    private int certType;

    /**
     * 证件号
     */
    private String certId;

    /**
     * 证明链接
     */
    private String proofUrl;

    /**
     * 电话号码
     */
    private String phoneNumber;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getCertType() {
        return certType;
    }

    public void setCertType(int certType) {
        this.certType = certType;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getProofUrl() {
        return proofUrl;
    }

    public void setProofUrl(String proofUrl) {
        this.proofUrl = proofUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
