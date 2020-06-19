package com.yzl.ExceptionController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.utils.YzlResult;

@ControllerAdvice
public class AppExceptionHandler {

	private static final Logger Log = LoggerFactory.getLogger(AppExceptionHandler.class);
	
//	@Autowired
//	private HttpServletRequest Request;
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public YzlResult exception(Exception e) {
		Log.error(e.getMessage(),e);
		YzlResult result = new YzlResult(400,"·¢Éú´íÎó£¡£¡£¡",e.getMessage());
//		Request.setAttribute("msg", result);
		
		return result;
	}
}
