package com.yzl.distriEpcTaskService.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzl.distriEpcTaskService.TemplateService;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlTaskEpcMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlEpcTaskProgressExample;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskEpc;
import com.yzl.pojo.YzlTaskEpcExample;
import com.yzl.pojo.YzlTaskExample;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

@Service
public class TemplateServiceImpl implements TemplateService{

	/*@Autowired
	private YzlEpcTaskProgressMapper taskProgressMapper;*/
	
	@Autowired
	private YzlTaskMapper taskMapper;
	
	@Autowired
	private YzlEpcMapper epcMapper;
	
	@Autowired
	private YzlTaskEpcMapper taskEpcMapper;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	
	//查询所有
	@Override
	public EasyUIResult findAll(String value,Integer page,Integer rows) {
		
		if (page==null) {
			page=1;
		}
		if (rows==null) {
			rows=10;
		}
		
		LinkedHashSet<YzlEpc> epcSet=new LinkedHashSet<>();
		
		List<Integer> tatolCount=null;
		List<YzlEpc> vague=null;
		if (value == null || value == "") {
			//所有记录数   
			tatolCount= taskEpcMapper.selectByDistinctEcode();//多对多关系表
			 
			//当前页  等于	当前页	-1	乘以当前页显示的记录数
			int page2=(page-1)*rows;
			//分页获取所有与任务有关联的工程重复的去掉
			List<Integer> list = taskEpcMapper.selectByDistinctEcodePage(page2,rows);//多对多关系表
			if (list != null && list.size() > 0) {
				
				for (Integer integer : list) {
					//获取数据中的工程Id
					Integer epcode = integer;
					YzlTaskEpcExample example=new YzlTaskEpcExample();
					example.createCriteria().andEcodeEqualTo(epcode);
					
					//查询这个工程拥有的任务
					List<YzlTaskEpc> TaskIds = taskEpcMapper.selectByExample(example);
					
					//用来装任务的集合
					List<YzlTask> tasksList=new ArrayList<>();
					for (YzlTaskEpc YzlTaskEpc : TaskIds) {
						//获取工程中的任务id
						Integer tpcode = YzlTaskEpc.getTcode();
						//根据id查询出任务对象放进集合中
						YzlTask task = taskMapper.selectByPrimaryKey(tpcode);
						tasksList.add(task);
					}
					//根据id查询工程
					YzlEpc epc = epcMapper.selectByPrimaryKey(epcode);
					//把工程拥有的任务放进集合
					epc.setList(tasksList);
					
					int sum=0;
					//遍历LinkHashSet的植和放进LinkHashSet的集合的植比较如果他们的名字不一样就添加进去
					if (epcSet != null && epcSet.size() > 0) {
						for(YzlEpc epc2:epcSet) {
							if(!epc2.getEname().equals(epc.getEname())) {
								sum+=1;
							}
						}
						if (sum==epcSet.size()) {
							epcSet.add(epc);
						}
					}else {
						epcSet.add(epc);
					}
				}
			}
		}else {
			 vague = taskEpcMapper.selectVague(value);
			//当前页  等于	当前页	-1	乘以当前页显示的记录数
			int page2=(page-1)*rows;
			//根据输入的字符得到工程对象+分页
			List<YzlEpc> list = taskEpcMapper.selectVaguePage(value,page2,rows);
			if (list != null && list.size() > 0) {
				for (YzlEpc yzlEpc : list) {
					//根据工程id得到所拥有的任务对象
					List<YzlTask> tasks = taskEpcMapper.selectByEcode(yzlEpc.getEcode());
					yzlEpc.setList(tasks);
					epcSet.add(yzlEpc);
				}
			}
		}
		EasyUIResult result=new EasyUIResult();
		
		if (value == null || value == "") {
			result.setTotal(tatolCount.size());//总记录数
			result.setRows(new ArrayList<>(epcSet));
			return result;
		}else {
			result.setTotal(vague.size());
			result.setRows(new ArrayList<>(epcSet));
			
			return result;
		}
	}

	//添加
		@Override
		public YzlResult addTemplate(String eid, String[] tids) {
			
			//根据工程id查询
			List<YzlTaskEpc> listTaskEpc = this.findByEid(eid);
			
			if (listTaskEpc.size()>0 && listTaskEpc!=null) {
				return YzlResult.ok(300);
			}
			int sum=0;
			if (tids.length>0) {
				for (String string : tids) {
					
					YzlTaskEpc record=new YzlTaskEpc();
					record.setEcode(Integer.valueOf(eid));
					record.setTcode(Integer.valueOf(string));
					
					int si = taskEpcMapper.insert(record);
					sum+=si;
				}
			}
			
			if (sum==tids.length) {
				return YzlResult.ok(200);
			}
			return YzlResult.ok(400);
		}

	//根据工程id查询
	public List<YzlTaskEpc> findByEid(String eid){
		
		YzlTaskEpcExample example=new YzlTaskEpcExample();
		example.createCriteria().andEcodeEqualTo(Integer.valueOf(eid));
		List<YzlTaskEpc> list = taskEpcMapper.selectByExample(example);
		
		return list;
	}

	//删除
	@Override
	public YzlResult deleterTemplate(String[] etdis) {
		
		List<List<YzlEpcTaskProgress>> epcTaskProgresses =new ArrayList<>();
		List<YzlEpcTaskProgress> etp = null;
		for (String string : etdis) {
			
			YzlEpcTaskProgressExample example=new YzlEpcTaskProgressExample();
			etp = epcTaskProgressMapper.selectByExample(example);
			if (etp!=null&&etp.size()>0) {
				epcTaskProgresses.add(etp);
			}
		}
		
		if (epcTaskProgresses!=null && epcTaskProgresses.size()>0) {
			return YzlResult.ok(300);
		}
		
		int dum=0;
		for (int i = 0; i < etdis.length; i++) {
			String string = etdis[i];
			taskEpcMapper.deleteByEcode(Integer.valueOf(string));
			dum+=1;
		}
		if (dum>0) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	//修改
	@Override
	public YzlResult updateTemplate(String edname, String[] ids) {
		//根据工程名称查出工程编号
		Integer ecode = taskEpcMapper.selectByEpcEname(edname);
		//根据工程编号删除
		int ko = taskEpcMapper.deleteByEcode(ecode);
		//批量插入
		YzlResult result = this.addTemplate(String.valueOf(ecode), ids);
		Integer data = (Integer) result.getData();
		
		if (ko > 0 && data==200) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	@Override
	public List<YzlTask> showTask() {
		YzlTaskExample example=new YzlTaskExample();
		List<YzlTask> list = taskMapper.selectByExample(example);
		return list;
	}

}
