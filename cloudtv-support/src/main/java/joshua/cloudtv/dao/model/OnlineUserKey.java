package joshua.cloudtv.dao.model;

public class OnlineUserKey {
    private String cookieUuid;

    private Integer userid;

    public String getCookieUuid() {
        return cookieUuid;
    }

    public void setCookieUuid(String cookieUuid) {
        this.cookieUuid = cookieUuid == null ? null : cookieUuid.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}