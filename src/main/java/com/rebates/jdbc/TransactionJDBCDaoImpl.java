package com.rebates.jdbc;

import com.rebates.model.Rebate;
import com.rebates.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionJDBCDaoImpl implements TransactionJDBCDao {
    private Logger logger = LoggerFactory.getLogger(TransactionJDBCDaoImpl.class);
    //STEP 1: Database information
    private static final String DB_URL = "jdbc:postgresql://localhost:5430/ascending-14";
    private static final String USER = "admin";
    private static final String PASS = "password";
    @Override
    public Transaction save(Transaction transaction) {
        Transaction createdTransaction = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Insert statement...");
            String SQL_INSERT = "INSERT TRANSACTION (ID,PURCHASENAME,ORDERID) VALUES (?,?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, transaction.getId());
            preparedStatement.setLong(2, transaction.getPurchaseTime());
            preparedStatement.setString(3,transaction.getOrderId()) ;


            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                createdTransaction = transaction;

        }catch(SQLException e){
            logger.error("save failed(...) for Transaction throws SQLException error"+e.getMessage());
        }
        finally {
//           STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("save close failed(...) for Transaction throws SQLException error"+se.getMessage());
            }
        }
        return createdTransaction;
    }


    @Override
    public Transaction update(Transaction transaction) {
        Transaction createdTransaction = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Insert statement...");
            String SQL_INSERT = "UPDATE TRANSACTION (ID,PURCHASENAME,ORDERID) VALUES (?,?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, transaction.getId());
            preparedStatement.setLong(2, transaction.getPurchaseTime());
            preparedStatement.setString(3,transaction.getOrderId()) ;


            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                createdTransaction = transaction;

        }catch(SQLException e){
            logger.error("update failed(...) for Transaction throws SQLException error"+e.getMessage());
        }
        finally {
//           STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("update close failed(...) for Transaction throws SQLException error"+se.getMessage());
            }
        }
        return createdTransaction;
    }

    @Override
    public Boolean deleteByName(String transactionName) {
        return null;
    }

    @Override
    public Boolean delete(Transaction transaction) {
        Transaction createdTransaction = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Delete statement...");
            String SQL_INSERT = "DELETE TRANSACTION (ID,PURCHASENAME,ORDERID) VALUES (?,?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, transaction.getId());
            preparedStatement.setLong(2, transaction.getPurchaseTime());
            preparedStatement.setString(3,transaction.getOrderId()) ;


            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                return true;

        }catch(SQLException e){
            logger.error("delete failed(...) for Transaction throws SQLException error"+e.getMessage());
        }
        finally {
//           STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("delete close failed(...) for Transaction throws SQLException error"+se.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Transaction> getTransactions() {List<Transaction> transactions = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Transaction ";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long id  = rs.getLong("id");
                Long purchaseTime =rs.getLong("purchase_time");
                String orderId = rs.getString("order_id");


                //Fill the object
                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setPurchaseTime(purchaseTime);
                transaction.setOrderId(orderId);
                transactions.add(transaction);
            }
        }catch(SQLException se) {
            logger.error("call List(...) for transaction throws SQLException error"+se.getMessage());
        }
        finally {
            //STEP 5: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                logger.error("call close List(...) for Transaction throws SQLException error\"+se.getMessage()");
            }
        }
        return transactions;
    }


    @Override
    public Rebate getTransactionById(Long id) {
        return null;
    }
}
