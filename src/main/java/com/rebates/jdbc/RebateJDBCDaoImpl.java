package com.rebates.jdbc;

import com.rebates.model.Rebate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RebateJDBCDaoImpl implements RebateJDBCDao{
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
            String SQL_INSERT = "INSERT Rebate (ID,NAME,LINK,REBETATYPE,VALUE,STARTTIME,ENDTIME) VALUES (?,?,?,?,?,?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, rebate.getId());
            preparedStatement.setString(2, rebate.getName());
            preparedStatement.setString(3,rebate.getLink()) ;
            preparedStatement.setString(4,rebate.getRebateType());
            preparedStatement.setBigDecimal(5,rebate.getValue());
            preparedStatement.setLong(6,rebate.getStartTime());
            preparedStatement.setLong(7,rebate.getEndTime());

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
            String SQL_INSERT = "UPDATE Rebate (ID,NAME,LINK,REBETATYPE,VALUE,STARTTIME,ENDTIME) VALUES (?,?,?,?,?,?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, rebate.getId());
            preparedStatement.setString(2, rebate.getName());
            preparedStatement.setString(3,rebate.getLink()) ;
            preparedStatement.setString(4,rebate.getRebateType());
            preparedStatement.setBigDecimal(5,rebate.getValue());
            preparedStatement.setLong(6,rebate.getStartTime());
            preparedStatement.setLong(7,rebate.getEndTime());

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
    public Boolean deleteByName(String rebateName) {
        return null;
    }

    @Override
    public Boolean delete(Rebate rebate) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            logger.debug("Delete statement...");
            String SQL_INSERT = "DELETE Rebate (ID,NAME,LINK,REBETATYPE,VALUE,STARTTIME,ENDTIME) VALUES (?,?,?,?,?,?,?)";;
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, rebate.getId());
            preparedStatement.setString(2, rebate.getName());
            preparedStatement.setString(3,rebate.getLink()) ;
            preparedStatement.setString(4,rebate.getRebateType());
            preparedStatement.setBigDecimal(5,rebate.getValue());
            preparedStatement.setLong(6,rebate.getStartTime());
            preparedStatement.setLong(7,rebate.getEndTime());

            int row = preparedStatement.executeUpdate();
            if (row > 0 )
                return true;

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
        return false;
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
            sql = "SELECT * FROM Rebate";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long id  = rs.getLong("id");
                String name = rs.getString("name");
                String link=rs.getNString("link") ;
                String rebateType=rs.getNString("rebate_type");
                BigDecimal value=rs.getBigDecimal("value");
                Long startTime=rs.getLong("start_time");
                Long endTime=rs.getLong("end_time");
                //Fill the object
                Rebate rebate = new Rebate();
                rebate.setId(id);
                rebate.setName(name);
                rebate.setLink(link);
                rebate.setRebateType(rebateType);
                rebate.setValue(value);
                rebate.setStartTime(startTime);
                rebate.setEndTime(endTime);
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
