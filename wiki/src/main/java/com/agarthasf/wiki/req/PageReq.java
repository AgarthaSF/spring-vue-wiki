package com.agarthasf.wiki.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class PageReq {
    @NotNull(message = "current page can't be null")
    private int page;

    @NotNull(message = "Size of each page can't be null")
    @Max(value = 1000, message="size of each page could not surpass 1000")
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }


}