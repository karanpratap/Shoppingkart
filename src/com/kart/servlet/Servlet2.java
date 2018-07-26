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

import com.kart.dao.ProductDAO;
import com.kart.entity.Order;
import com.kart.entity.Product;
import com.kart.entity.User;
import com.kart.service.UserService;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("ACTION");
		if("HOME".equals(action)) {
			request.setAttribute("MSG", request.getSession().getAttribute("USERID"));
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			
		}
		else if("SIGNOUT".equals(action))
		{
			if((String)request.getSession().getAttribute("USERID")!=null) {
				request.getSession().invalidate();
				request.setAttribute("LOG_INFO", "You have been successfully logged out!");
			}
			else {
				request.setAttribute("LOG_INFO", "You are not signed in!");
			}
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else if("CART".equals(action)) {
			String msg;
			if((msg=(String)request.getSession().getAttribute("USERID"))!=null) {
				request.setAttribute("MSG", "Hello, "+msg);
			}
			else {
				request.setAttribute("MSG", null);
			}
			RequestDispatcher rd=request.getRequestDispatcher("cart.jsp");
			rd.forward(request, response);
		}
		else if("ORDERS".equals(action)) {
			String msg;
			request.setAttribute("ORDERS_VECT", null);
			if((msg=(String)request.getSession().getAttribute("USERID"))!=null){
				request.setAttribute("MSG", "Hello, "+msg);
				Vector<Order> orders=ProductDAO.getAllOrders(msg);
				request.setAttribute("ORDERS_VECT", orders);			
			}
			else {
				request.setAttribute("MSG", null);
			}
			RequestDispatcher rd=request.getRequestDispatcher("orders.jsp");
			rd.forward(request, response);
		}
		else if("CONFIRM".equals(action)) {
			int total=0;
			String userid=(String)request.getSession().getAttribute("USERID");
			Vector<Product> products = (Vector<Product>) request.getSession().getAttribute("PRODUCTS");
			for(Product product : products) {
				total+=product.getPrice();
			}
			
			Order order = new Order(products, total);
			String oid=ProductDAO.createOrder(order,userid);
			
			for(Product product : products) 
			{
				ProductDAO.create(product,(String) request.getSession().getAttribute("USERID"),oid);
			}
			request.getSession().setAttribute("ITEMS", null);
			request.setAttribute("MSG", userid);
			request.setAttribute("LOG_INFO", "ORDER SUCCESSFUL!");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("userID");
		String pwd = request.getParameter("password");
		String action = request.getParameter("ACTION");
		
		HttpSession session = request.getSession();
		
		PrintWriter out=response.getWriter();  
		
		String userid = null;
		
		if("LOGIN".equals(action))
		{
			User user = new User(uid, pwd);
			if(UserService.authorize(user))
			{
				session.setAttribute("USERID", uid);
				request.setAttribute("MSG", uid);
				request.setAttribute("LOG", " ");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				
			}
			else
			{
				request.setAttribute("LOG", "Invalid username or password. SignUp?");
				userid="Guest";
				request.setAttribute("MSG", userid);
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				rd.forward(request, response);
			}
		}
		else if("SIGNUP".equals(action))
		{
			User user = new User(uid, pwd);
			if(UserService.create(user))
			{
				request.setAttribute("LOG_INFO", "Account '"+uid+"' Creation Successful!");
				session.setAttribute("USERID", uid);
				request.setAttribute("MSG", uid);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("LOG", "User already exists! Please choose a different User Name.");
				userid="Guest";
				request.setAttribute("MSG", userid);
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				rd.forward(request, response);
			}
		}
		else if("CHECKOUT".equals(action))
		{
			int total=0;
			if((userid=(String)session.getAttribute("USERID")) != null)
			{
				@SuppressWarnings("unchecked")
				Vector<Product> products = (Vector<Product>) session.getAttribute("PRODUCTS");
				for(Product product : products) {
					total+=product.getPrice();
				}
				Order order = new Order(products, total);
				String oid=ProductDAO.getOrderId(userid);
//				request.setAttribute("PRODUCTS", products);
//				request.getSession().setAttribute("ITEMS", null);
				request.setAttribute("ORDER_DETAILS", order);
				request.setAttribute("ORDER_ID", oid);
				request.setAttribute("USERID", userid);
				request.setAttribute("MSG", userid);
				
				RequestDispatcher rd = request.getRequestDispatcher("confirm.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("MSG", "Guest");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}
		else if("REMOVEALL".equals(action)) {
			userid=(String)session.getAttribute("USERID");
			request.setAttribute("USERID", userid);
			request.getSession().setAttribute("ITEMS", null);
			
			RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
			rd.forward(request, response);
		}
		
	}
	}
