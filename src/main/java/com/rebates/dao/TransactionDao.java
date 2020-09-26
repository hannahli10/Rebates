package com.rebates.dao;

import com.rebates.model.Transaction;

import java.util.List;

public interface TransactionDao {
    Transaction save (Transaction transaction);
    Transaction update (Transaction transaction);
    boolean delete (Transaction transaction);
    List<Transaction> getTransactions();
    Transaction getTransactionById(Long id);

}
