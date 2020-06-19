package com.yzl.utils;

import java.util.ArrayList;
import java.util.List;

import com.yzl.utils.enums.TaskWorkSatusEnum;

public class TaskStatuUtils {

	
		public static List<Integer> getTaskStatUnreviewed(){
			List<Integer> stats = new ArrayList<>();
			stats.add(TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode());
			stats.add(TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode());
			return stats;
		}
		
		
		
		public static List<Integer> getTaskStatAudited(){
			List<Integer> stats = new ArrayList<>();
			stats.add(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode());
			return stats;
		}
}
