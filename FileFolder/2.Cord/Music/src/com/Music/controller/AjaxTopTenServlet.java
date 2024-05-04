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
@WebServlet("/AjaxTopTenServlet")
public class AjaxTopTenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
		System.out.println("------------------------------------------------------");
	    System.out.println("AjaxTopTenServlet doPost");
	    String platform = request.getParameter("Platform");

	    ArrayList<MusicVo> platformList = new MusicDao().Select_TopTen_Img(platform);

	    StringBuffer AjaxTop_result = new StringBuffer("");
	    for (int i = 0; i < platformList.size(); i++) {
	        MusicVo music = platformList.get(i);
	        AjaxTop_result.append(music.getAlbumImgUrls()); // 이미지 URL 정보를 추가 
	        if (i < platformList.size() - 1) {
	        	AjaxTop_result.append(", "); // URL 간 구분을 위해 쉼표 추가
	        }
	    }
	    response.getWriter().write(AjaxTop_result.toString()); // 클라이언트로 응답 전송
	  
	}
}