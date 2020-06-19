package com.yzl.planManagementService.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFShapeGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlMonitoringstatisticsMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlTaskProgressSheetMapper;
import com.yzl.planManagementService.DataPushService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlMonitoringstatistics;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskProgressSheet;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.attr;
import com.yzl.utils.attrs;

@Transactional
@Service
public class DataPushServiceImpl implements DataPushService{

	@Autowired
	private YzlTaskProgressSheetMapper taskProgressSheetMapper;
	
	@Autowired
	private YzlMonitoringstatisticsMapper monitoringstatisticsMapper;
	
	@Autowired
	private YzlDistrictMapper districtMapper;
	@Autowired
	private YzlTaskMapper taskMapper;
	@Autowired
	private YzlEpcMapper epcMapper;
	
	@Override
	public String text(String name) {
		// TODO Auto-generated method stub
		return name;
	}

	//String taskProgress,taskProgress进度
	//dname地区, epcname工程, tname工程的字段，  ,time 时间 ，SBMJ上报面积， HSMJ核实面积，HGMJ合格面积，SJYLMJ设计育林面积，HSYLMJ核实育林面积 ，YDAMJ有档案面积 ，KZXJZJMJ开展县级自检面积 ，GHMJ管护面积
	@Override
	public void push(attrs attrs) throws Exception {
		
		System.out.println(attrs.getAttr().getCOUNTY());
		
		if (attrs.getAttr().getCOUNTY().equals("450122")) {
			attrs.setCOUNTY("横县");
		}else if (attrs.getAttr().getCOUNTY().equals("450123")) {
			attrs.setCOUNTY("武鸣区");
		}else if (attrs.getAttr().getCOUNTY().equals("450124")) {
			attrs.setCOUNTY("马山县");
		}else if (attrs.getAttr().getCOUNTY().equals("450125")) {
			attrs.setCOUNTY("上林县");
		}else if (attrs.getAttr().getCOUNTY().equals("450126")) {
			attrs.setCOUNTY("隆安县");
		}
		
		
		System.out.println(attrs.getAttr().getGCLB());
		System.out.println(attrs.getAttr().getZLLB());
		
		if (attrs.getAttr().getGCLB().equals("4")) {
			attrs.setGCLB("石漠化工程");
		}else if (attrs.getAttr().getGCLB().equals("3")) {
			attrs.setGCLB("珠防林、海防林工程");
		}else if (attrs.getAttr().getGCLB().equals("7")) {
			attrs.setGCLB("油茶营造林项目");
		}else if (attrs.getAttr().getGCLB().equals("5")) {
			attrs.setGCLB("造林补贴项目");
		}else if (attrs.getAttr().getGCLB().equals("2")) {
			attrs.setGCLB("退耕还林工程");
		}
		
		if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("封山育林");
		}else if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("人工造林");
		}else if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("低效林改造");
		}else if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("迹地人工更新造林");
		}else if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("退耕地还林");
		}
		
		//attrs.setGCLB("石漠化工程");
		attrs.setZLLB("封山育林");
		
		System.out.println(attrs.getCOUNTY());
		String county2 = attrs.getCOUNTY();//县
		String gclb = attrs.getGCLB();//工程
		String zllb = attrs.getZLLB();//造林类别
		
		
		
		YzlDistrict district = districtMapper.selectByCounty(county2);
		YzlTask task = taskMapper.selectByTname(zllb);
		YzlEpc epc = epcMapper.selectByEname(gclb);
		
		String city = district.getCity();
		String county = district.getCounty();
		
		//String[] strings = PinYin4jUtils.getHeadByString(city);
		//String[] stringx = PinYin4jUtils.getHeadByString(county);
		
		//String string = StringUtils.join(strings);
		//String string2 = StringUtils.join(stringx);
		
		//String mark = string+"-"+string2;
		
		YzlMonitoringstatistics monitoringstatistics = new YzlMonitoringstatistics();
		
		System.out.println(district.getDcode());
		System.out.println(epc.getEcode());
		
		//monitoringstatistics.setMark(mark);
		monitoringstatistics.setDid(Long.valueOf(district.getDcode()));//地区
		monitoringstatistics.setEid(Long.valueOf(epc.getEcode()));//工程
		monitoringstatistics.setTime(attrs.getAttr().getZYND());//时间
		monitoringstatistics.setTid(String.valueOf(task.getTcode()));//任务
		monitoringstatistics.setDesignforestarea(attrs.getAttr().getSJYLMJ());//设置育林面积
		monitoringstatistics.setVerifyforestarea(attrs.getAttr().getHSYLMJ());//核实育林面积
		monitoringstatistics.setInspectionreportarea(attrs.getAttr().getXTJSBMJ());//上报面积
		monitoringstatistics.setVerifyarea(attrs.getAttr().getHSMJ());//核实面积
		monitoringstatistics.setCertifiedarea(attrs.getAttr().getHGMJ());//核实合格面积
		//monitoringstatistics.setFilesize(attrs.getSFYDA());//有档案面积
		//monitoringstatistics.setInspectionreportarea(SBMJ);//上报面积
		//monitoringstatistics.setCountyinspectionarea(attrs.getSFZJ());//是否开展县级自检面积
		monitoringstatistics.setManagementarea(attrs.getAttr().getSFGH());//管护面积
		monitoringstatistics.setSfyda(attrs.getAttr().getSFYDA());//是否有档案面积
		monitoringstatistics.setSffy(attrs.getAttr().getSFFY());//是否抚育
		monitoringstatistics.setSfgh(attrs.getAttr().getSFGH());//是否管护
		monitoringstatistics.setSfzj(attrs.getAttr().getSFZJ());//是否开展县级自检
		monitoringstatistics.setYlfs(attrs.getAttr().getYLFS());//育林方式
		monitoringstatistics.setClmj(attrs.getAttr().getCLMJ());//成林面积

		int i = monitoringstatisticsMapper.insertSelective(monitoringstatistics);
		System.out.println(i);
		//获取当前登录的用户setInspectionReportArea
		//Subject subject = SecurityUtils.getSubject();
		//Session session = subject.getSession();
		//YzlUser user = (YzlUser) session.getAttribute("user");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date parse = sdf.parse(attrs.getAttr().getZYND());
		
		YzlTaskProgressSheet epcTaskProgress = new YzlTaskProgressSheet();
		epcTaskProgress.setCreatetime(parse);//创建时间
		epcTaskProgress.setDpcode(Integer.valueOf(district.getDcode()));//地区
		//epcTaskProgress.setMark(mark);//权限标识
		epcTaskProgress.setTaskprogress(Float.parseFloat(attrs.getAttr().getXTJSBMJ()));//任务进度
		System.out.println(attrs.getAttr().getXTJSBMJ()+"11111111111111111111");
		epcTaskProgress.setTpcode(Integer.valueOf(task.getTcode()));//任务Id
		epcTaskProgress.setEpcode(Integer.valueOf(epc.getEcode()));//工程id
		
		System.out.println(epc.getEcode()+" "+task.getTcode()+" "+district.getDcode()+"+111186");
		
		YzlTaskProgressSheet taskProgressSheet = taskProgressSheetMapper.countByEcodeAndTpcodeAndDpcode(Integer.valueOf(epc.getEcode()), Integer.valueOf(task.getTcode()), Integer.valueOf(district.getDcode()));
		//如果记录不存在则插入新数据
		if(ObjectUtils.isEmpty(taskProgressSheet)){
			System.out.println(taskProgressSheet+"222222222222222222");
			taskProgressSheetMapper.insert(epcTaskProgress);
		}else{
			//修改任务数据  
			System.out.println(taskProgressSheet);
			taskProgressSheet.setTaskprogress(taskProgressSheet.getTaskprogress()+epcTaskProgress.getTaskprogress());
			taskProgressSheetMapper.update11(taskProgressSheet);
		}
		
//		String mark = StringUtils.join(strings)+"-"+StringUtils.join(stringx);
//		//转日期
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//		
//		Date parse = sdf.parse(time);
		
		/*YzlTaskProgressSheet record = new YzlTaskProgressSheet();
		
		record.setDpcode(district.getDcode());
		record.setTpcode(task.getTcode());
		record.setEpcode(epc.getEcode());
		record.setCreatetime(parse);
		//record.setTaskprogress(Float.valueOf(taskProgress));
		record.setMark(mark);
		record.setCreator(null);
		int insertSelective = taskProgressSheetMapper.insertSelective(record);*/
//		System.out.println(insertSelective);
	}

}
