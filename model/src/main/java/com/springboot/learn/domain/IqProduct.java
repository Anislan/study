package com.springboot.learn.domain;

public class IqProduct {

    private Integer id;

    private Integer sort;

    private Long timestamp;

    private String productName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "IqProduct{" +
                "id=" + id +
                ", sort=" + sort +
                ", timestamp=" + timestamp +
                ", productName='" + productName + '\'' +
                '}';
    }
}
