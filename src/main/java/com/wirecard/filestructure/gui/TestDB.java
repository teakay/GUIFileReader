package com.wirecard.filestructure.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args){
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:sample.db";
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try{
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
