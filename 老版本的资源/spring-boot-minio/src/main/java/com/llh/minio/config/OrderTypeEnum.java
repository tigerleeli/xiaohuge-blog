package com.llh.minio.config;


/**
 * @author admin
 */

public enum OrderTypeEnum {
    /**
     * 采购单
     */
    PURCHASE(1, "采购单"),

    /**
     * 销售单
     */
    SALE(2, "销售单");

    private Integer type;

    private String name;

    OrderTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public boolean eq(Integer value) {
        if (value == null) {
            return false;
        }
        return value.equals(this.getType());
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
