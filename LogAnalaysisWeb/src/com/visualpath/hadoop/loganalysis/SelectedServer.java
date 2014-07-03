package com.visualpath.hadoop.loganalysis;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.visualpath.hadoop.loganalysis.dto.BrowsersInfo;
import com.visualpath.hadoop.loganalysis.dto.MonthlyHits;
import com.visualpath.hadoop.loganalysis.dto.SelectedSereverHits;
import com.visualpath.hadoop.loganalysis.java.HBaseConnector;
import com.visualpath.hadoop.loganalysis.utill.LogAnalysisUtill;

/**
 * Servlet implementation class SelectedServer
 */
@WebServlet("/SelectedServer")
public class SelectedServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectedServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get"+request.getParameter("param"));
		String server = request.getParameter("param");
		HBaseConnector connector = new HBaseConnector();
		BrowsersInfo browsersInfo = new BrowsersInfo();
		SelectedSereverHits selectedServer = new SelectedSereverHits();
		LogAnalysisUtill logAnalysisUtill = new LogAnalysisUtill();
		if(server !=null && server.equals("splunk")){
			//selectedServer.setSelServer(server);
			System.out.println("value"+request.getParameter("param"));
			connector.consumingSplunkbrowsersInfo(browsersInfo);
			connector.slectedServerMonthlyHits(selectedServer);
			
		}else if(server !=null && server.equals("www")){
			//selectedServer.setSelServer(server);
			System.out.println("value"+request.getParameter("param"));
			connector.consumingWwwbrowsersInfo(browsersInfo);
			connector.slectedServerMonthlyHits(selectedServer);
			
		}else if(server !=null && server.equals("apacheServer")){
			//selectedServer.setSelServer(server);
			System.out.println("value"+request.getParameter("param"));
			connector.consumingApacheServerLogData(browsersInfo);
			
		}
		try {
			String name = getServerName(server);
			System.out.println(name);
			selectedServer.setSelServer(name);
			request.setAttribute("selServer", name);
			request.setAttribute("browserDeatils", browsersInfo);
			request.setAttribute("hits", selectedServer);
			getServletConfig().getServletContext()
					.getRequestDispatcher("/jsp/selectedSerever.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doPost(request, response,browsersInfo,selectedServer);
	}

	private String getServerName(String server) {
		String name = null;
		if(server.equalsIgnoreCase("apacheServer")){
			name = "Apache Server";
		}if(server.equalsIgnoreCase("www")){
			name = "WWW";
		}if(server.equalsIgnoreCase("splunk")){
			name = "Splunk";
		}if(server.equalsIgnoreCase("websphereServer")){
			name = "Web Sphere";
		}if(server.equalsIgnoreCase("tomactServer")){
			name = "Tomcat";
		}
		return name;
	}

	/**
	 * @param selectedServer 
	 * @param browsersInfo 
	 * @param logAnalysisUtill 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response, BrowsersInfo browsersInfo,
			SelectedSereverHits selectedServer) throws ServletException, IOException {
		System.out.println("post" + request.getParameter("param"));
		try {
			request.setAttribute("selectedServer", selectedServer);
			request.setAttribute("browserDeatils", browsersInfo);
			request.setAttribute("hits", selectedServer);
			getServletConfig().getServletContext()
					.getRequestDispatcher("/jsp/selectedSerever.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
