package com.kart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.kart.db.DBConnectionManager;
import com.kart.entity.Order;
import com.kart.entity.Product;

public class ProductDAO {
	public static boolean create(Product product,String userID,String orderID) {
		int rows=0;
		try(Connection conn = DBConnectionManager.getConnection();){
			
			
			String sql="select startwith from sequence where seq='pid'";
			PreparedStatement ps=conn.prepareStatement(sql);
			String pid=null;
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				pid=rs.getString(1);
			}
			else {
				return false;
			}
			sql="Insert into products values(?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			
			ps.setString(1,pid);
			ps.setString(2,userID.substring(0, 2)+orderID);
			ps.setString(3, userID);
			ps.setString(4,product.getName());
			ps.setInt(5,product.getPrice());
			
			rows=ps.executeUpdate();
			System.out.println("PRODUCT ADDED !");
			
			int proID=Integer.parseInt(pid);
			
			sql="update sequence set startwith=? where seq='pid'";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, proID+1);
			
			ps.executeQuery();
			
		} catch (SQLException e) {
			if(e.getMessage().contains("Duplicate")) {
				System.out.println("PROD ALREADY EXISTS!");
			}
			else {
				e.printStackTrace();
			}
		}
		return rows>0;
	}
	
	public static Vector<Order> getAllOrders(String userid){
		Vector<Order> orders=new Vector<Order>();
		try(Connection conn = DBConnectionManager.getConnection();){
			Order order=null;
			Product product=null;
			Vector<Product> products=new Vector<Product>();
			String sql="select orderid,total from orders where userid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			ResultSet rs=ps.executeQuery();
			ResultSet rs2;
			while(rs.next()) {
				products=new Vector<Product>();
				sql="select name,price from products where orderid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, rs.getString(1));
				rs2=ps.executeQuery();
				while(rs2.next()) {
					product=new Product(rs2.getInt(2), rs2.getString(1));
					products.add(product);
				}
				order=new Order(products, rs.getInt(2));
				orders.add(order);
				products=null;
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return orders;
	}
	
	public static String createOrder(Order order,String userid) {
		int rows=0;
		String oid=null;
		try(Connection conn = DBConnectionManager.getConnection();){
			
			String sql="select startwith from sequence where seq='oid'";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				oid=rs.getString(1);
			}
			else {
				return "F";
			}
			
			sql="insert into orders values (?,?,?)";
			ps=conn.prepareStatement(sql);
			
			ps.setString(1,userid.substring(0, 2)+oid);
			ps.setString(2, userid);
			ps.setInt(3,order.getTotal());
			
			rows=ps.executeUpdate();
			
			System.out.println("ORDER ADDED !");
			
			int oID=Integer.parseInt(oid);
			
			sql="update sequence set startwith=? where seq='oid'";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, oID+1);
			
			ps.executeQuery();
			
		} catch(SQLException e) {
			if(e.getMessage().contains("Duplicate")) {
				System.out.println("ORDER ALREADY EXISTS!");
			}
			else {
				e.printStackTrace();
			}
		}
		
		return oid;
		
	}
	
	public static String getOrderId(String userid) {
		String oid=null;	
		try(Connection conn = DBConnectionManager.getConnection();){
			
			String sql="select startwith from sequence where seq='oid'";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				oid=rs.getString(1);
			}
			else {
				return "F";
			}
		
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userid.substring(0,2)+oid;
	}
}
