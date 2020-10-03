package com.rebates.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "orders")
public class Order {
    public Order(){}
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;
    @Column(name = "purchase_time")
    private LocalDateTime purchaseTime;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rebate_id")
    private Rebate rebate;


    public long getId() {
        return id;
    }
    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public Rebate getRebate() { return rebate; }


    public void setId(long id) {
        this.id = id;
    }
    public void setPurchaseTime(LocalDateTime purchaseTime) { this.purchaseTime=purchaseTime;}
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setRebate(Rebate rebate) {
        this.rebate = rebate;
    }



    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ",purchaseTime='"+ purchaseTime + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
