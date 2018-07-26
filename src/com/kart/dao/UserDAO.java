package com.kart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kart.db.DBConnectionManager;
import com.kart.entity.User;

public class UserDAO {
	
	public boolean create(User user) {
		int rows=0;
		try(Connection conn = DBConnectionManager.getConnection();){
			
			
			String sql="Insert into user values(?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ps.setString(1,user.getUserid());
			ps.setString(2,user.getPassword());
			
			rows=ps.executeUpdate();
			System.out.println("USER REGISTERED!");
			
		} catch (SQLException e) {
			if(e.getMessage().contains("Duplicate")) {
				System.out.println("USER ALREADY EXISTS!");
			}
			else {
				e.printStackTrace();
			}
		}
		return rows>0;
	}
	
	public boolean authorize(User user) {
		try(Connection conn = DBConnectionManager.getConnection();){
			String sql="select userid,password from user where userid=? and password=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getPassword());
			
			ResultSet rs=ps.executeQuery();
			try{
				if(rs.next()) {
					return true;
				}
				else {
					return false;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
}
