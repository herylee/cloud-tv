package joshua.cloudtv.dao.model;

import java.util.Date;

public class RtmpServerInfo {
    private Integer id;

    private String hostname;

    private Long ip;

    private String clusterUrl;

    private String parentClusterUrl;

    private Integer connectionCount;

    private Integer maxConnectionNumber;

    private Date startTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    public Long getIp() {
        return ip;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }

    public String getClusterUrl() {
        return clusterUrl;
    }

    public void setClusterUrl(String clusterUrl) {
        this.clusterUrl = clusterUrl == null ? null : clusterUrl.trim();
    }

    public String getParentClusterUrl() {
        return parentClusterUrl;
    }

    public void setParentClusterUrl(String parentClusterUrl) {
        this.parentClusterUrl = parentClusterUrl == null ? null : parentClusterUrl.trim();
    }

    public Integer getConnectionCount() {
        return connectionCount;
    }

    public void setConnectionCount(Integer connectionCount) {
        this.connectionCount = connectionCount;
    }

    public Integer getMaxConnectionNumber() {
        return maxConnectionNumber;
    }

    public void setMaxConnectionNumber(Integer maxConnectionNumber) {
        this.maxConnectionNumber = maxConnectionNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}