package joshua.cloudtv.dao.model;

public class Publisher {
    private Integer id;

    private Integer roomId;

    private String realname;

    private Byte certType;

    private String certId;

    private String proofUrl;

    private String phoneNumber;

    private Byte level;

    private Long experience;

    private Integer publisherCondition;

    private Integer followerCount;

    private Integer sumWatchCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Byte getCertType() {
        return certType;
    }

    public void setCertType(Byte certType) {
        this.certType = certType;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId == null ? null : certId.trim();
    }

    public String getProofUrl() {
        return proofUrl;
    }

    public void setProofUrl(String proofUrl) {
        this.proofUrl = proofUrl == null ? null : proofUrl.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public Integer getPublisherCondition() {
        return publisherCondition;
    }

    public void setPublisherCondition(Integer publisherCondition) {
        this.publisherCondition = publisherCondition;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getSumWatchCount() {
        return sumWatchCount;
    }

    public void setSumWatchCount(Integer sumWatchCount) {
        this.sumWatchCount = sumWatchCount;
    }
}