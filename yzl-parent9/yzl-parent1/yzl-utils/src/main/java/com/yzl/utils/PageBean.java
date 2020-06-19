package com.yzl.utils;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
		//总页数
		private int totalPage;
		//总记录数
		private int totalCount;
		//当前页
		private int currentPage;
		//每页显示记录数
		private int pageSize;
		
		private List<T> list=new ArrayList<T>(); //每页要显示的所有数据 

		public int getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public List<T> getList() {
			return list;
		}

		public void setList(List<T> list) {
			this.list = list;
		}
		

}
