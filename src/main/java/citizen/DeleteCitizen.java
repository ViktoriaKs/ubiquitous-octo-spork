package citizen;

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
import domain.Citizen;

@WebServlet("/deletecitizens")
public class DeleteCitizen extends HttpServlet {
private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String selectAllCitizensQuery = "SELECT id, document_id, person_id, first_name, second_name, last_name, number FROM citizen ORDER BY first_name ASC";
	String select_citizen_ById = "SELECT id, document_id, person_id, first_name, second_name, last_name, number FROM citizen WHERE id = ?";
	String delete_citizen = "DELETE FROM citizen WHERE id = ?";
	ArrayList<Citizen> citizens = new ArrayList<Citizen>();
	ArrayList<Citizen> deletecitizens = new ArrayList<Citizen>();
	String userPath;

 public DeleteCitizen() throws FileNotFoundException,
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
	ResultSet rs = stmt.executeQuery(selectAllCitizensQuery);
if (rs != null) {
	citizens.clear();
	while (rs.next()) {
		citizens.add(new Citizen(
				rs.getLong("id"),
	            rs.getLong("document_id"),
	            rs.getLong("person_id"),
	            rs.getString("first_name"),
	            rs.getString("second_name"),
	            rs.getString("last_name"),
	            rs.getString("number")
	            ));
		}
	rs.close();
	request.setAttribute("citizens", citizens);
} else {
System.out.println("Ошибка загрузки citizens");
}
try (PreparedStatement preparedStatement =
conn.prepareStatement(select_citizen_ById)) {
preparedStatement.setLong(1, id);
rs = preparedStatement.executeQuery();
if (rs != null) {
deletecitizens.clear();
while (rs.next()) {
	deletecitizens.add(new
			Citizen(
					rs.getLong("id"),
                    rs.getLong("document_id"),
                    rs.getLong("person_id"),
                    rs.getString("first_name"),
                    rs.getString("second_name"),
                    rs.getString("last_name"),
                    rs.getString("number")
                    ));
}
rs.close();
request.setAttribute("citizensDelete",
		deletecitizens);
} else {
System.out.println("Ошибка загрузки citizens");

}
} catch (Exception e) {
System.out.println(e);
}
} catch (Exception e) {
System.out.println(e);
}
userPath = request.getServletPath();
if ("/deletecitizens".equals(userPath)) {
request.getRequestDispatcher("/view/Citizen/deletecitizen.jsp")
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
	conn.prepareStatement(delete_citizen)) {
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
