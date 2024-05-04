package com.Music.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DBConn {
    private DBConn() {}
    private static Connection dbConn = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static Scanner sc = new Scanner(System.in);
    
    public static ResultSet statementQuery(String sql) {
        try {
            if(st == null) {
                st = dbConn.createStatement();
            }
            rs = st.executeQuery(sql);
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public static int statementUpdate(String sql) {
        int resultValue = 0;
        try {
            if(st == null) {
                st = dbConn.createStatement();
            }
            resultValue = st.executeUpdate(sql);
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {}
        System.out.println("statementUpdate : " + resultValue);
        return resultValue;
    }
    
    public static Connection getInstance() {
        if(dbConn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("DB 연결 중");
                String url = "jdbc:mysql://localhost:3306/MusicDatabase";
                String user = "root";
                String pw = "music";
                dbConn = DriverManager.getConnection(url, user, pw);
                System.out.println("DB 연결 완료");
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return dbConn;
    }
    public static void dbClose() {
        try {
            if(rs != null) rs.close();
            if(st != null) st.close(); 
            if(dbConn != null) dbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            rs = null;
            st = null;
            dbConn = null;
        }

    }
    //
    public static int inputInt() {
        return Integer.parseInt(sc.nextLine());
    }
    public static double inputDouble() {
        return Double.parseDouble(sc.nextLine());
    }
    public static String inputString() {
        return sc.nextLine();
    }
    //=================================1번째 추가================================================
    // insert delete update
    public static void close(Statement st) {
        stClose(st);
        dbClose();
    }

    // select
    public static void close(Statement st, ResultSet rs) {
        rsClose(rs);
        stClose(st);
        dbClose();
    }

    public static void close(Connection conn, PreparedStatement pstmt) {
        try {
            if (dbConn != null) {
                dbConn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pstmt = null;
            st = null;
            dbConn = null;
        }
    }
    
    
    public static void close(Connection conn, PreparedStatement pstm, ResultSet rs) {
        try {
            if (dbConn != null) {
                dbConn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pstm = null;
            rs = null;
            st = null;
            dbConn = null;
        }
    }

    // resultset
    public static void rsClose(ResultSet st) {
        if (st != null) {
            try {
                st.close();
                st = null;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // statement
    public static void stClose(Statement st) {
        if (st != null) {
            try {
                st.close();
                st = null;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static Connection getConnection() {
        if (dbConn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/MusicDatabase";
                String user = "root";
                String pw ="music";

                dbConn = DriverManager.getConnection(url, user, pw);

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

        }
        return dbConn;
    }
    public static String OdateToString(Date d) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(d);
    }
}
