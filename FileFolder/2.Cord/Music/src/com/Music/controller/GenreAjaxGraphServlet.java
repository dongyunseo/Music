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
@WebServlet("/GenreAjaxGraphServlet")
public class GenreAjaxGraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/plain; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
		System.out.println("------------------------------------------------------");
	    System.out.println("GenreAjaxGraphServlet doPost");
	    String platform = request.getParameter("Platform");

	    ArrayList<MusicVo> GraphList = new MusicDao().Select_Genre_Graph(platform);

	    StringBuffer Genre_AjaxGraph_result = new StringBuffer("");
	    for (int i = 0; i < GraphList.size(); i++) {
	        MusicVo music = GraphList.get(i);
	        Genre_AjaxGraph_result.append(music.getDay() + ", " + music.getRanking() + ", " + music.getTitle());
	        if (i < GraphList.size() - 1) {
	        	Genre_AjaxGraph_result.append(", "); // URL 간 구분을 위해 쉼표 추가
	        }
	    }
	    response.getWriter().write(Genre_AjaxGraph_result.toString()); // 클라이언트로 응답 전송
	  
	}
}