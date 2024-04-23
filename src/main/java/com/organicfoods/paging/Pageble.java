package com.organicfoods.paging;

import com.organicfoods.sorting.Sorter;

public interface Pageble {
	Integer getOffset();
	Integer getLimit();
	Integer getPage();
	Sorter getSorter();
}
