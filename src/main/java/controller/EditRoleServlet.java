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
import dao.ConnectionProperty;

import domain.Role;

/**
* Servlet implementation class EditRoleServlet
*/
@WebServlet("/editrole")
public class EditRoleServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String select_all_role = "SELECT id, rolename FROM roles ORDER BY rolename ASC";
	String select_role_ById = "SELECT id, rolename FROM roles WHERE id = ?";
	String edit_role = "UPDATE roles SET rolename = ? WHERE id = ?";
	ArrayList<Role> roles = new ArrayList<Role>();
	ArrayList<Role> editroles = new ArrayList<Role>();
	String userPath;

 /**
 * @see HttpServlet#HttpServlet()
 */
 public EditRoleServlet() throws FileNotFoundException, IOException
{
 super();
 // TODO Auto-generated constructor stub
 prop = new ConnectionProperty();
 }
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
	throws ServletException, IOException {
	response.setContentType("text/html");
	ConnectionProperty builder = new ConnectionProperty();
	
// Загрузка всех должностей
try (Connection conn = builder.getConnection()) {
String strId = request.getParameter("id");
Long id = null; // id редактируемой должности
if(strId != null) {
id = Long.parseLong(strId);
}
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(select_all_role);
if(rs != null) {
roles.clear();
while (rs.next()) {
roles.add(new Role(
		rs.getLong("id"),
		rs.getString("rolename")
));
}
rs.close();
request.setAttribute("roles", roles);
}

else
{
System.out.println("Ошибка загрузки role");
}
try (PreparedStatement preparedStatement =
conn.prepareStatement(select_role_ById)) {
preparedStatement.setLong(1, id);
rs = preparedStatement.executeQuery();
if(rs != null) {editroles.clear();
while (rs.next()) {
editroles.add(new
Role(
		rs.getLong("id"), 
		rs.getString("rolename")));
}
rs.close();
request.setAttribute("rolesEdit",editroles);
}
else
{
System.out.println("Ошибка загрузки role");
}
} catch (Exception e) {
System.out.println(e);
}
} catch (Exception e) {
System.out.println(e);
}
userPath = request.getServletPath();
if("/editrole".equals(userPath)){
request.getRequestDispatcher("/view/editrole.jsp")
.forward(request, response);
}
}
protected void doPost(HttpServletRequest request,
HttpServletResponse response) 
		throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();
		try (Connection conn = builder.getConnection()) {
		String strId = request.getParameter("id");
		Long id = null;
		if(strId != null) {
		id = Long.parseLong(strId);
		}
		String namerole = request.getParameter("namerole");
		try (PreparedStatement preparedStatement = conn.prepareStatement(edit_role)) {
		preparedStatement.setString(1, namerole);
		
		preparedStatement.setLong(2, id);
		int result = preparedStatement.executeUpdate();
		} catch (Exception e) {
		System.out.println(e);
		}
		} catch (Exception e) {
		System.out.println(e);
		}
		doGet(request, response);
}
}