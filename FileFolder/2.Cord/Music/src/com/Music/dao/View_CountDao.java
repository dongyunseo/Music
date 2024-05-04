package com.Music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.Music.util.DBConn;

public class View_CountDao {
	public View_CountDao() {
	}

	private static View_CountDao instance = new View_CountDao();

	public static View_CountDao getInstance() {
		return instance;
	}
	// 총 방문자 수
	public int[] getAllTotalCount() {
	    Connection con = DBConn.getConnection();
	    PreparedStatement pstmt = null;
	    int[] visitorCounts = new int[5]; // 배열에 어제와 오늘의 방문자 수를 저장
	    Statement st = null;
	    ResultSet rs = null;
	    
	    try {
	        String sql = "select\r\n" + 
	        		"(SELECT COUNT(DISTINCT VISIT_IP) FROM view_count WHERE DATE_FORMAT(VISIT_TIME, '%Y-%m-%d') = DATE_FORMAT(CURDATE(), '%Y-%m-%d')) as TODAY_VISITORS,\r\n" + 
	        		"(SELECT COUNT(DISTINCT VISIT_IP) FROM view_count WHERE DATE_FORMAT(VISIT_TIME, '%Y-%m-%d') = DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 DAY), '%Y-%m-%d')) as YESTERDAY_VISITORS,\r\n" + 
	        		"(SELECT COUNT(DISTINCT VISIT_IP) FROM view_count WHERE DATE_FORMAT(VISIT_TIME, '%Y-%m-%d') BETWEEN DATE_FORMAT(NOW() - INTERVAL 7 DAY, '%Y-%m-%d') AND DATE_FORMAT(NOW(), '%Y-%m-%d')) AS WEEK_VISITORS,\r\n" + 
	        		"(SELECT COUNT(DISTINCT VISIT_IP) FROM view_count WHERE DATE_FORMAT(VISIT_TIME, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m')) AS MONTH_VISITORS,\r\n" + 
	        		"(SELECT COUNT(DISTINCT VISIT_IP) FROM view_count) AS TOTAL_VISITORS";
	        st = con.createStatement();
	        rs = st.executeQuery(sql);
	        // System.out.println(sql);
	        // 결과가 있으면 배열에 저장
	        System.out.println(sql);
	        if (rs.next()) {
	            visitorCounts[0] = rs.getInt("TODAY_VISITORS");
	            visitorCounts[1] = rs.getInt("YESTERDAY_VISITORS");
	            visitorCounts[2] = rs.getInt("WEEK_VISITORS");
	            visitorCounts[3] = rs.getInt("MONTH_VISITORS");
	            visitorCounts[4] = rs.getInt("TOTAL_VISITORS");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
        return visitorCounts;
	}


	// 방문자수 insert
	public void insertNewJoin(String ipAddress) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DBConn.getConnection();
	        String sql = "INSERT INTO view_count (VISIT_IP, VISIT_TIME) VALUES (?, DATE_SUB(NOW(), INTERVAL 0 DAY))";
	        System.out.println("Insert Voew_count : " + sql);
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, ipAddress);
	        pstmt.executeUpdate();
	        System.out.println(ipAddress + ": 주소 접속 기록 완료");
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(conn, pstmt);
	    }
	}

}
