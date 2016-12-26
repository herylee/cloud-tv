package joshua.cloudtv.dao.model;

public class UserSecurity {
    private Integer id;

    private Integer questionOne;

    private Integer questionTwo;

    private Integer questionThree;

    private String answerOne;

    private String answerTwo;

    private String answerThree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionOne() {
        return questionOne;
    }

    public void setQuestionOne(Integer questionOne) {
        this.questionOne = questionOne;
    }

    public Integer getQuestionTwo() {
        return questionTwo;
    }

    public void setQuestionTwo(Integer questionTwo) {
        this.questionTwo = questionTwo;
    }

    public Integer getQuestionThree() {
        return questionThree;
    }

    public void setQuestionThree(Integer questionThree) {
        this.questionThree = questionThree;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne == null ? null : answerOne.trim();
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo == null ? null : answerTwo.trim();
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree == null ? null : answerThree.trim();
    }
}