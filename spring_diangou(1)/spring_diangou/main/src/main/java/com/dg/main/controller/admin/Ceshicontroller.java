
package com.dg.main.controller.admin;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

@Controller
public class Ceshicontroller {
	
	@RequestMapping("/Test/login")
	public @ResponseBody ModelMap encodeToString(String url,HttpServletResponse httpServletResponse) throws IOException {
		ModelMap map = new ModelMap();
	  //  String type = StringUtils.substring(imagePath, imagePath.lastIndexOf(".") + 1);
		//获取图片类型
		
	//String url = "D:\\tupian\\index.jpg";
		int indexOf = url.indexOf(".");
		System.out.println(indexOf);
		String result = url.substring(indexOf+1, url.length());
		System.out.println(result);
		
		
	    BufferedImage image = ImageIO.read(new File(url));
	    String imageString = null;
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    try {
	        ImageIO.write(image, result, bos);
	        byte[] imageBytes = bos.toByteArray();
	        BASE64Encoder encoder = new BASE64Encoder();
	        imageString = encoder.encode(imageBytes);
	        bos.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	 
	    map.put("imageString", imageString);
	    map.put("typess", result);
	    return map;
	}

}
