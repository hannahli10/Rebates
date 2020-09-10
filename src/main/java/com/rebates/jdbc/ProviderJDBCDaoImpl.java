package com.rebates.jdbc;

import com.rebates.model.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProviderJDBCDaoImpl implements ProviderJDBCDao{
    private Logger logger = LoggerFactory.getLogger(ProviderJDBCDaoImpl.class);
    //STEP 1: Database information
    private static final String DB_URL = "jdbc:postgresql://localhost:5430/ascending-14";
    private static final String USER = "admin";
    private static final String PASS = "password";

    @Override
    public Provider save(Provider provider) {
        Provider createdProvider = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Insert statement...");
            String SQL_INSERT = "INSERT Provider (ID, NAME) VALUES (?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, provider.getId());
            preparedStatement.setString(2, provider.getName());

            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                createdProvider = provider;

        }catch(SQLException e){
            logger.error("save failed(...) for Provider throws SQLException error"+e.getMessage());
        }
        finally {
//            STEP 4: finally block used to close resources
        try{
            if(preparedStatement != null) preparedStatement.close();
            if(conn != null) conn.close();
        }catch(SQLException se) {
            logger.error("save close failed(...) for Provider throws SQLException error"+se.getMessage());
        }
    }
        return createdProvider;
}

    @Override
    public Provider update(Provider provider) {
        Provider createdProvider = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Update statement...");
            String SQL_INSERT = "UPDATE Provider (ID, NAME) VALUES (?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, provider.getId());
            preparedStatement.setString(2, provider.getName());

            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                createdProvider = provider;

        }catch(SQLException e){
            logger.error("update failed(...) for Provider throws SQLException error"+e.getMessage());
        }
        finally {
//            STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("update close failed(...) for Provider throws SQLException error"+se.getMessage());
            }
        }
        return createdProvider;
    }

    @Override
    public Boolean deleteByName(String providerName) {
        return null;
    }

    @Override
    public Boolean delete(Provider provider) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Delete statement...");
            String SQL_INSERT = "DELETE Provider (ID, NAME) VALUES (?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, provider.getId());
            preparedStatement.setString(2, provider.getName());

            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                return true;

        }catch(SQLException e){
            logger.error("delete failed(...) for Provider throws SQLException error"+e.getMessage());
        }
        finally {
//            STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("delete close failed(...) for Provider throws SQLException error"+se.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Provider> getProviders() {
        List<Provider> providers = new ArrayList();
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
            sql = "SELECT * FROM provider";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long id  = rs.getLong("id");
                String name = rs.getString("name");
                //Fill the object
                Provider provider = new Provider();
                provider.setId(id);
                provider.setName(name);
                providers.add(provider);
            }
        }catch(SQLException se) {
            logger.error("call List(...) for Provider throws SQLException error"+se.getMessage());
        }
        finally {
            //STEP 5: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
               logger.error("call close List(...) for Provider throws SQLException error\"+se.getMessage()");
            }
        }
        return providers;
    }

    @Override
    public Provider getProviderById(Long id) {
        return null;
    }
}
