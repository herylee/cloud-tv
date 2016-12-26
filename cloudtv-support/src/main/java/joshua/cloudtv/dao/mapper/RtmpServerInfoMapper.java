package joshua.cloudtv.dao.mapper;

import joshua.cloudtv.dao.model.RtmpServerInfo;

import java.util.List;

public interface RtmpServerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RtmpServerInfo record);

    int insertSelective(RtmpServerInfo record);

    RtmpServerInfo selectByPrimaryKey(Integer id);

    List<RtmpServerInfo> selectAll();

    int updateByPrimaryKeySelective(RtmpServerInfo record);

    int updateByPrimaryKey(RtmpServerInfo record);
}