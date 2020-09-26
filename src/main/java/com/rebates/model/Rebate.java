package com.rebates.model;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "rebates")
public class Rebate {
    public Rebate(){}
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;
    @Column (name = "name")
    private String name;
    @Column (name = "link")
    private String link;
    @Column (name = "rebate_type")
    private String rebateType;
    @Column (name = "value")
    private BigDecimal value;



//    @Transient
//    private long ProviderId;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLink() {
        return link;
    }
    public String getRebateType() {
        return rebateType;
    }
    public BigDecimal getValue() {
        return value;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setRebateType(String rebateType) {
        this.rebateType = rebateType;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Rebate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", rebateType='" + rebateType + '\'' +
                ", value=" + value +
                '}';
    }



}
