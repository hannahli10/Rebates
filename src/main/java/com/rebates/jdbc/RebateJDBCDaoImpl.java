package com.rebates.jdbc;

import com.rebates.dao.RebateDao;
import com.rebates.model.Rebate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RebateJDBCDaoImpl implements RebateDao {
    private Logger logger = LoggerFactory.getLogger(RebateJDBCDaoImpl.class);
    //STEP 1: Database information
    private static final String DB_URL = "jdbc:postgresql://localhost:5430/ascending-14";
    private static final String USER = "admin";
    private static final String PASS = "password";
    @Override
    public Rebate save(Rebate rebate) {
        Rebate createdRebate = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Insert statement...");
            String SQL_INSERT = "INSERT into Rebates (NAME,LINK,REBETA_TYPE,VALUE) VALUES (?,?,?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, rebate.getName());
            preparedStatement.setString(2,rebate.getLink()) ;
            preparedStatement.setString(3,rebate.getRebateType());
            preparedStatement.setBigDecimal(4,rebate.getValue());



            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                createdRebate = rebate;

        }catch(SQLException e){
            logger.error("save failed(...) for Rebate throws SQLException error"+e.getMessage());
        }
        finally {
//           STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("save close failed(...) for Rebate throws SQLException error"+se.getMessage());
            }
        }
        return createdRebate;
    }


    @Override
    public Rebate update(Rebate rebate) {
        Rebate createdRebate = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Update statement...");
            String SQL_INSERT = "UPDATE Rebates (NAME,LINK,REBETA_TYPE,VALUE) VALUES (?,?,?,?)";
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, rebate.getName());
            preparedStatement.setString(2,rebate.getLink());
            preparedStatement.setString(3,rebate.getRebateType());
            preparedStatement.setBigDecimal(4,rebate.getValue());



            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                createdRebate = rebate;

        }catch(SQLException e){
            logger.error("update failed(...) for Rebate throws SQLException error"+e.getMessage());
        }
        finally {
//           STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("update close failed(...) for Rebate throws SQLException error"+se.getMessage());
            }
        }
        return createdRebate;
    }


    @Override
    public boolean deleteByName(String rebateName) {
        boolean rebateDeleted = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Delete statement by name...");
            String SQL_INSERT = "DELETE FROM Rebates where Name=?";
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, rebateName);
            int row = preparedStatement.executeUpdate();
            if(row > 0)
                rebateDeleted = true;
        }catch (Exception e){
            logger.error("delete by name failed(...) for Rebate throws SQLException error"+e.getMessage());

        }finally {
//            STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("delete by name close failed(...) for Rebate throws SQLException error"+se.getMessage());
            }
        }
        return rebateDeleted;
    }

    @Override
    public boolean delete(Rebate rebate) {
        boolean rebateDeleted = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Delete statement...");
            String SQL_INSERT = "DELETE Rebates where rebates.id = :Id";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, rebate.getName());
            preparedStatement.setString(2,rebate.getLink()) ;
            preparedStatement.setString(3,rebate.getRebateType());
            preparedStatement.setBigDecimal(4,rebate.getValue());



            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                rebateDeleted = true;

        }catch(SQLException e){
            logger.error("delete failed(...) for Rebate throws SQLException error"+e.getMessage());
        }
        finally {
//           STEP 4: finally block used to close resources
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }catch(SQLException se) {
                logger.error("delete close failed(...) for Rebate throws SQLException error"+se.getMessage());
            }
        }
        return rebateDeleted;
    }



    @Override
    public List<Rebate> getRebates() {
        List<Rebate> rebates = new ArrayList();
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
            sql = "SELECT * FROM Rebates";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long id  = rs.getLong("id");
                String name = rs.getString("name");
                String link=rs.getNString("link") ;
                String rebateType=rs.getNString("rebate_type");
                BigDecimal value=rs.getBigDecimal("value");
                //Fill the object
                Rebate rebate = new Rebate();
                rebate.setId(id);
                rebate.setName(name);
                rebate.setLink(link);
                rebate.setRebateType(rebateType);
                rebate.setValue(value);
                rebates.add(rebate);
            }
        }catch(SQLException se) {
            logger.error("call List(...) for Rebate throws SQLException error"+se.getMessage());
        }
        finally {
            //STEP 5: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                logger.error("call close List(...) for Rebate throws SQLException error\"+se.getMessage()");
            }
        }
        return rebates;
    }

    @Override
    public Rebate getRebateById(Long id) {
        return null;
    }
}
