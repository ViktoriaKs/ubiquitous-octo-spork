package citizen;

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

import domain.Citizen;

/**
* Servlet implementation class EditRoleServlet
*/
@WebServlet("/editcitizens")
public class EditCitizen extends HttpServlet {
private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String selectAllCitizensQuery = "SELECT id, document_id, person_id, first_name, second_name, last_name, number FROM citizen ORDER BY first_name ASC";
	String select_citizen_ById = "SELECT id, document_id, person_id, first_name, second_name, last_name, number FROM citizen WHERE id = ?";
	String edit_citizen = "UPDATE citizen SET document_id = ?, person_id = ?, first_name = ?, second_name = ?, last_name = ?, number = ?  WHERE id = ?";
	ArrayList<Citizen> citizens = new ArrayList<Citizen>();
	ArrayList<Citizen> editcitizens = new ArrayList<Citizen>();
	String userPath;

 /**
 * @see HttpServlet#HttpServlet()
 */
 public EditCitizen() throws FileNotFoundException, IOException
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
ResultSet rs = stmt.executeQuery(selectAllCitizensQuery);
if(rs != null) {
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
}

else
{
System.out.println("Ошибка загрузки citizens");
}
try (PreparedStatement preparedStatement =
conn.prepareStatement(select_citizen_ById)) {
preparedStatement.setLong(1, id);
rs = preparedStatement.executeQuery();
if(rs != null) {editcitizens.clear();
while (rs.next()) {
	editcitizens.add(new
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
request.setAttribute("citizensEdit",editcitizens);
}
else
{
System.out.println("Ошибка загрузки editcitizens");
}
} catch (Exception e) {
System.out.println(e);
}
} catch (Exception e) {
System.out.println(e);
}
userPath = request.getServletPath();
if("/editcitizens".equals(userPath)){
request.getRequestDispatcher("/view/Citizen/editсitizen.jsp")
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
		long document_id = Long.parseLong(request.getParameter("document_id"));
		long person_id = Long.parseLong(request.getParameter("person_id"));
        String first_name = request.getParameter("first_name");
        String second_name = request.getParameter("second_name");
        String last_name = request.getParameter("last_name");
        java.lang.String number = request.getParameter("number");
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(edit_citizen)) {
				
			preparedStatement.setLong(1, document_id);
            preparedStatement.setLong(2, person_id);	
			preparedStatement.setString(3, first_name);
			preparedStatement.setString(4, second_name);
			preparedStatement.setString(5, last_name);
			preparedStatement.setString(6, number);
			preparedStatement.setLong(7, id);
			
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