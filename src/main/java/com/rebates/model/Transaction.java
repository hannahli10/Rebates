package com.rebates.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "transactions")
public class Transaction {
    public Transaction(){}
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;
    @Column(name = "purchase_time")
    private LocalDateTime purchaseTime;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "amount")
    private BigDecimal amount;


    public long getId() {
        return id;
    }
    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }
    public String getOrderId() {
        return orderId;
    }
    public BigDecimal getAmount() {
        return amount;
    }



    public void setId(long id) {
        this.id = id;
    }
    public void setPurchaseTime(LocalDateTime purchaseTime) { this.purchaseTime=purchaseTime;}
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ",purchaseTime='"+ purchaseTime + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount=" + amount +
                '}';
    }

}
