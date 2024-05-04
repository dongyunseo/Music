package com.Music.Command;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Music.dao.SearchDao;
import com.Music.dto.MusicVo;

public class SearchCommand implements FlatformCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String Search_Text = request.getParameter("searchQuery");

		if (Search_Text == null) { 
            System.out.println("검색어가 비어 있습니다."); 
        } else {
            System.out.println("검색 : " + Search_Text); 
            SearchDao SearchDao = new SearchDao();
            ArrayList<MusicVo> Title_similar = SearchDao.Select_Title(Search_Text);
            ArrayList<MusicVo> Genre_Text_similar = SearchDao.Select_Genre_Text(Search_Text);
    		request.setAttribute("Title_similar", Title_similar);
    		request.setAttribute("Genre_Text_similar", Genre_Text_similar);
    		
    	
            
    		InetAddress ia = InetAddress.getLocalHost();
    		String Search_ip =  ia.getHostAddress();
    		System.out.println("검색한 IP : " + Search_ip);
    		
            SearchDao.insert_SearchRecord(Search_ip, Search_Text);
            
        }
    }
}
