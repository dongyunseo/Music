package com.Music.Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Music.dao.View_CountDao;

public class View_Count_Command implements FlatformCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 총 방문자 수 count
		int[] visitorCounts = View_CountDao.getInstance().getAllTotalCount();

		int TODAY_VISITORS = visitorCounts[0];
		int YESTERDAY_VISITORS = visitorCounts[1];
		int WEEK_VISITORS = visitorCounts[2];
		int MONTH_VISITORS = visitorCounts[3];
		int TOTAL_VISITORS = visitorCounts[4];
		
		
        // request에 총 방문자 수 설정
        //System.out.println("command 어제 방문자 수: " + TODAY_VISITORS);
        //System.out.println("command 오늘 방문자 수: " + YESTERDAY_VISITORS);
        //System.out.println("command 어제 방문자 수: " + WEEK_VISITORS);
        //System.out.println("command 오늘 방문자 수: " + MONTH_VISITORS);
        //System.out.println("command 오늘 방문자 수: " + TOTAL_VISITORS);
		
		
        // request에 총 방문자 수 설정
        request.setAttribute("TODAY_VISITORS", TODAY_VISITORS);
        request.setAttribute("YESTERDAY_VISITORS", YESTERDAY_VISITORS);
        request.setAttribute("WEEK_VISITORS", WEEK_VISITORS);
        request.setAttribute("MONTH_VISITORS", MONTH_VISITORS);
        request.setAttribute("TOTAL_VISITORS", TOTAL_VISITORS);
        
        
        
		
        String ipAddress = request.getParameter("ip_address");
        System.out.println("아이피 주소는 : " + ipAddress);
		View_CountDao view_CountDao = new View_CountDao();
		view_CountDao.insertNewJoin(ipAddress);
		
	}

}
