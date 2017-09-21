package com.example.demo.entity;

import java.io.Serializable;

/**
 * Created by panyunyi on 2017/9/19.
 * CUFE cs14
 */
public class ProductEntity implements Serializable {
    private int id;
    private Integer pinji;
    private Double shouyilv;
    private Integer qixian;
    private Integer jine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPinji() {
        return pinji;
    }

    public void setPinji(Integer pinji) {
        this.pinji = pinji;
    }

    public Double getShouyilv() {
        return shouyilv;
    }

    public void setShouyilv(Double shouyilv) {
        this.shouyilv = shouyilv;
    }

    public Integer getQixian() {
        return qixian;
    }

    public void setQixian(Integer qixian) {
        this.qixian = qixian;
    }

    public Integer getJine() {
        return jine;
    }

    public void setJine(Integer jine) {
        this.jine = jine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (pinji != null ? !pinji.equals(that.pinji) : that.pinji != null) return false;
        if (shouyilv != null ? !shouyilv.equals(that.shouyilv) : that.shouyilv != null) return false;
        if (qixian != null ? !qixian.equals(that.qixian) : that.qixian != null) return false;
        if (jine != null ? !jine.equals(that.jine) : that.jine != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pinji != null ? pinji.hashCode() : 0);
        result = 31 * result + (shouyilv != null ? shouyilv.hashCode() : 0);
        result = 31 * result + (qixian != null ? qixian.hashCode() : 0);
        result = 31 * result + (jine != null ? jine.hashCode() : 0);
        return result;
    }
}
