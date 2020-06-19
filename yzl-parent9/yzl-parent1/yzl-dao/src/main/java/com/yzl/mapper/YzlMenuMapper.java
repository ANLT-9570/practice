package com.yzl.mapper;

import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlMenuExample;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.vo.TreeVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlMenuMapper {
    int countByExample(YzlMenuExample example);

    int deleteByExample(YzlMenuExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(YzlMenu record);

    int insertSelective(YzlMenu record);

    List<YzlMenu> selectByExample(YzlMenuExample example);

    YzlMenu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") YzlMenu record, @Param("example") YzlMenuExample example);

    int updateByExample(@Param("record") YzlMenu record, @Param("example") YzlMenuExample example);

    int updateByPrimaryKeySelective(YzlMenu record);

    int updateByPrimaryKey(YzlMenu record);
    //递归查询所有权限
    List<TreeVO> findAllRecursion();
    //根据角色查询权限
    List<TreeVO> findFunctionByRoleId(@Param("roleId") Long roleId);

	List<YzlMenu> findMenuByRoleId(@Param("valueOf")Long valueOf);

	List<YzlMenu> selectByUserId(Integer id);

	List<YzlMenu> findAll();

	List<YzlMenu> findByPid(Long pid);

	//根据用户id查询权限标识
	List<String> queryPermsByUserId(@Param("userId")Integer userId);
	
	//分页查询
	List<YzlEpcTaskProgress> selectByUid(@Param("userId")Integer userId,@Param("page")Integer page,@Param("rows")Integer rows,@Param("dpcode")String did,@Param("epcode")String epcode,@Param("tpcode")String tpcode,@Param("time")String time);

	//统计
	List<YzlEpcTaskProgress> selectByUidStiti(@Param("userId")Integer id,@Param("dpcode")String did,@Param("epcode")String epcode,@Param("tpcode")String tpcode,@Param("time")String time);

	//查询所有
	List<YzlEpcTaskProgress> selectFindAllETP(@Param("dpcode")String did,@Param("epcode")String epcode,@Param("tpcode")String tpcode,@Param("time")String time);

	//分页查询所有
	List<YzlEpcTaskProgress> selectFindAllETPAndPage(@Param("page")Integer page,@Param("rows") Integer rows,@Param("dpcode")String did,@Param("epcode")String epcode,@Param("tpcode")String tpcode,@Param("time")String time);
	
	//查询这个工程编号拥有的任务
	List<YzlTask> selectByEcode(Integer epcode);

	Integer selectbyPerms(String perms);

	//主节点
	List<YzlMenu> selectByUserIdHost(Integer id);

	//子节点
	List<YzlMenu> selectByUserIdSon(Integer id);

	List<String> selectByUserIdPerms(String id);
}