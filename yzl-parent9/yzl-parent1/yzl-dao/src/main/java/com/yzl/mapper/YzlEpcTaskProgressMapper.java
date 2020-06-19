package com.yzl.mapper;

import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlEpcTaskProgressExample;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlMonitoringstatistics;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.dto.TaskDTO;
import com.yzl.utils.dto.TaskIssuedDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface YzlEpcTaskProgressMapper {
    int countByExample(YzlEpcTaskProgressExample example);

    int deleteByExample(YzlEpcTaskProgressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YzlEpcTaskProgress record);

    int insertSelective(YzlEpcTaskProgress record);

    List<YzlEpcTaskProgress> selectByExample(YzlEpcTaskProgressExample example);

    YzlEpcTaskProgress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YzlEpcTaskProgress record, @Param("example") YzlEpcTaskProgressExample example);

    int updateByExample(@Param("record") YzlEpcTaskProgress record, @Param("example") YzlEpcTaskProgressExample example);

    int updateByPrimaryKeySelective(YzlEpcTaskProgress record);

    int updateByPrimaryKey(YzlEpcTaskProgress record);
    
    //根据工程编号删除
    int deleteByEcode(Integer ecodes);
    //查询所有的工程去掉重复的+分页
    List<Integer> selectByDistinctEcodePage(Integer start,Integer end);
  //查询所有的工程去掉重复的+分页
    List<Integer> selectByDistinctEcode();
    //根据工程id查询所有任务进度
    List<YzlEpcTaskProgress> selectDistinctByEpcode(int epcode);
    //根据工程名称查工程编号
    Integer selectByEpcEname(String ename);
  //模糊查询//根据输入的字符得到工程对象+分页
    List<YzlEpc> selectVaguePage(String ename,Integer start,Integer end);
    //模糊查询//根据输入的字符得到工程对象
    List<YzlEpc> selectVague(String ename);
    
    //List<Integer> selectListEcode(List<Map<Integer, Integer>> lm);
    //List<Map<Integer, Integer>> selectListET(String value);
    //根据一个工程编号返回工程拥有的所有任务对象
    List<YzlTask> selectByEcode(Integer ecode);
    List<YzlTask> selectByEcodes(@Param("ecode")String ecode,@Param("year")String year);
    //根据工程编号查询所有任务进度和地区
    List<TaskIssuedDTO> queryByYearAndGCLB(@Param("list")List<String> list,@Param("year")String year,@Param("GCLB")String GCLB);
    
    //统计通过 数据并根据地区id去重复
    long countTaskNumber();
    //统计创建时间的年 查询
    List<YzlEpcTaskProgress> selectByCreateTime(@Param("list")List<String> marks,@Param("year")String year);
    //修改发布的任务
    void updateTaskProgress(YzlEpcTaskProgress epcTaskProgress);
    
    void deleteByEcodeAndCreateTime(YzlEpcTaskProgress epcTaskProgress);
    
    List<YzlEpcTaskProgress> searchKey(@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    
    List<YzlEpcTaskProgress> searchKeyAndEcode(@Param("list")List<String> marks,@Param("ecode")int ecode,@Param("searchKey")String searchKey);
    
    List<TaskDTO> queryTaskGroupByCity(@Param("aNumbers")List<String> aNumbers,@Param("year")String year);

    List<YzlEpcTaskProgress> queryTaskDistrictByEcodeAndAreaAndYear(@Param("list")List<String> permsList,@Param("ecode") Integer ecode,@Param("area") String area,@Param("year") String year);

    //根据县查询拥有的工程
	List<YzlEpc> selectByCounty(String county);

	//根据县和工程查询工程拥有的字段
	List<YzlTask> selectByEcodeAndDname(@Param("ecode")String ecode,@Param("county")String county);

	//查询任务基数通过mark并按county进行
	List<TaskDTO> selectTaskNumberGroupByCountyAndByMark(@Param("aNumbers")List<String> anumbers,@Param("cityCode")String cityCode,@Param("year")String year);
	
	//查询所有
	List<YzlEpcTaskProgress> selectAll(@Param("ecode")String ecode,@Param("county")String county,@Param("tname")String tname);

	//分页查询
	List<YzlEpcTaskProgress> selectAllPage(@Param("ecode")String ecode,@Param("county")String county,@Param("tname")String tname,@Param("rows")Integer rows,@Param("page")int page2);

	//根据用户id查询这个用户拥有的所有记录
	List<YzlEpcTaskProgress> selectByUidAndL(@Param("uid")Integer uid,@Param("epcode")String ecode,@Param("dpcode")String dcode,@Param("tpcode")String tcode);

	List<YzlEpcTaskProgress> selectByUidAndLPage(@Param("uid")Integer uid,@Param("epcode")String ecode,@Param("dpcode")String dcode,@Param("tpcode")String tcode,@Param("rows")Integer rows,@Param("page")Integer page);

	//根据用户获取到权限
	List<YzlMenu> findByUid(Integer uid);

	//
	List<YzlMonitoringstatistics> selectAlls(@Param("ecode")String ecode,@Param("dcode")String dcode,@Param("tcode")String tcode,@Param("time")String time);

	List<YzlMonitoringstatistics> selectAllPages(@Param("ecode")String ecode,@Param("dcode")String dcode,@Param("tcode")String tcode,@Param("rows")Integer rows,@Param("page") int page,@Param("time")String time);
	
	//通过作业年度和地区行政编号查询任务下发数据根据县、工程类别、县级编号进行分组
	List<TaskIssuedDTO> queryTaskIssuedByGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(@Param("aNumbers")List<String> aNumbers ,@Param("areaCode")String areaCode,@Param("year")String year,@Param("GCLB")String GCLB);
	//更新    通过作业年度，计划年度，工程类别，造林类别，县行政编号
	int updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(YzlEpcTaskProgress epcTaskProgress);
	//删除   通过作业年度，计划年度，工程类别，造林类别，县行政编号
	int deleteByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(YzlEpcTaskProgress epcTaskProgress);
	//删除   通过作业年度，计划年度，县行政编号
	void deleteByZYNDAndJHNDAndCountyCode(@Param("ZYND")String ZYND,@Param("JHND")String JHND, @Param("countyCode")String countyCode,@Param("cityCode")String cityCode,@Param("zllbs")Set<String> zllbs,@Param("gclbs")Set<String> gclbs);
	//根据记录数 根据工程类别、计划年度、作业年度、造林类别、县行政编号
	int countByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(YzlEpcTaskProgress epcTaskProgress);
	
	List<YzlTask> selectByEpcIdAndTime(@Param("epcid")String epcid, @Param("year")String year);

	YzlEpc selectByEpcId(String eid);

	YzlTask selectByTid(String tid);

	//根据工程查询对应的任务
	List<YzlEpcTaskProgress> selectByEpc(@Param("ename")String ename,@Param("year")String year);

	List<YzlEpcTaskProgress> selectTaskIssued(@Param("gc")String gc, @Param("zl") String zl, @Param("county")String county,@Param("time") String time);
	//查询ZLLB并去重复， 通过用户权限，作业年度，地区行政编号
	//查询并统计市级数据 条件看参数
	List<YzlEpcTaskProgress> queryCityData(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("stats")List<Integer> stats);
	//查询并统计县级数据 条件看参数
	List<YzlEpcTaskProgress> queryCountyData(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("stats")List<Integer> stats);

	List<String> selectByZJCCZ(@Param("zynd")String zYND,@Param("jhnd") String jHND,@Param("countyCode") String countyCode,@Param("cityCode") String cityCode,
			@Param("gclbs")Set<String>  gclbs,@Param("zllbs")Set<String> zllbs);
	//查询这个造林类别拥有的工程
	List<String> selectByTaskPosessEpc(@Param("year")String year,@Param("zllb")String zllb, @Param("code")String clcik,@Param("authority")List<String> authoritys);
	//根据县级行政编号和作业年度 更新上传的文件
	int updatefilesUrlByCountCodeAndZYND(@Param("year")String year,@Param("countyCode")String countyCode,@Param("cityCode")String cityCode,@Param("filesUrl")String filesUrl
			,@Param("modifier")String modifier ,@Param("updateTime")Date updateTime);
	
	//根据县级行政编号和作业年度删除上传的文件
	int deletefilesUrlByCountCodeAndZYND(@Param("year")String year,@Param("countyCode")String countyCode,@Param("cityCode")String cityCode
			,@Param("modifier")String modifier,@Param("uploadFiles") String uploadFiles,@Param("deleteFileCode") String deleteFileCode);
	//查询最近下发新数据并统计县级数据 条件看参数
	List<YzlEpcTaskProgress>  queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);
	//查询最近下发新数据并统计市级数据 条件看参数
	List<YzlEpcTaskProgress> queryCityDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);
	
	//根据记录数 根据工程类别、计划年度、作业年度、造林类别、县行政编号
	List<YzlEpcTaskProgress>  queryByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(YzlEpcTaskProgress epcTaskProgress);

	List<YzlEpcTaskProgress> selectByProceed(@Param("zllb")String zllb,@Param("anumber")String anumber, @Param("year")String year,@Param("stats")List<String> stats);
}