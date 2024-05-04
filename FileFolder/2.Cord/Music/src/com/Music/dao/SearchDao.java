package com.Music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.Music.dto.MusicVo;
import com.Music.util.DBConn;

public class SearchDao {
	public SearchDao() {
	} 
	private static SearchDao instance = new SearchDao();
	
	public static SearchDao getInstance() {
		return instance;
	}
	//------- Title AND Singer 유사곡
		public ArrayList<MusicVo> Select_Title(String Search_Text) {
		    System.out.println("Select_Graph 접속완료 !.....");
		    ArrayList<MusicVo> Title_similar = new ArrayList<>();
		    Connection con = DBConn.getConnection();
		    String sql = "SELECT ROW_NUMBER() OVER () AS ranking, albumImgUrls, title, genreText, singer FROM \r\n" + 
		    		"(SELECT Album_img_urls AS albumImgUrls,title,genre_text AS genreText, singer FROM  Music WHERE  1 = 1 AND (title LIKE '%"+Search_Text+"%' OR Singer LIKE '%"+Search_Text+"%')AND Genre_Text IN \r\n" + 
		    		"(SELECT Genre_Text FROM Music WHERE 1 = 1 AND Genre_Text IS NOT NULL AND (title LIKE '%"+Search_Text+"%' OR Singer LIKE '%"+Search_Text+"%') AND Platform IN ('Melon') GROUP BY Genre_Text)\r\n" + 
		    		"AND Platform IN ('Melon') GROUP BY album_img_urls, title, Genre_Text, Singer\r\n" + 
		    		"ORDER BY CASE WHEN (title LIKE '%"+Search_Text+"%' OR Singer LIKE '%"+Search_Text+"%') THEN 1 ELSE 2 END, title ASC LIMIT 100) AS T";
		    System.out.println("======================================================= ");
		    System.out.println("제목 및 가수 유사곡 : " + sql);
		    Statement st = null;
		    ResultSet rs = null;
		    try {
		        st = con.createStatement();
		        rs = st.executeQuery(sql);
		        while (rs.next()) {
		            MusicVo Titlesimilar = new MusicVo(null,  rs.getInt("ranking"), rs.getString("title"), rs.getString("singer"), rs.getString("albumImgUrls"), 0,null, rs.getString("genreText"), null, 0, null);
		            Title_similar.add(Titlesimilar);
		        }
		        DBConn.close(st, rs);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return Title_similar;
		}
		//-------장르 유사곡
		public ArrayList<MusicVo> Select_Genre_Text(String Search_Text) {
		    System.out.println("Select_Graph 접속완료 !.....");
		    ArrayList<MusicVo> Genre_Text_similar = new ArrayList<>();
		    Connection con = DBConn.getConnection();
		    String sql = "SELECT ROW_NUMBER() OVER () AS ranking, albumImgUrls, title, genreText, singer FROM \r\n" + 
		    		"(SELECT Album_img_urls AS albumImgUrls,title,genre_text AS genreText, singer FROM  Music WHERE  1 = 1 AND Genre_Text IN \r\n" + 
		    		"(SELECT Genre_Text FROM Music WHERE 1 = 1 AND Genre_Text IS NOT NULL AND (title LIKE '%"+Search_Text+"%' OR Singer LIKE '%"+Search_Text+"%') AND Platform IN ('Melon') GROUP BY Genre_Text)\r\n" + 
		    		"AND Platform IN ('Melon') GROUP BY album_img_urls, title, Genre_Text, Singer\r\n" + 
		    		"ORDER BY CASE WHEN (title LIKE '%"+Search_Text+"%' OR Singer LIKE '%"+Search_Text+"%') THEN 1 ELSE 2 END, title ASC LIMIT 200) AS T";
		    System.out.println("======================================================= ");
		    System.out.println("장르 유사곡 : " + sql);
		    Statement st = null;
		    ResultSet rs = null;
		    try {
		        st = con.createStatement();
		        rs = st.executeQuery(sql);
		        while (rs.next()) {
		            MusicVo GenreTextsimilar = new MusicVo(null,  rs.getInt("ranking"), rs.getString("title"), rs.getString("singer"), rs.getString("albumImgUrls"), 0,null, rs.getString("genreText"), null, 0, null);
		            Genre_Text_similar.add(GenreTextsimilar);
		        }
		        DBConn.close(st, rs);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return Genre_Text_similar;
		}
		public void insert_SearchRecord(String Search_ip, String Search_Text) {
		    Connection conn = null;
		    PreparedStatement pstmt = null;

		    try {
		        conn = DBConn.getConnection();
		        String sql = "INSERT INTO Search_record(Search_Text, VISIT_IP, VISIT_TIME) VALUES(?, ?, NOW())";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, Search_Text);
		        pstmt.setString(2, Search_ip);
		        pstmt.executeUpdate();

		        System.out.println(Search_ip + ": 사용자의 " + Search_Text + " : 검색기록 저장");
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DBConn.close(conn, pstmt);
		    }
		}


	}