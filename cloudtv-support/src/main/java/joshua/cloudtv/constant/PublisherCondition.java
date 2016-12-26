package joshua.cloudtv.constant;

/**
 * Created by JoshuaShaw on 2016/12/6.
 */
public enum PublisherCondition {
    NON_VERIFICATION("未验证身份", 0),NORMAL("正常", 1), TEMPORARY_FORBIDDEN("临时禁播", 2),
    PERMANENT_FORBIDDEN("永久禁播", 3), INITIATIVE_FORBIDDEN("主动禁播", 4), IMNORMAL("异常", 127);

    private String description;
    private int condition;

    PublisherCondition(String description, int condition) {
        this.description = description;
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public int getCondition() {
        return condition;
    }
}
