package com.yzl.planManagementController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Decimal;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;



public class Test {

	public static void main(String[] args) throws IOException {
		
		HSSFWorkbook workbook = new HSSFWorkbook();//创建工作簿对象（wxcel文档对象）
		
		HSSFSheet sheet = workbook.createSheet("造林林林林林林林");//创建工作表
		sheet.setColumnWidth(0, 5*256);//设置单元格的宽
		
		HSSFRow row = sheet.createRow(1);//在sheet里创建行
		HSSFCell cell0 = row.createCell(0);//在行里创建列
		row.setHeightInPoints(30);//行高
		//设置单元格内容
		cell0.setCellValue("造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造");
		//合并单元格//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 35));
		
		HSSFRow row1 = sheet.createRow(3);//在sheet里创建行
		
		//在行里创建列
		HSSFCell cell1 = row1.createCell(1);//创建列
		cell1.setCellValue("市");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 1, 1));
		
		HSSFCell cell2 = row1.createCell(2);
		cell2.setCellValue("县");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 2, 2));
		
		HSSFCell cell3 = row1.createCell(3);
		cell3.setCellValue("工程类别 ");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 3, 3));
		
		HSSFCell cell4 = row1.createCell(4);
		cell4.setCellValue("造林类别 ");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 4, 4));
		
		HSSFCell cell5 = row1.createCell(5);
		cell5.setCellValue("时间");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 5, 5));
		
		HSSFCell cell6 = row1.createCell(6);
		cell6.setCellValue("抽查情况");//自检上报面积
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 6, 16));//合并列
		
		HSSFCell cell7 = row1.createCell(17);
		cell7.setCellValue("核查质量情况");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 17, 19));
		
		HSSFCell cell8 = row1.createCell(20);
		cell8.setCellValue("任务完成情况");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 20, 26));
		
		HSSFCell cell9 = row1.createCell(27);
		cell9.setCellValue("管理指标");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 27, 35));
		
		//在建一行
		HSSFRow row2 = sheet.createRow(5);//在sheet里创建行
		
		HSSFCell cell10 = row2.createCell(6);
		cell10.setCellValue("自检上报面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 6, 6));
		
		HSSFCell cell11 = row2.createCell(7);
		cell11.setCellValue("核实面积  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 7, 7));
		
		HSSFCell cell12 = row2.createCell(8);
		cell12.setCellValue("核实合格面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 8, 8));
		
		HSSFCell cell13 = row2.createCell(9);
		cell13.setCellValue("作业设计面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 9, 9));
		
		HSSFCell cell14 = row2.createCell(10);
		cell14.setCellValue("按作业设计施工面 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 10, 10));
		
		HSSFCell cell15 = row2.createCell(11);
		cell15.setCellValue("有档案面积");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 11, 11));
		
		HSSFCell cell16 = row2.createCell(12);
		cell16.setCellValue("开展县级自检面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 12, 12));
		
		HSSFCell cell17 = row2.createCell(13);
		cell17.setCellValue("设计育林面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 13, 13));
		
		HSSFCell cell18 = row2.createCell(14);
		cell18.setCellValue("核实育林面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 14, 14));
		
		HSSFCell cell19 = row2.createCell(15);
		cell19.setCellValue("抚育面积  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 15, 15));
		
		HSSFCell cell20 = row2.createCell(16);
		cell20.setCellValue("管护面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 16, 16));
		
		
		HSSFCell cell21 = row2.createCell(17);
		cell21.setCellValue("面积核实率  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 17, 17));
		
		HSSFCell cell22 = row2.createCell(18);
		cell22.setCellValue("核实面积合格率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 18, 18));
		
		HSSFCell cell23 = row2.createCell(19);
		cell23.setCellValue("上报面积合格率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 19, 19));
		
		
		HSSFCell cell24 = row2.createCell(20);
		cell24.setCellValue("当年上报面积保存 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 20, 20));
		
		HSSFCell cell25 = row2.createCell(21);
		cell25.setCellValue("造林当年上报合格 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 21, 21));
		
		HSSFCell cell26 = row2.createCell(22);
		cell26.setCellValue("全县自检合格面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 22, 22));
		
		HSSFCell cell27 = row2.createCell(23);
		cell27.setCellValue("推算核实面积");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 23, 23));
		
		HSSFCell cell28 = row2.createCell(24);
		cell28.setCellValue("推算完成合格面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 24, 24));
		
		HSSFCell cell29 = row2.createCell(25);
		cell29.setCellValue("计划任务  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 25, 25));
		
		HSSFCell cell30 = row2.createCell(26);
		cell30.setCellValue("任务完成率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 26, 26));
		
		
		HSSFCell cell31 = row2.createCell(27);
		cell31.setCellValue("作业设计率");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 27, 27));
		
		HSSFCell cell32 = row2.createCell(28);
		cell32.setCellValue("按设计施工率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 28, 28));
		
		HSSFCell cell33 = row2.createCell(29);
		cell33.setCellValue("建档率  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 29, 29));
		
		HSSFCell cell34 = row2.createCell(30);
		cell34.setCellValue("自查率");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 30, 30));
		
		HSSFCell cell35 = row2.createCell(31);
		cell35.setCellValue("自检率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 31, 31));
		
		HSSFCell cell36 = row2.createCell(32);
		cell36.setCellValue("档案率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 32, 32));
		
		HSSFCell cell37 = row2.createCell(33);
		cell37.setCellValue("抚育率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 33, 33));
		
		HSSFCell cell38 = row2.createCell(34);
		cell38.setCellValue("育林率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 34, 34));
		
		HSSFCell cell39 = row2.createCell(35);
		cell39.setCellValue("管护率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 35, 35));
		
		HSSFCellStyle style = workbook.createCellStyle();//设置样式
		HSSFFont font = workbook.createFont();//字体样式
		
		//加边框
//		style.setBorderBottom(BorderStyle.THIN);//下边框 
//		style.setBorderLeft(BorderStyle.THIN);//左边框 
//		style.setBorderRight(BorderStyle.THIN);//右边框 
//		style.setBorderTop(BorderStyle.THIN); //上边框
		
		//居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中（上下居中）
		style.setAlignment(HorizontalAlignment.CENTER);// 左右居中
		
		//设置字体
		font.setFontName("仿宋_GB2312");//字体名称
		font.setFontHeightInPoints((short) 12);//字体的大小
		font.setItalic(false);//设置是否为斜体 
		font.setBold(true);//字体加粗
		style.setWrapText(true);//自动换行
		
		style.setFont(font);//设置需要用到的字体样式
		
		List<HSSFCell> list = new ArrayList<>();
		list.add(cell0);list.add(cell1);list.add(cell2);list.add(cell3);list.add(cell4);list.add(cell5);list.add(cell6);list.add(cell7);list.add(cell8);list.add(cell9);list.add(cell10);
		list.add(cell11);list.add(cell12);list.add(cell13);list.add(cell14);list.add(cell15);list.add(cell16);list.add(cell17);list.add(cell18);list.add(cell19);list.add(cell20);
		list.add(cell21);list.add(cell22);list.add(cell23);list.add(cell24);list.add(cell25);list.add(cell26); list.add(cell27); list.add(cell28);list.add(cell29);list.add(cell30);
		list.add(cell31);list.add(cell32);list.add(cell33);list.add(cell34);list.add(cell35);list.add(cell36);list.add(cell37);list.add(cell38);list.add(cell39);
		
		//渲染单元格
		for (HSSFCell hssfCell : list) {
			hssfCell.setCellStyle(style);
		}
		
		
		//OutputStream outputStream = response.getOutputStream();
		OutputStream os=new FileOutputStream("D://EXXXX4.xls");
		
		workbook.write(os);
		
		workbook.close();
		os.close();
		
		
	}

}
