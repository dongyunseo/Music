package com.Music.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.Music.dto.MusicVo;
import com.Music.util.DBConn;

public class MusicDao {
	public MusicDao() {
	} 
	private static MusicDao instance = new MusicDao();
	
	public static MusicDao getInstance() {
		return instance;
	}
	// -------------------------- 각 플랫폼 별 그래프추출------------------
	public ArrayList<MusicVo> Select_Graph(String Platform) {
	    System.out.println("Select_Graph 접속완료 !.....");
	    ArrayList<MusicVo> Graph_Dto = new ArrayList<>();
	    Connection con = DBConn.getConnection();
	    String sql = "SELECT Day, Ranking,Title FROM Music \r\n" + 
	    		"WHERE Platform =  '" + Platform + "'  \r\n" + 
	    		"AND STR_TO_DATE(SUBSTR(Day, 1, 8), '%Y%m%d') >= DATE_SUB((SELECT STR_TO_DATE(MAX(DAY), '%Y%m%d') FROM Music), INTERVAL 63 DAY) \r\n" + 
	    		"AND STR_TO_DATE(SUBSTR(Day, 1, 8), '%Y%m%d') <= DATE_ADD(DATE_SUB((SELECT STR_TO_DATE(MAX(DAY), '%Y%m%d') FROM Music), INTERVAL 7 DAY), INTERVAL 1 WEEK) \r\n" + 
	    		"AND Ranking < 6 ORDER BY Day DESC, Ranking";
	    System.out.println("======================================================= ");
	    System.out.println("그래프 SQL : " + sql);
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        st = con.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next()) {
	            MusicVo GraphDto = new MusicVo(rs.getString("Day"), rs.getInt("Ranking"), rs.getString("Title"), null, null, 0, null, null, null, 0, null);
	            Graph_Dto.add(GraphDto);
	        }
	        DBConn.close(st, rs);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return Graph_Dto;
	}
	// -------------------------- Top10 img ------------------
	public ArrayList<MusicVo> Select_TopTen_Img(String Platform) {
	    System.out.println("Select_TopTen_Img 접속완료 !.....");
	    ArrayList<MusicVo> topTenDtoList = new ArrayList<>();
	    Connection con = DBConn.getConnection();
	    String sql = "SELECT album_img_urls FROM Music WHERE 1=1 AND Platform = '" + Platform + "' and Day = (Select max(Day) from Music where Platform = '" + Platform + "') and Ranking < 11  order by Ranking asc";
	    System.out.println("======================================================= ");
	    System.out.println("Top10 Img Sql문 : "+ sql);
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        st = con.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next()) {
	            MusicVo topTenDto = new MusicVo(null, 0, null, null, rs.getString("album_img_urls"), 0, null, null, null, 0, null);
	            topTenDtoList.add(topTenDto);
	        }
	        DBConn.close(st, rs);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return topTenDtoList;
	}
	// -------------------------- Top100 정보------------------
		public ArrayList<MusicVo> Select_Top100(String Platform) {
		    System.out.println("Select_Top100_List 접속완료 !.....");
		    ArrayList<MusicVo> Top100_DtoList = new ArrayList<>();
		    Connection con = DBConn.getConnection();
		    String sql = "SELECT Day, Ranking, Title, Singer, album_img_urls, release_date, genre_text, view_Count, click_Url  FROM Music WHERE 1=1 AND Platform = '" + Platform + "'  and Day = (Select max(Day) from Music where Platform =  '" + Platform + "') order by Ranking asc LIMIT 100";
		    System.out.println("======================================================= ");
		    System.out.println("Top 100위 Sql문 : "+ sql);
		    Statement st = null;
		    ResultSet rs = null;
		    try {
		        st = con.createStatement();
		        rs = st.executeQuery(sql);
		        while (rs.next()) {
		            MusicVo top100Dto = new MusicVo(rs.getString("Day"), rs.getInt("Ranking"), rs.getString("Title"), rs.getString("Singer"), rs.getString("album_img_urls"), 0, rs.getDate("release_date"), rs.getString("genre_text"), null, rs.getInt("view_Count"), rs.getString("click_Url"));
		            Top100_DtoList.add(top100Dto);
		        }
		        DBConn.close(st, rs);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return Top100_DtoList;
		}
	// 장르별 그래프 
	public ArrayList<MusicVo> Select_Genre_Graph(String Platform) {
	    System.out.println("Genre_Select_Graph 접속완료 !.....");
	    ArrayList<MusicVo> Gener_Graph_Dto = new ArrayList<>();
	    Connection con = DBConn.getConnection();
	    System.out.println(Platform);
	    String sql = null;
	    if (Platform.equals("Youtube")) {
	        System.out.println(Platform +" 입니다.");
	        sql = "SELECT substr(a.Day,1,6) as Day, Count(b.genre_text) as Ranking, b.genre_text as Title FROM Music a, Genre_List b \r\n" + 
	              "WHERE 1=1 AND a.title = b.title AND Platform = '"+Platform+"' AND substr(a.Day,1,4) = '2023'\r\n" + 
	              "GROUP BY substr(a.Day,1,6), b.genre_text HAVING Count(b.genre_text) >= 9 ORDER BY substr(a.Day,1,6) DESC";
	    } else if (Platform.equals("Circle")) {
	        System.out.println(Platform +" 입니다.");
	        sql = "SELECT substr(a.Day,1,6) as Day, Count(b.genre_text) as Ranking, b.genre_text as Title FROM Music a, Genre_List b \r\n" + 
	              "WHERE 1=1 AND a.title = b.title AND Platform = '"+Platform+"' AND substr(a.Day,1,4) = '2023'\r\n" + 
	              "GROUP BY substr(a.Day,1,6), b.genre_text HAVING Count(b.genre_text) >= 16 ORDER BY substr(a.Day,1,6) DESC";
	    } else if (Platform.equals("Melon") || Platform.equals("Genie")) {
	        System.out.println("Youtube 아닙니다.");
	    	sql = "select substr(Day,1,6) as Day, \r\n" + 
		    		"       count(Genre_Text) as Ranking,\r\n" + 
		    		"           CASE \r\n" + 
		    		"        WHEN Genre_Text = '발라드' OR Genre_Text = '발라드 국내드라마' THEN '발라드'\r\n" + 
		    		"        WHEN Genre_Text = '발라드' OR Genre_Text = '발라드 국내영화' THEN '발라드'\r\n" + 
		    		"        WHEN Genre_Text = '발라드' OR Genre_Text = '발라드 인디음악' THEN '발라드'\r\n" + 
		    		"        ELSE Genre_Text \r\n" + 
		    		"    END as Title\r\n" + 
		    		"  from Music \r\n" + 
		    		" where 1=1 \r\n" + 
		    		"   and Platform =  '"+Platform+"'" +
		    		"   and Genre_Text is not null\r\n" + 
		    		"   and substr(Day,1,4) = '2023'\r\n" + 
		    		" group by substr(Day,1,6), \r\n" + 
		    		"              CASE \r\n" + 
		    		"        WHEN Genre_Text = '발라드' OR Genre_Text = '발라드 국내드라마' THEN '발라드'\r\n" + 
		    		"        WHEN Genre_Text = '발라드' OR Genre_Text = '발라드 국내영화' THEN '발라드'\r\n" + 
		    		"        WHEN Genre_Text = '발라드' OR Genre_Text = '발라드 인디음악' THEN '발라드'\r\n" + 
		    		"        ELSE Genre_Text\r\n" + 
		    		"    END \r\n" + 
		    		"HAVING count(Genre_Text) >= 16\r\n" + 
		    		"order by substr(Day,1,6) desc, count(Genre_Text) desc";
	    } else {
	        // 다른 플랫폼인 경우의 처리 (추가로 필요한 작업을 여기에 추가)
	    }
	    System.out.println("======================================================= ");
	    System.out.println("장르별 그래프 : " +sql);
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        st = con.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next()) {
	            MusicVo GraphDto = new MusicVo(rs.getString("Day"), rs.getInt("Ranking"), rs.getString("Title"), null, null, 0, null, null, null, 0, null);
	            Gener_Graph_Dto.add(GraphDto);
	        }
	        DBConn.close(st, rs);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return Gener_Graph_Dto;
	}
}

