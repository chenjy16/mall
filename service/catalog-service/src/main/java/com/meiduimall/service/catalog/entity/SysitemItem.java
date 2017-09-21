package com.meiduimall.service.catalog.entity;

import java.math.BigDecimal;

public class SysitemItem {
    private Integer itemId;

    private Integer shopId;

    private Integer catId;

    private Integer brandId;

    private String shopCatId;

    private String title;

    private String subTitle;

    private String bn;

    private BigDecimal price;

    private BigDecimal costPrice;

    private BigDecimal mktPrice;

    private BigDecimal weight;

    private String imageDefaultId;

    private Integer store;

    private Integer freez;

    private Integer orderSort;

    private Integer modifiedTime;

    private Integer hasDiscount;

    private Integer isVirtual;

    private Integer isTiming;

    private Integer violation;

    private Integer isSelfshop;

    private Integer nospec;

    private Integer subStock;

    private String outerId;

    private Integer isOffline;

    private String barcode;

    private Integer disabled;

    private String usePlatform;

    private Integer isScore;

    private BigDecimal score;

    private BigDecimal rewardScore;

    private String checkStatus;

    private String supplyType;

    private Integer isbreak;

    private Integer isdirect;

    private String throughReason;

    private Integer point;

    private Integer isShowWeight;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getShopCatId() {
        return shopCatId;
    }

    public void setShopCatId(String shopCatId) {
        this.shopCatId = shopCatId == null ? null : shopCatId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn == null ? null : bn.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMktPrice() {
        return mktPrice;
    }

    public void setMktPrice(BigDecimal mktPrice) {
        this.mktPrice = mktPrice;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getImageDefaultId() {
        return imageDefaultId;
    }

    public void setImageDefaultId(String imageDefaultId) {
        this.imageDefaultId = imageDefaultId == null ? null : imageDefaultId.trim();
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getFreez() {
        return freez;
    }

    public void setFreez(Integer freez) {
        this.freez = freez;
    }

    public Integer getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(Integer orderSort) {
        this.orderSort = orderSort;
    }

    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Integer hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Integer getIsTiming() {
        return isTiming;
    }

    public void setIsTiming(Integer isTiming) {
        this.isTiming = isTiming;
    }

    public Integer getViolation() {
        return violation;
    }

    public void setViolation(Integer violation) {
        this.violation = violation;
    }

    public Integer getIsSelfshop() {
        return isSelfshop;
    }

    public void setIsSelfshop(Integer isSelfshop) {
        this.isSelfshop = isSelfshop;
    }

    public Integer getNospec() {
        return nospec;
    }

    public void setNospec(Integer nospec) {
        this.nospec = nospec;
    }

    public Integer getSubStock() {
        return subStock;
    }

    public void setSubStock(Integer subStock) {
        this.subStock = subStock;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }

    public Integer getIsOffline() {
        return isOffline;
    }

    public void setIsOffline(Integer isOffline) {
        this.isOffline = isOffline;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getUsePlatform() {
        return usePlatform;
    }

    public void setUsePlatform(String usePlatform) {
        this.usePlatform = usePlatform == null ? null : usePlatform.trim();
    }

    public Integer getIsScore() {
        return isScore;
    }

    public void setIsScore(Integer isScore) {
        this.isScore = isScore;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getRewardScore() {
        return rewardScore;
    }

    public void setRewardScore(BigDecimal rewardScore) {
        this.rewardScore = rewardScore;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public String getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType == null ? null : supplyType.trim();
    }

    public Integer getIsbreak() {
        return isbreak;
    }

    public void setIsbreak(Integer isbreak) {
        this.isbreak = isbreak;
    }

    public Integer getIsdirect() {
        return isdirect;
    }

    public void setIsdirect(Integer isdirect) {
        this.isdirect = isdirect;
    }

    public String getThroughReason() {
        return throughReason;
    }

    public void setThroughReason(String throughReason) {
        this.throughReason = throughReason == null ? null : throughReason.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getIsShowWeight() {
        return isShowWeight;
    }

    public void setIsShowWeight(Integer isShowWeight) {
        this.isShowWeight = isShowWeight;
    }
}