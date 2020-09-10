package com.rebates.jdbc;

import com.rebates.model.Rebate;
import com.rebates.model.Transaction;

import java.util.List;

public interface TransactionJDBCDao {
    Transaction save (Transaction transaction);
    Transaction update (Transaction transaction);
    Boolean deleteByName(String transactionName);
    Boolean delete (Transaction transaction);
    List<Transaction> getTransactions();
    Rebate getTransactionById(Long id);

}
