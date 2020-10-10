package com.rebates.jdbc;

import com.rebates.dao.ProviderDao;
import com.rebates.model.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProviderJDBCDaoImpl implements ProviderDao {
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
            String SQL_INSERT = "INSERT into Providers (NAME) VALUES (?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, provider.getName());

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
            String SQL_UPDATE = "UPDATE providers SET name = ? WHERE id = :id ";
            preparedStatement = conn.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, provider.getName());

            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                //TODO provider.setId(1234134);
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
    public boolean deleteByName(String providerName) {
        boolean providerDeleted = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Delete statement by name...");
            String SQL_DELETE = "DELETE FROM providers where NAME=?";
            preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1, providerName);
            int row = preparedStatement.executeUpdate();
            if(row > 0)
                providerDeleted = true;
        }catch (Exception e){
            logger.error("delete by name failed(...) for Provider throws SQLException error"+e.getMessage());

        }finally {
//            STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("delete by name close failed(...) for Provider throws SQLException error"+se.getMessage());
            }
        }
        return providerDeleted;

    }

    @Override
    public boolean delete(Provider provider) {
        boolean providerDeleted = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info("Connected to database...");

            //STEP 3: Execute a query
            logger.debug("Delete statement...");
           // String SQL_DELETE = "DELETE FROM PROVIDERS where id = ?";
              String SQL_DELETE = "DELETE PROVIDERS as providers where providers.id = :Id";
            preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, provider.getId());
//            preparedStatement.setString(2, provider.getName());

            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                logger.debug("A provider was deleted successfully!");
                providerDeleted = true;

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

       return providerDeleted;
    }

    @Override
    public List<Provider> findAllProviders() {
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
            sql = "SELECT * FROM providers";
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
            logger.error("List(...) for Provider throws SQLException error"+se.getMessage());
        }
        finally {
            //STEP 5: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
               logger.error("Close List(...) for Provider throws SQLException error\"+se.getMessage()");
            }
        }
        return providers;
    }

    @Override
    public Provider getProviderById(Long id) {
        Provider targetProvider = new Provider();
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
            sql = "SELECT * FROM providers";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long id1  = rs.getLong("id");
                String name = rs.getString("name");
                //Fill the object
                if (id1 == id) {
                    targetProvider.setId(id1);
                    targetProvider.setName(name);
                    break;
                }
            }
        }catch(SQLException se) {
            logger.error("Get (...) for Provider throws SQLException error"+se.getMessage());
        }
        finally {
            //STEP 5: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                logger.error("Close List(...) for Provider throws SQLException error\"+se.getMessage()");
            }
        }
        return targetProvider;
    }
}
