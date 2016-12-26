package joshua.cloudtv.vo.request;

/**
 * @see joshua.cloudtv.service.UserService
 */
public class SetSecurityQuestionRequest {
    private String uuid;
    private int questionOne;
    private int questionTwo;
    private int questionThree;
    private String answerOne;
    private String answerTwo;
    private String answerThree;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getQuestionOne() {
        return questionOne;
    }

    public void setQuestionOne(int questionOne) {
        this.questionOne = questionOne;
    }

    public int getQuestionTwo() {
        return questionTwo;
    }

    public void setQuestionTwo(int questionTwo) {
        this.questionTwo = questionTwo;
    }

    public int getQuestionThree() {
        return questionThree;
    }

    public void setQuestionThree(int questionThree) {
        this.questionThree = questionThree;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }
}
