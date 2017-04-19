package com.ys.app.util;



public class UtilPagination {

	private int begin;
	private int end;
	private int lastPage;
	private int firstPage;
	private int maxDisplayedPages = 5;

	public UtilPagination(int currentPage, int totalListCount, int pageSize) {


		lastPage = (int) Math.ceil(totalListCount / (double) pageSize);
		firstPage = 1;

		int pageOffset = maxDisplayedPages / 2;

		

		
		if(lastPage <= maxDisplayedPages){
			begin = firstPage;
			end = lastPage;
		}
		else{
			
			//move begin and end to make sure the current page is in the middle
			//but only if the page is greated than half of the maxDisplayed (page 4 of 7 pages, then display 2,3,4,5,6 only) 
			if (currentPage > (int) Math.ceil((double) maxDisplayedPages / 2)){
				if(currentPage + pageOffset >= lastPage){
					begin = lastPage - maxDisplayedPages;
					end = lastPage;
				}else{
					begin = currentPage - pageOffset;
					end = currentPage + pageOffset;
				}
			}
			else{
				begin = firstPage;
				end = maxDisplayedPages;
			}
		}
			

	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getMaxDisplayedPages() {
		return maxDisplayedPages;
	}

	public void setMaxDisplayedPages(int maxDisplayedPages) {
		this.maxDisplayedPages = maxDisplayedPages;
	}
	
	
	
	

}
