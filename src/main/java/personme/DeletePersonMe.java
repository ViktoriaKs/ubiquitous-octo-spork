package personme;
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
import domain.PersonMe;

@WebServlet("/deletepersonsme")
public class DeletePersonMe extends HttpServlet {
private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String selectAllPersonsQuery = "SELECT id, shifer, inn, type, data FROM person_me ORDER BY shifer ASC";
	String select_personme_ById = "SELECT id, shifer, inn, type, data FROM person_me WHERE id = ?";
	String delete_personme = "DELETE FROM person_me WHERE id = ?";
	ArrayList<PersonMe> persons = new ArrayList<PersonMe>();
	ArrayList<PersonMe> deletepersons = new ArrayList<PersonMe>();
	String userPath;

 public DeletePersonMe() throws FileNotFoundException,
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
	ResultSet rs = stmt.executeQuery(selectAllPersonsQuery);
if (rs != null) {
	persons.clear();
	while (rs.next()) {
		persons.add(new PersonMe(
				rs.getLong("id"),
	            rs.getString("shifer"),
	            rs.getString("inn"),
	            rs.getString("type"),
	            rs.getDate("data")
	            ));
		}
	rs.close();
	request.setAttribute("persons", persons);
} else {
System.out.println("Ошибка загрузки personme");
}
try (PreparedStatement preparedStatement =
conn.prepareStatement(select_personme_ById)) {
preparedStatement.setLong(1, id);
rs = preparedStatement.executeQuery();
if (rs != null) {
deletepersons.clear();
while (rs.next()) {
	deletepersons.add(new
			PersonMe(
					rs.getLong("id"), 
					rs.getString("shifer"),
                    rs.getString("inn"),
                    rs.getString("type"),
                    rs.getDate("data")));
}
rs.close();
request.setAttribute("personsDelete",
		deletepersons);
} else {
System.out.println("Ошибка загрузки personme");

}
} catch (Exception e) {
System.out.println(e);
}
} catch (Exception e) {
System.out.println(e);
}
userPath = request.getServletPath();
if ("/deletepersonsme".equals(userPath)) {
request.getRequestDispatcher("/view/PersonMe/deletepersonme.jsp")
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
	conn.prepareStatement(delete_personme)) {
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
