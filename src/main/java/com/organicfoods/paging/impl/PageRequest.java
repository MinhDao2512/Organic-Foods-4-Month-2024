package com.organicfoods.paging.impl;

import com.organicfoods.paging.Pageble;
import com.organicfoods.sorting.Sorter;

public class PageRequest implements Pageble{

	private Integer page;
	private Integer itemsPerPage;
	private Sorter sorter;

	public PageRequest(Integer page, Integer itemsPerPage, Sorter sorter) {
		super();
		this.page = page;
		this.itemsPerPage = itemsPerPage;
		this.sorter = sorter;
	}

	@Override
	public Integer getOffset() {
		if(this.page != null && this.itemsPerPage != null) {
			return (this.page-1)*this.itemsPerPage;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return this.itemsPerPage;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Sorter getSorter() {
		return this.sorter;
	}
	
	

}
