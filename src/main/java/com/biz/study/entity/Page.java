package com.biz.study.entity;

import java.util.List;

public class Page {
    private Integer currentPage;//当前页
    private Integer pagSize = 10;//每页显示记录条数(默认每页10条)
    private Integer totaalPage;//总页数
    private List<Student> dataList;//每页显示的数据
    private Integer star;//开始的条数

    public Page() {
    }

    public Page(Integer currentPage, Integer pagSize, Integer totaalPage, List<Student> dataList, Integer star) {
        this.currentPage = currentPage;
        this.pagSize = pagSize;
        this.totaalPage = totaalPage;
        this.dataList = dataList;
        this.star = star;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPagSize() {
        return pagSize;
    }

    public void setPagSize(Integer pagSize) {
        this.pagSize = pagSize;
    }

    public Integer getTotaalPage() {
        return totaalPage;
    }

    public void setTotaalPage(Integer totaalPage) {
        this.totaalPage = totaalPage;
    }

    public List<Student> getDataList() {
        return dataList;
    }

    public void setDataList(List<Student> dataList) {
        this.dataList = dataList;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}
