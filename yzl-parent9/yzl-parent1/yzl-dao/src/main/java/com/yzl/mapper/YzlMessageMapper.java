package com.yzl.mapper;

import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlMessageExample;
import com.yzl.utils.vo.BacklogVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlMessageMapper {
    int countByExample(YzlMessageExample example);

    int deleteByExample(YzlMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YzlMessage record);

    int insertSelective(YzlMessage record);

    List<YzlMessage> selectByExample(YzlMessageExample example);

    YzlMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YzlMessage record, @Param("example") YzlMessageExample example);

    int updateByExample(@Param("record") YzlMessage record, @Param("example") YzlMessageExample example);

    int updateByPrimaryKeySelective(YzlMessage record);

    int updateByPrimaryKey(YzlMessage record);
    
    
    int countMessageNumber(@Param("authoritys")List<String> authoritys,@Param("keywork")String keywork);
    
    List<BacklogVO> countMessageNumberGroupByCountyCode(@Param("authoritys")List<String> authoritys,@Param("keywork")String keywork);
    
    List<String> queryCountyCodeByStatuAndCountyCode(@Param("authoritys")List<String> authoritys,@Param("userId")String userId);
   
    List<String> queryDISTINCTCountyCodeByStatuAndCountyCode(@Param("authoritys")List<String> authoritys,@Param("keywork")String keywork);
    //设置信息状态，根据用户id和县级编号
    int updateStatuByUserIdAndCountCode(@Param("keywork")String keywork,@Param("authoritys") List<String> authoritys);

    List<YzlMessage>  queryByAuthoritysAndStatu(@Param("authoritys")List<String> authoritys,@Param("keywork")String keywork);
}