package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ConnectionProperty;
import domain.Person;
import domain.Role;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
/**
* Servlet implementation class RoleServlet_
*/
@WebServlet("/persons")
public class PersonServlet extends HttpServlet implements Servlet {
private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String select_all_person = "SELECT id, firstName, lastname, "+ "email, phone, roleid FROM persons";
			String select_all_role = "SELECT id, rolename FROM roles";
			String insert_person = "INSERT INTO persons(roleid, firstname, lastname, phone, email) VALUES(?,?,?,?,?)";
			ArrayList<Role> roles = new ArrayList<Role>();
			ArrayList<Person> persons = new ArrayList<Person>();
			String userPath;
	
	public PersonServlet() throws FileNotFoundException, IOException {
		prop = new ConnectionProperty();
	}
// Поиск должности по id
	// Поиск должности по id
	 private Role FindById(Long id, ArrayList<Role> roles) {
	 if(roles != null) {
	 for(Role r: roles) {
	 if((r.getId()).equals(id)) {
	 return r;
	 }
	 }
	 }
	 else {
	 return null;
	 }
	 return null;
	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()) {
			// Загрузка всех должностей
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
			
			// Загрузка всех сотрудников
			long id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(select_all_person);
			if(rs != null) {
			persons.clear();
			while (rs.next()) {
				id = rs.getLong("roleid");
				persons.add(new Person(rs.getLong("id"),
				rs.getString("firstName"),
				rs.getString("lastName"),
				rs.getString("email"),
				rs.getString("phone"),id,
			FindById(id, roles)
			));
			}
			rs.close();
			request.setAttribute("persons", persons);
			}
			
			} catch (Exception e) {
			System.out.println(e);
			}
			userPath = request.getServletPath();
			if("/persons".equals(userPath)){
			request.getRequestDispatcher("/view/person.jsp")
			.forward(request, response);
			}
			}
			/**
			* @see HttpServlet#doPost(HttpServletRequest request,
			HttpServletResponse response)
			*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnectionProperty builder = new ConnectionProperty();
		try (Connection conn = builder.getConnection()){
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
	        String email = request.getParameter("email");
	        String phone = request.getParameter("phone");
	        String role = request.getParameter("roleid");
	        int index1 = role.indexOf('=');
	        int index2 = role.indexOf(",");
	        String r1 = role.substring(index1 + 1, index2);
	        Long roleId = Long.parseLong((r1.trim()));

	
		try (PreparedStatement preparedStatement = conn.prepareStatement(insert_person)){
		preparedStatement.setLong(1, roleId );
		preparedStatement.setString(2, firstName);
		preparedStatement.setString(3, lastName);
		preparedStatement.setString(4, phone);
		preparedStatement.setString(5, email );
        
		int result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
				}
				} catch (Exception e) {
				System.out.println(e);
				getServletContext().getRequestDispatcher("/views/person.jsp")
				.forward(request, response);
				}
				doGet(request, response);
			}
		}