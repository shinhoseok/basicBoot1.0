package com.basic.pagination;

import java.text.MessageFormat;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class BasicPaginaionFormat {

    protected String firstPageLabel = "<a onclick=\"{0}({1})\" class='pprev' id='first' title='맨 앞으로'></a>";
    protected String previousPageLabel = "<a onclick=\"{0}({1})\" class='prev' id='prev' title='이전'></a>";
    protected String currentPageLabel = "<span><a class='active'>{0}</a></span>";
    protected String otherPageLabel = "<span><a>{0}</a></span>";
    protected String nextPageLabel = "<a onclick=\"{0}({1})\" class='next' id ='next' title='다음'></a>";
    protected String lastPageLabel = "<a onclick=\"{0}({1})\" class='nnext' id='last' title='맨 뒤로'></a>";

    public String paginationFormat(PaginationInfo paginationInfo, String jsFunction) {
    	
        StringBuffer stringBuffer = new StringBuffer();

        int firstPageNo = paginationInfo.getFirstPageNo();
        int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
        int totalPageCount = paginationInfo.getTotalPageCount();
        int pageSize = paginationInfo.getPageSize();
        int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
        int currentPageNo = paginationInfo.getCurrentPageNo();
        int lastPageNo = paginationInfo.getLastPageNo();

        if (totalPageCount > pageSize) {
            if (firstPageNoOnPageList > pageSize) {
                stringBuffer.append(MessageFormat.format(firstPageLabel, new Object[]{jsFunction, Integer.toString(firstPageNo)}));
                stringBuffer.append(MessageFormat.format(previousPageLabel, new Object[]{jsFunction, Integer.toString(firstPageNoOnPageList-1)}));
            } else {
                stringBuffer.append(MessageFormat.format(firstPageLabel, new Object[]{jsFunction, Integer.toString(firstPageNo)}));
                stringBuffer.append(MessageFormat.format(previousPageLabel, new Object[]{jsFunction, Integer.toString(firstPageNo)}));
            }
        }

        for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
            if (i == currentPageNo) {
                stringBuffer.append(MessageFormat.format(currentPageLabel, new Object[]{Integer.toString(i)}));
            } else {
                stringBuffer.append(MessageFormat.format(otherPageLabel, new Object[]{jsFunction, Integer.toString(i), Integer.toString(i)}));
            }
        }

        if (totalPageCount > pageSize) {
            if (lastPageNoOnPageList < totalPageCount) {
                stringBuffer.append(MessageFormat.format(nextPageLabel, new Object[]{jsFunction, Integer.toString(firstPageNoOnPageList+pageSize)}));
                stringBuffer.append(MessageFormat.format(lastPageLabel, new Object[]{jsFunction, Integer.toString(lastPageNo)}));
            } else {
                stringBuffer.append(MessageFormat.format(nextPageLabel, new Object[]{jsFunction, Integer.toString(lastPageNo)}));
                stringBuffer.append(MessageFormat.format(lastPageLabel, new Object[]{jsFunction, Integer.toString(lastPageNo)}));
            }
        }

        return stringBuffer.toString();
    }

}