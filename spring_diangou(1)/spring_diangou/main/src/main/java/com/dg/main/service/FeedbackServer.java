package com.dg.main.service;

import com.dg.main.Entity.Feedback;
import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;

public interface FeedbackServer extends BaseMapper<Feedback>{

	slzcResult selectAll(int offset, int limit,String search) ;
}
