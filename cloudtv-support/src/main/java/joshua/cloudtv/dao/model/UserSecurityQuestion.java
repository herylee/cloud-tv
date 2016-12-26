package joshua.cloudtv.dao.model;

public class UserSecurityQuestion {
    private Integer id;

    private String questionDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription == null ? null : questionDescription.trim();
    }
}