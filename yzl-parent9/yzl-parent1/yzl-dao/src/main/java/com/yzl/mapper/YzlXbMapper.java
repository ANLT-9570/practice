package com.yzl.mapper;

import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlXb;
import com.yzl.pojo.YzlXbExample;
import com.yzl.pojo.dto.TaskDTO;
import com.yzl.pojo.dto.XbDTO;
import com.yzl.utils.CountZLLBDTO;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.dto.CountyTaskWorkingDTO;
import com.yzl.utils.enums.TaskProgressStatusEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.vo.BacklogVO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YzlXbMapper {
    int countByExample(YzlXbExample example);

    int deleteByExample(YzlXbExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YzlXb record);

    int insertSelective(YzlXb record);

    List<YzlXb> selectByExample(YzlXbExample example);

    YzlXb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YzlXb record, @Param("example") YzlXbExample example);

    int updateByExample(@Param("record") YzlXb record, @Param("example") YzlXbExample example);

    int updateByPrimaryKeySelective(YzlXb record);

    int updateByPrimaryKey(YzlXb record);
    
    //查询所有县级上报的面积并通过County进行分组
    public List<XbDTO> queryXTJSBMJGroupByCounty(@Param("anumbers")List<String> anumbers);

    //查询所有县级上报的面积并通过City进行分组
    public List<TaskDTO> queryXTJSBMJGroupByCity(@Param("anumbers")List<String> anumbers,@Param("year")String year,@Param("stats")List<String> stats);
    //根据city查询县级上报面积并通过县分组
   List<TaskDTO> queryXTJSBMJGroupCountyAndByCity(@Param("anumbers")List<String> anumbers,@Param("city")String cityCode,@Param("year")String year,@Param("stat")Integer stat);
   
   ////根据市的编号查询小班表中的拥有的县的编号
	List<String> selectByCityNumber(@Param("anumber")String anumber,@Param("gc")String gc,@Param("zl")String zl);

	//根据市的编号查询小班表中的拥有年度
	List<String> selectByYer(@Param("anumber")String anumber,@Param("times")String times);

	//查询某个县知道年度的小班
	List<YzlXb> selectByCountyAndTime(@Param("county")String county,@Param("gclb")String gclb,@Param("zllb")String zllb, @Param("time")String time);

	//查询所有根据地区和年度分组区分 市市市市
	List<YzlXb> selectByGropCity(@Param("anumber")String anumber,@Param("sname")String sname,@Param("times")String times,@Param("permsList")List<String> permsList,@Param("page")Integer page,@Param("rows")Integer rows,@Param("cityCode")String cityCode,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//总记录数 市市市
	List<YzlXb> selecTtotalCity(@Param("anumber")String anumber,@Param("sname")String sname,@Param("times")String times,@Param("permsList")List<String> permsList,@Param("cityCode")String cityCode,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//查询所有根据地区和年度分组区分 县县县县
	List<YzlXb> selectByGropCounty(@Param("number")String number,@Param("sname")String sname,@Param("time")String time, @Param("page")Integer page, @Param("rows")Integer rows,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//总记录数 县县县
	List<YzlXb> selecTtotalCounty(@Param("number")String number,@Param("sname")String sname,@Param("time")String time,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//查询所有自治区
	List<YzlXb> selectByGropMunicipality(@Param("sname")String sname,@Param("time")String time,@Param("permsList")List<String> permsList, @Param("page")Integer page, @Param("rows")Integer rows,@Param("cityCode")String cityCode,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//总记录数
	List<YzlXb> selecTtotalMunicipality(@Param("sname")String sname,@Param("time")String time,@Param("permsList")List<String> permsList,@Param("cityCode")String cityCode,@Param("zllb")String zllb,@Param("gclb")String gclb);
	
	//查询任务根据县、工程类别、造林类别 进行分组
	List<CountyTaskWorkingDTO> queryTaskWorkingByGroupCountyAndGCLBAndZLLB(@Param("aNumbers")List<String> aNumbers,@Param("year")String year,@Param("stats")List<Integer> stats);
	
	//根据地区行政编号,任务状态 ，year查询任务根据县、工程类别、造林类别 进行分组
	List<CountyTaskWorkingDTO> queryTaskWorkingByGroupCountyAndGCLBAndZLLBAndAreaCode(@Param("aNumbers")List<String> aNumbers,@Param("areaCode")String areaCode,@Param("year")String year,@Param("stats")List<Integer> stats);

	//提交
	int updateByMark(@Param("city")String city, @Param("county")String county, @Param("JHND")String jHND, @Param("ZYND")String zYND,@Param("stat")String stat,@Param("stats")List<String> stats,@Param("zllb")String zllb);

	List<YzlTask> selectByCodeAndEpc(@Param("time")String time, @Param("code")String code,@Param("epc") String epc,@Param("stat")String stat);

	List<CountyTaskWorkingDTO> seletByEpcAndTask(@Param("time")String time, @Param("code")String code,@Param("epc") String epc,@Param("authoritys")List<String> authoritys,@Param("stats")List<Integer> stats);

	List<YzlXb> selectByEidAndTime(@Param("eid")String eid, @Param("year")String year,@Param("county")String county);

	List<YzlTask> selectEpcTabName(@Param("stats")List<String> stats,@Param("year")String year,@Param("disCode")String disCode,@Param("menu")List<String> menu,@Param("zllb")String zllb);

	//根据工程查询拥有的任务
	List<YzlEpc> selectByEpcPossessTask(@Param("tcode")String tcode,@Param("year")String year,@Param("stats")List<String> stat,@Param("disCode")String disCode,@Param("menu")List<String> menu,@Param("zllb")String zllb);

	List<YzlEpcAndTaskStaticti> selectByGroupStaticti(@Param("year")String year, @Param("stat")Integer stat);
	
	//查询所有这个状态的市去重
	List<String> selectByCityAndTimeAndStat(@Param("year")String year, @Param("stats")List<String> stats,@Param("menu")List<String> menu);

	//查询这个市今年有多少个工程
	List<YzlEpcAndTaskStaticti> selectByCityAndEpc(@Param("stats")List<String> stats,@Param("year")String year,@Param("city")String city,@Param("disCode")String disCode,@Param("citys") List<String> citys,@Param("menu")List<String> menu,@Param("zllb")String zllb);

	
	//得到这个市这个工程今年的数据
	List<YzlEpcAndTaskStaticti> selectByEidAndCityAndTime(@Param("stat")Integer stat,@Param("year")String year,@Param("city")String city, @Param("eid")Integer eid);

	//根据市编号查询市名称
	String selectByCity(String city);

	List<String> selectByCityAndTimeAndStatCountyAndCity(@Param("year")String year, @Param("stats")List<String> stats, @Param("disCode")String disCode,@Param("menu")List<String> menu);

	//查询这个工程这个状态下有多少个任务
	List<YzlXb> selectByeid(@Param("eid")String eid,@Param("stat")Integer stat,@Param("year")String year,@Param("menu")List<String> menu,@Param("Anumbers")List<String> Anumbers);

	//查询自治区或市或县所应用的Anumber编号
	List<String> selectByFlagOrDcode(String nid);

	//根据工程名查询拥有多少个任务
	List<YzlXb> selectByEpc(@Param("year")String year,@Param("stat") Integer stat, @Param("ename")String ename,@Param("Anumbers")List<String> Anumbers,@Param("menu")List<String> menu);

	//查出这个造林类别今年的计划任务是多少
	YzlEpcTaskProgress selectByPlanTask(@Param("zllb")String zllb, @Param("gclb")String gclb, @Param("county")String county, @Param("jhnd")String jhnd, @Param("zynd")String zynd);

	////查出这个状态造林类别今年完成了多少
	YzlEpcAndTaskStaticti selectByCompleteTask(@Param("zllb")String zllb, @Param("gclb")String gclb, @Param("county")String county, @Param("jhnd")String jhnd, @Param("zynd")String zynd,@Param("stats")List<String> stats);

	//查询这个造林类别拥有多少个工程//	tcode 造林类别编号	year时间		stat状态		nid市县或自治区
	List<String> selectByTaskPossessEpc(@Param("tcode")String tcode, @Param("year")String year,@Param("stat") String stat, @Param("nid")String nid);

	/*List<YzlEpcAndTaskStaticti> selectByCityAndEpcInMenu(@Param("stat")Integer stat,@Param("year") String year,@Param("citys") List<String> citys, @Param("disCode")String disCode);*/
	List<YzlTask> selectByCodeAndEpc();
	//		<!-- 根据地区行政编号和作业年度查询县级自检上报面积并通过 县级、工程类别、造林类别、作业年度、计划年度进行分组 -->
	List<YzlEpcAndTaskStaticti> queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYND(@Param("year")String year,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB);
	//根据地区行政编号和作业年度查询县级自检上报面积并通过 县级、工程类别、造林类别、作业年度、计划年度进行分组 -->
	List<YzlEpcAndTaskStaticti> queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYND(@Param("year")String year,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB);

	List<YzlEpcAndTaskStaticti> selectByCityAndEpcComplation(@Param("stats")List<String> stats,@Param("year")String year,@Param("city")String city,@Param("disCode")String disCode,@Param("citys") List<String> citys,@Param("menu")List<String> menu,@Param("zllb")String zllb);
	//统计市级造林类别数量
	List<CountZLLBDTO> countCityZllbNumber(@Param("stats")List<Integer> stats);
	//统计县级造林类别数量
	List<CountZLLBDTO> countCountyZllbNumber(@Param("stats")List<Integer> stats);
	//统计带审核的个数
	int countCountyNmberByStat(@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys);
	
	List<YzlEpcAndTaskStaticti> queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(@Param("year")String year,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);

	List<YzlEpcAndTaskStaticti> queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(@Param("year")String year,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);

	List<BacklogVO> queryCountyAndCityAndUpdateTimeAndStat(@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys);
	

	//查询下发的任务
	List<YzlEpcTaskProgress> selectByCityCountyTaskIssued(@Param("year")String year,@Param("menu")List<String> menu,@Param("zllb")String zllb,@Param("stats")List<String> stats);

	//查询工作中或完成的任务
	List<YzlXb> selectByCityComplation(@Param("year")String year,@Param("stats") List<String> stats, @Param("menu")List<String> menu,@Param("disCode") String disCode,@Param("zllb") String zllb,@Param("proceed")String proceed);

	List<YzlTask> selectByTaskIssuedTableHead(@Param("year")String year, @Param("disCode")String disCode,@Param("zllb") String zllb,@Param("menu") List<String> menu,@Param("stats")List<String> stats);

	List<YzlEpc> selectByZllb(@Param("tcode")String tcode,@Param("year")String year,@Param("disCode") String disCode,@Param("menu") List<String> menu,@Param("zllb") String zllb,@Param("stats")List<String> stats);

	List<YzlEpcTaskProgress> selectByCountyCountyTaskIssued(@Param("year")String year,@Param("menu") List<String> menu,@Param("disCode") String disCode,@Param("zllb") String zllb,@Param("stats")List<String> stats,@Param("proceed")String proceed);

	int updateByEpcTaskTable(@Param("zllb")String zllb, @Param("county")String county,@Param("ZYND") String ZYND, @Param("stats")List<String> stats,@Param("code")Integer code);

	
	List<YzlXb> queryDemo(@Param("messageList")List<YzlMessage> messageList);
	//List<YzlXb> selectByCountyComplation(@Param("year")String year,@Param("stats") List<String> stats,@Param("menu") List<String> menu,@Param("disCode") String disCode);


	
	Double sumCityXTJSBMJ(String statu);
	
    //查询所有县级上报的面积和市名并通过City进行分组
    public List<TaskDTO> queryXTJSBMJAndCityGroupByCity(@Param("anumbers")List<String> anumbers,@Param("year")String year,@Param("statu")String statu);
    //查询所有县级上报的面积和县名并通过County进行分组
    public List<TaskDTO> queryXTJSBMJAndCountyByCityCode(@Param("anumbers")List<String> anumbers,@Param("year")String year,@Param("statu")String statu,@Param("cityCode")String cityCode);

}