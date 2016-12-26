package joshua.cloudtv.repository;

import joshua.cloudtv.dao.model.RtmpServerInfo;

/**
 * rtmp服务器仓储层
 */
public interface RtmpServerRepository {
    RtmpServerInfo getBestRtmpServerInfo(long ip);
}
