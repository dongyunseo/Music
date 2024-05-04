package com.Music.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Music.Command.FlatformCommand;
import com.Music.Command.SearchCommand;
import com.Music.Command.View_Count_Command;

@WebServlet("*.do")
public class FlatformController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlatformController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("플랫폼 (doGet)");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("플랫폼 Controller (doPost)");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		FlatformCommand bCom = null;
		String viewPage = null;

		String uri = request.getRequestURI();

		String conPath = request.getContextPath();

		String com = uri.substring(conPath.length());

		if (com.equals("/Main_Page/Main.do")) {
			viewPage = "Main.jsp";
			System.out.println(viewPage);
			bCom = new View_Count_Command();
			bCom.execute(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} else if (com.equals("/Main_Page/Search.do")) {
			viewPage = "Search.jsp";
			System.out.println(viewPage);
			bCom = new SearchCommand();
			bCom.execute(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
