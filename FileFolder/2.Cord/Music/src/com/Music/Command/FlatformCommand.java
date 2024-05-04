package com.Music.Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FlatformCommand {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
