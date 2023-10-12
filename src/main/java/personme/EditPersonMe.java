package personme;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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

import domain.PersonMe;

/**
* Servlet implementation class EditRoleServlet
*/
@WebServlet("/editpersonsme")
public class EditPersonMe extends HttpServlet {
private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String selectAllPersonsQuery = "SELECT id, shifer, inn, type, data FROM person_me ORDER BY shifer ASC";
	String select_personme_ById = "SELECT id, shifer, inn, type, data FROM person_me WHERE id = ?";
	String edit_personme = "UPDATE person_me SET shifer = ?, inn = ?, type = ?, data = ? WHERE id = ?";
	ArrayList<PersonMe> persons = new ArrayList<PersonMe>();
	ArrayList<PersonMe> editpersons = new ArrayList<PersonMe>();
	String userPath;

 /**
 * @see HttpServlet#HttpServlet()
 */
 public EditPersonMe() throws FileNotFoundException, IOException
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
ResultSet rs = stmt.executeQuery(selectAllPersonsQuery);
if(rs != null) {
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
}

else
{
System.out.println("Ошибка загрузки personme");
}
try (PreparedStatement preparedStatement =
conn.prepareStatement(select_personme_ById)) {
preparedStatement.setLong(1, id);
rs = preparedStatement.executeQuery();
if(rs != null) {editpersons.clear();
while (rs.next()) {
	editpersons.add(new
			PersonMe(
					rs.getLong("id"), 
					rs.getString("shifer"),
                    rs.getString("inn"),
                    rs.getString("type"),
                    rs.getDate("data")));
}
rs.close();
request.setAttribute("personsEdit",editpersons);
}
else
{
System.out.println("Ошибка загрузки personme");
}
} catch (Exception e) {
System.out.println(e);
}
} catch (Exception e) {
System.out.println(e);
}
userPath = request.getServletPath();
if("/editpersonsme".equals(userPath)){
request.getRequestDispatcher("/view/PersonMe/editpersonme.jsp")
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
		String shifer = request.getParameter("shifer");
        String inn = request.getParameter("inn");
        String type = request.getParameter("type");
        Date data = Date.valueOf(request.getParameter("data"));
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(edit_personme)) {
			preparedStatement.setString(1, shifer);
			preparedStatement.setString(2, inn);
			preparedStatement.setString(3, type);
			preparedStatement.setDate(4, data);
			preparedStatement.setLong(5, id);
			
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