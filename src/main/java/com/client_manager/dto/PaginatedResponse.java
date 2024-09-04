package com.client_manager.dto;

import java.util.List;

public class PaginatedResponse<T> {
	private List<T> content;
	private int currentPage;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
	
	
	public PaginatedResponse(List<T> content, int currentPage, int pageSize, long totalElements, int totalPages,
			boolean last) {
		this.content = content;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}


	public List<T> getContent() {
		return content;
	}


	public void setContent(List<T> content) {
		this.content = content;
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


	public long getTotalElements() {
		return totalElements;
	}


	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public boolean isLast() {
		return last;
	}


	public void setLast(boolean last) {
		this.last = last;
	}
}
