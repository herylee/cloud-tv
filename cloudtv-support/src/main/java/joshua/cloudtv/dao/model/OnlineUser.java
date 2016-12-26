package joshua.cloudtv.dao.model;

import java.util.Date;

public class OnlineUser extends OnlineUserKey {
    private Date loginDatetime;

    public Date getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }
}