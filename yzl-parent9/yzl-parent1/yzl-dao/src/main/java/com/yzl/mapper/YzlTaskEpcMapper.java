package com.yzl.mapper;

import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskEpc;
import com.yzl.pojo.YzlTaskEpcExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlTaskEpcMapper {
    int countByExample(YzlTaskEpcExample example);

    int deleteByExample(YzlTaskEpcExample example);

    int insert(YzlTaskEpc record);

    int insertSelective(YzlTaskEpc record);

    List<YzlTaskEpc> selectByExample(YzlTaskEpcExample example);

    int updateByExampleSelective(@Param("record") YzlTaskEpc record, @Param("example") YzlTaskEpcExample example);

    int updateByExample(@Param("record") YzlTaskEpc record, @Param("example") YzlTaskEpcExample example);

    //所有记录数去重
	List<Integer> selectByDistinctEcode();

	//分页获取所有工程id去重
	List<Integer> selectByDistinctEcodePage(int page2, Integer rows);

	//模糊查询
	List<YzlEpc> selectVague(String value);

	//模糊加分页
	List<YzlEpc> selectVaguePage(String value, int page2, Integer rows);

	//根据工程id查询所拥有的任务
	List<YzlTask> selectByEcode(Integer ecode);

	//根据工程id删除
	int deleteByEcode(Integer ecode);

	//根据工程名查出工程编号
	Integer selectByEpcEname(String edname);


}