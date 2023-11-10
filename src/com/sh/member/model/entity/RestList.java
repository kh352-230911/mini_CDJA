package com.sh.member.model.entity;

public class RestList {
    
    private int idNo;
    private String name;
    private String categoryCode;
    private String category;
    private String localCode;
    private String localName;
    private String kindshop;
    private String addr;
    private String rownum;
    public RestList() {
        super();
        // TODO Auto-generated constructor stub
    }
    public RestList(int idNo, String name, String categoryCode, String category, String localCode, String localName,
            String kindshop, String addr, String rownum) {
        super();
        this.idNo = idNo;
        this.name = name;
        this.categoryCode = categoryCode;
        this.category = category;
        this.localCode = localCode;
        this.localName = localName;
        this.kindshop = kindshop;
        this.addr = addr;
        this.rownum = rownum;
    }
    public int getIdNo() {
        return idNo;
    }
    public void setIdNo(int idNo) {
        this.idNo = idNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getLocalCode() {
        return localCode;
    }
    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }
    public String getLocalName() {
        return localName;
    }
    public void setLocalName(String localName) {
        this.localName = localName;
    }
    public String getKindshop() {
        return kindshop;
    }
    public void setKindshop(String kindshop) {
        this.kindshop = kindshop;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getRownum() {
        return rownum;
    }
    public void setRownum(String rownum) {
        this.rownum = rownum;
    }
    @Override
    public String toString() {
        return "RestList [idNo=" + idNo + ", name=" + name + ", categoryCode=" + categoryCode + ", category=" + category
                + ", localCode=" + localCode + ", localName=" + localName + ", kindshop=" + kindshop + ", addr=" + addr
                + ", rownum=" + rownum + "]";
    }
    
    
}