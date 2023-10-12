package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import dao.ConnectionProperty;

import domain.Role;

@WebServlet("/roles")
public class RoleServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String select_all_role = "SELECT id, rolename FROM roles";
	String insert_role = "INSERT INTO roles(rolename) VALUES(?)";
	ArrayList<Role> roles = new ArrayList<Role>();
	String userPath;
	
	public RoleServlet() throws FileNotFoundException, IOException {
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select_all_role);
			if(rs != null) {
				roles.clear();
				while (rs.next()) {
					roles.add(new Role(rs.getLong("id"),rs.getString("rolename")));
				}
				rs.close();
				request.setAttribute("roles", roles);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		userPath = request.getServletPath();
		if("/roles".equals(userPath)){
			request.getRequestDispatcher("/view/role.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnectionProperty builder = new ConnectionProperty();
		try (Connection conn = builder.getConnection()){
		String name = request.getParameter("namerole");
		Role newRole = new Role(name);
	
		try (PreparedStatement preparedStatement = 
				conn.prepareStatement(insert_role)){
		preparedStatement.setString(
		1, newRole.getNamerole());
		int result = preparedStatement.executeUpdate();
		} catch (Exception e) {
		System.out.println(e);
		}
		} catch (Exception e) {
		System.out.println(e);
		getServletContext().getRequestDispatcher("/views/roles.jsp")
		.forward(request, response);
		}

		
		doGet(request, response);
	}
}
