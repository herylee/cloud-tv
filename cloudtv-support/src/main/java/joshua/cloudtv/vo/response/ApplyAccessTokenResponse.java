package joshua.cloudtv.vo.response;

/**
 * Created by JoshuaShaw on 2016/12/1.
 */
public class ApplyAccessTokenResponse extends BaseResponse{

    /**
     * 推送url
     */
    private String publishUrl;

    /**
     * 推送令牌
     */
    private String accessToken;

    public String getPublishUrl() {
        return publishUrl;
    }

    public void setPublishUrl(String publishUrl) {
        this.publishUrl = publishUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
