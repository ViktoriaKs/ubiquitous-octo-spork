package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import dao.ConnectionProperty;
import domain.Role;

@WebServlet("/deleterole")
public class DeleteRoleServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

ConnectionProperty prop;
String select_all_role = "SELECT id, rolename FROM roles ORDER BY rolename ASC";
String select_role_ById = "SELECT id, rolename FROM roles WHERE id = ?";
String delete_role = "DELETE FROM roles WHERE id = ?";
ArrayList<Role> roles = new ArrayList<Role>();
ArrayList<Role> deleteroles = new ArrayList<Role>();
String userPath;

 public DeleteRoleServlet() throws FileNotFoundException,
IOException {
 super();
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
	Long id = null;
	if (strId != null) {
	id = Long.parseLong(strId);
	}
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(select_all_role);
if (rs != null) {
	roles.clear();
	while (rs.next()) {
		roles.add(new Role(
				rs.getLong("id"),
				rs.getString("rolename")));
		}
	rs.close();
	request.setAttribute("roles", roles);
} else {
System.out.println("Ошибка загрузки role");
}
try (PreparedStatement preparedStatement =
conn.prepareStatement(select_role_ById)) {
preparedStatement.setLong(1, id);
rs = preparedStatement.executeQuery();
if (rs != null) {
deleteroles.clear();
while (rs.next()) {
deleteroles.add(new
Role(
		rs.getLong("id"), 
		rs.getString("rolename")));
}
rs.close();
request.setAttribute("rolesDelete",
deleteroles);
} else {
System.out.println("Ошибка загрузки role");

}
} catch (Exception e) {
System.out.println(e);
}
} catch (Exception e) {
System.out.println(e);
}
userPath = request.getServletPath();
if ("/deleterole".equals(userPath)) {
request.getRequestDispatcher("/view/deleterole.jsp")
.forward(request, response);
}
}

protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
	ConnectionProperty builder = new ConnectionProperty();
try (Connection conn = builder.getConnection()) {
	Long id = Long.parseLong(request.getParameter("id"));
try (PreparedStatement preparedStatement =
	conn.prepareStatement(delete_role)) {
	preparedStatement.setLong(1, id);
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
