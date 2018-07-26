package com.kart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String item = (String)request.getParameter("ITEM");
		
		
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Vector<String> items= (Vector<String>)session.getAttribute("ITEMS");
		
		
		if(items == null)
		{
			items = new Vector<>();
		}
		items.add(item);
		session.setAttribute("ITEMS", items);
		String user=null;
		if((String)session.getAttribute("USERID") != null) {
			user=(String)session.getAttribute("USERID");
		}
		else {
			user="Guest";
		}
		
		request.setAttribute("MSG", user);
		if(item.equals("ac")) {
			request.setAttribute("LOG_INFO", "Assassin's Creed(PC) Successfully added to Cart!");
		}
		else if(item.equals("raz")) {
			request.setAttribute("LOG_INFO", "Razer Keyboard+Mouse Successfully added to Cart!");
		}
		else if(item.equals("bmw")) {
			request.setAttribute("LOG_INFO", "BMW Automobile Successfully added to Cart!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
