package com.example.qgame.helpers.paginations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public abstract class IPageWrapper<T> {
    private Slice<T> page;
    protected String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public IPageWrapper(Slice<T> page, String url) {
        this.page = page;
        this.url = url;
    }


    public List<T> getContent() {
        return page.getContent();
    }

    public int getSize() {
        return page.getSize();
    }

    protected abstract void setPage(Slice<T> page);

    public boolean hasPrevious() {
        return page.hasPrevious();
    }

    public boolean hasNext() {
        return page.hasNext();
    }

    public boolean hasPages() {
        return page.hasContent();
    }

    public abstract String nextPageUrl();

    public abstract String previousPageUrl();
}
