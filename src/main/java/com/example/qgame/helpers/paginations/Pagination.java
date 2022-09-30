package com.example.qgame.helpers.paginations;

import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pagination<T> extends IPageWrapper<T> {
    public static final int MAX_PAGE_ITEM_DISPLAY = 10;

    protected int currentNumber;
    protected Page<T> page;
    protected List<PageItem> pages;

    public Pagination(Page<T> page, String url) {
        super(page, url);

        this.page = page;
        currentNumber = page.getNumber();

        init();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    protected void init() {
        pages = new ArrayList<>();
        int start, size;
        if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
            start = 1;
            size = page.getTotalPages();
        } else {
            if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
                start = 1;
            } else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
                start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
            } else {
                start = currentNumber - MAX_PAGE_ITEM_DISPLAY / 2;
            }
            size = MAX_PAGE_ITEM_DISPLAY;
        }

        for (int i = 0; i < size; i++) {
            pages.add(new PageItem(this.url, start + i, (start + i) == currentNumber));
        }
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public int getNumber() {
        return currentNumber;
    }

    public String lastPageUrl() {
        return this.url + "?page=" + (this.page.getTotalPages() - 1);
    }

    public int getCurrentNumber() {
        return page.getNumber();
    }

    public String nextPageUrl() {
        if (hasNext()) {
            return this.url + "?page=" + (getCurrentNumber() + 1);
        }
        return null;
    }

    public String previousPageUrl() {
        if (hasPrevious()) {
            return this.url + "?page=" + (getCurrentNumber() - 1);
        }
        return null;
    }

    public String firstPageUrl() {
        return this.url;
    }

    public boolean isFirstPage() {
        return page.isFirst();
    }

    public boolean isLastPage() {
        return page.isLast();
    }

    protected void setPage(Slice<T> page) {
        this.page = (Page<T>) page;
    }

    @Override
    public Map<String, Object> getInfoResource() {
        return Map.ofEntries(
                Map.entry("size",getSize()),
                Map.entry("firstPageUrl",firstPageUrl()),
                Map.entry("lastPageUrl",lastPageUrl()),
                Map.entry("totalPages",getTotalPages()),
                Map.entry("isFirstPage",isFirstPage()),
                Map.entry("isLastPage",isLastPage()),
                Map.entry("currentPage",getCurrentNumber()),
                Map.entry("pageSize",getSize())
        );
    }
}
