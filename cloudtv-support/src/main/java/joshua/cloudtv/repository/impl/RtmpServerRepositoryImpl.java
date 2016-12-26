package joshua.cloudtv.repository.impl;

import joshua.cloudtv.dao.mapper.RtmpServerInfoMapper;
import joshua.cloudtv.dao.model.RtmpServerInfo;
import joshua.cloudtv.repository.RtmpServerRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @see joshua.cloudtv.service.AuthenticationService
 */
@Repository
public class RtmpServerRepositoryImpl implements RtmpServerRepository {

    @Resource
    private RtmpServerInfoMapper rtmpServerInfoMapper;

    //FIXME
    //以下做法需要改进
    //获得最佳的rtmp server以推送
    public RtmpServerInfo getBestRtmpServerInfo(long ip) {
        // 判断有没有边缘服务器，有的话从边缘服务器里面寻找，没有的话只能从核心服务器选，假设核心服务器的流是互通的

        List<RtmpServerInfo> rtmpServerInfoList = rtmpServerInfoMapper.selectAll();

        // 获得边缘服务器集合
        List<RtmpServerInfo> edgeRtmpServerInfoList = rtmpServerInfoList.stream()
                .filter(e->{ return !e.getClusterUrl().equals(e.getParentClusterUrl());} )
                .collect(Collectors.toList());
        // 无边缘服务器
        if(edgeRtmpServerInfoList.size() == 0) {
            //核心服务器集群一般只有一个，所以只有一个
            return rtmpServerInfoList.get(0);

            // 有边缘服务器
        } else {
            // 从边缘服务器中寻找距离用户最近的一个
            return edgeRtmpServerInfoList.get(new Random().nextInt(edgeRtmpServerInfoList.size()));
        }
    }
}
