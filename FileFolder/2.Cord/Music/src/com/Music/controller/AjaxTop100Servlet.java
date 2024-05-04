package com.Music.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Music.dao.MusicDao;
import com.Music.dto.MusicVo;

/**
 * Servlet implementation class AjaxTopTenServlet
 */
@WebServlet("/AjaxTop100Servlet")
public class AjaxTop100Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/plain; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
		System.out.println("------------------------------------------------------");
	    System.out.println("AjaxTop100_Servlet doPost");
	    String platform = request.getParameter("Platform");

	    ArrayList<MusicVo> Top100List = new MusicDao().Select_Top100(platform);

	    StringBuffer AjaxTop100_result = new StringBuffer("");
	    for (int i = 0; i < Top100List.size(); i++) {
	        MusicVo music = Top100List.get(i);
	        AjaxTop100_result.append(music.getDay() + ", " + 
	        						 music.getRanking() + ", " + 
	        						 music.getTitle() + ", " + 
	        						 music.getSinger() + ", " + 
	        						 music.getAlbumImgUrls() + ", " + 
	        						 music.getReleaseDate() + ", " +
	        						 music.getGenreText() + ", " + 
	        						 music.getView_Count() + ", " + 
	        						 music.getCilck_URL());
	        if (i < Top100List.size() - 1) {
	        	AjaxTop100_result.append(", "); // URL 간 구분을 위해 쉼표 추가
	        }
	        
	    }
	    response.getWriter().write(AjaxTop100_result.toString()); // 클라이언트로 응답 전송
	  
	}
}