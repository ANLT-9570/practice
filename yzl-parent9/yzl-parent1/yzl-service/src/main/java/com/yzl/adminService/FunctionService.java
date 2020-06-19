package com.yzl.adminService;

import java.util.List;

import com.yzl.pojo.YzlMenu;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.vo.TreeVO;

public interface FunctionService {

	List<TreeVO> getMenu();

	boolean save(YzlMenu menu);

	EasyUIResult getFunctions(int page, int rows);

	void delete(String ids);

	void update(YzlMenu menu);

	List<YzlMenu> findAllMenu();

	List<YzlMenu> findFunctionByRoleId(Long valueOf);

	List<YzlMenu> getMenus();

}
