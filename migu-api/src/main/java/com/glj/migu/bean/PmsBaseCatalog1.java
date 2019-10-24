package com.glj.migu.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guolongjie
 * @since 2019/10/23
 */

public class PmsBaseCatalog1 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;
    @Transient
    private List<PmsBaseCatalog2> list = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PmsBaseCatalog2> getList() {
        return list;
    }

    public void setList(List<PmsBaseCatalog2> list) {
        this.list = list;
    }
}
