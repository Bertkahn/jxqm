package com.cloud.common.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableDto {
    private Integer current = 1;
    private Integer size = 10;
    private String sort;
    private String sortType;
    private List<Map<String, Object>> search = new ArrayList<>();

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public List<Map<String, Object>> getSearch() {
        return search;
    }

    public void setSearch(List<Map<String, Object>> search) {
        this.search = search;
    }
}
