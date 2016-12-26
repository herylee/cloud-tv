package joshua.cloudtv.constant;

/**
 * Created by JoshuaShaw on 2016/12/6.
 */
public enum UserPermission {
    USER("一般用户", 1), PUBLISHER("主播", 2), ADMIN("管理员", 4), SUPER_ADMIN("超级管理员", 8);

    private String description;
    private int permission;

    UserPermission(String description, int permission) {
        this.description = description;
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public int getPermission() {
        return  permission;
    }

    public static boolean isPublisher(int permission) {
        return (PUBLISHER.getPermission() & permission) >> 1 == 1;
    }
}
