package com.visualpath.hadoop.loganalysis;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.visualpath.hadoop.loganalysis.dto.BrowsersInfo;
import com.visualpath.hadoop.loganalysis.dto.MonthlyHits;
import com.visualpath.hadoop.loganalysis.java.HBaseConnector;

/**
 * Servlet implementation class LogAnalysis
 */
@WebServlet("/LogAnalysis")
public class LogAnalysis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogAnalysis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("haiii");
		HBaseConnector connector = new HBaseConnector();
		BrowsersInfo browsersInfo = new BrowsersInfo();
		connector.consumingSplunkbrowsersInfo(browsersInfo);
		connector.consumingWwwbrowsersInfo(browsersInfo);
		MonthlyHits monthlyHits = new MonthlyHits();
		connector.splunkMonthlyHits(monthlyHits);
		connector.wwMonthlyHits(monthlyHits);
		try{ 
			request.setAttribute("browserDeatils", browsersInfo);
			request.setAttribute("hits", monthlyHits);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
			rd.forward(request, response);
	        }  
	        catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
