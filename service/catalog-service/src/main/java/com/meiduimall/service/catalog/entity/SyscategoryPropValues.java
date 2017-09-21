package com.meiduimall.service.catalog.entity;

public class SyscategoryPropValues {
    private Integer propValueId;

    private Integer propId;

    private String propValue;

    private String propImage;

    private Integer orderSort;

    public Integer getPropValueId() {
        return propValueId;
    }

    public void setPropValueId(Integer propValueId) {
        this.propValueId = propValueId;
    }

    public Integer getPropId() {
        return propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue == null ? null : propValue.trim();
    }

    public String getPropImage() {
        return propImage;
    }

    public void setPropImage(String propImage) {
        this.propImage = propImage == null ? null : propImage.trim();
    }

    public Integer getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(Integer orderSort) {
        this.orderSort = orderSort;
    }
}