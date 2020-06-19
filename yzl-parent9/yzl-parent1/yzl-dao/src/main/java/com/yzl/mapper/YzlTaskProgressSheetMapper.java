package com.yzl.mapper;

import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlTaskProgressSheet;
import com.yzl.pojo.YzlTaskProgressSheetExample;
import com.yzl.utils.vo.DrilldownNode;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YzlTaskProgressSheetMapper {
    int countByExample(YzlTaskProgressSheetExample example);

    int deleteByExample(YzlTaskProgressSheetExample example);

    int deleteByPrimaryKey(Long id);
    
    int insert(YzlTaskProgressSheet record);

    int insertSelective(YzlTaskProgressSheet record);

    List<YzlTaskProgressSheet> selectByExample(YzlTaskProgressSheetExample example);

    YzlTaskProgressSheet selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YzlTaskProgressSheet record, @Param("example") YzlTaskProgressSheetExample example);

    int updateByExample(@Param("record") YzlTaskProgressSheet record, @Param("example") YzlTaskProgressSheetExample example);

    int updateByPrimaryKeySelective(YzlTaskProgressSheet record);

    int updateByPrimaryKey(YzlTaskProgressSheet record);
    
    //根据工程编号查询所有任务进度和地区   如果工程id为空 则查询所有
    List<YzlTaskProgressSheet> queryTaskDistrictByEcode(@Param("ecode")Integer ecode);
    
    //更新任务进度
    void updateTaskProgressSheet(YzlTaskProgressSheet taskProgressSheet);
    //删除任务进度
    void deleteByEcodeAndDpcodeAndCreateTime(YzlTaskProgressSheet taskProgressSheet);
    //统计
    Integer countCreateTimeAlsoDistinct(@Param("epcode")Integer epcode);
    //查询所以已完成的任务
    List<YzlTaskProgressSheet> queryByEcodeAndMark(@Param("list")List<String> marks,@Param("ecode")Integer ecode,@Param("area")String area,@Param("year")String year);
    //查询所以未完成的任务
    List<YzlTaskProgressSheet> queryByUserpermsAndEcode(@Param("list")List<String> marks,@Param("ecode")Integer ecode);
    //根据mark查询任务进度
    List<YzlTaskProgressSheet> queryByMark(@Param("list")List<String> marks);
    //根据工程id查询不匹配的mark
    List<YzlTaskProgressSheet>  queryByEcodeAndContraryMark(YzlTaskProgressSheet taskProgressSheet);
    //根据关键字搜索
    List<YzlTaskProgressSheet> searchKey(@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    //关键字和mark搜索
    List<YzlTaskProgressSheet> searchKeyAndMark(@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    //关键字和工程ID、mark搜索
    List<YzlTaskProgressSheet> searchKeyAndEcode(@Param("ecode")Integer ecode,@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    //根据工程id、mark和关键字搜索任务进度
    List<YzlTaskProgressSheet> searchKeyAndEcodeMark(@Param("ecode")Integer ecode,@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    //根据关键字和不匹配的mark搜索
    List<YzlTaskProgressSheet> searchKeyAndContraryMark(@Param("mark")String mark,@Param("searchKey")String searchKey);
    
    //查询页面图形数据
    List<Map> queryTaskGroupByCity();
    //查询任务城市并去重复
    List<Map> queryTaskDistinctCity();
    //查询任务根据County进行分组
    List<Map> queryTaskGroupByCounty(String city);
    //根据用户权限查询所有 如果参数不为空则进行相应的过滤,area地区name，year创建时间的年份
    List<YzlTaskProgressSheet> queryByUserPower(@Param("list")List marks,@Param("area")String area,@Param("year")String year);
    //查询所有任务进度并按地区id和任务id进行分组 
    List<YzlTaskProgressSheet> queryTaskProgressGroupByDpcodeAndTpcode(@Param("list")List marks);
    //根据用户权限 和 关键字进行搜索所有任务进度并按地区id和任务id进行分组 
    List<YzlTaskProgressSheet> searchTaskProgressGroupByDpcodeAndTpcode(@Param("list")List marks,@Param("searchKey")String searchKey);
   //通过地区id查询任务进度
    List<YzlTaskProgressSheet> queryTaskprogressByDcode(@Param("dcode")Integer dcode);
    //根据工程Id 和任务id和地区Id进行统计
    YzlTaskProgressSheet countByEcodeAndTpcodeAndDpcode(@Param("ecode")Integer ecode,@Param("Tpcode")Integer Tpcode,@Param("Dpcode")Integer Dpcode);

    void  update11(YzlTaskProgressSheet taskProgressSheet);

}