package document;

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

import domain.Document;

/**
* Servlet implementation class EditRoleServlet
*/
@WebServlet("/editdocuments")
public class EditDocument extends HttpServlet {
private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String selectAllDocumentsQuery = "SELECT id, name, seriy, organ, data FROM documents ORDER BY name ASC";
	String select_document_ById = "SELECT id, name, seriy, organ, data FROM documents WHERE id = ?";
	String edit_document = "UPDATE documents SET name = ?, seriy = ?, organ = ?, data = ? WHERE id = ?";
	ArrayList<Document> documents = new ArrayList<Document>();
	ArrayList<Document> editdocuments = new ArrayList<Document>();
	String userPath;

 /**
 * @see HttpServlet#HttpServlet()
 */
 public EditDocument() throws FileNotFoundException, IOException
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
ResultSet rs = stmt.executeQuery(selectAllDocumentsQuery);
if(rs != null) {
	documents.clear();
while (rs.next()) {
	documents.add(new Document(
			rs.getLong("id"),
			rs.getString("name"),
            rs.getString("seriy"),
            rs.getString("organ"),
            rs.getDate("data")
));
}
rs.close();
request.setAttribute("documents", documents);
}

else
{
System.out.println("Ошибка загрузки document");
}
try (PreparedStatement preparedStatement =
conn.prepareStatement(select_document_ById)) {
preparedStatement.setLong(1, id);
rs = preparedStatement.executeQuery();
if(rs != null) {editdocuments.clear();
while (rs.next()) {
	editdocuments.add(new
			Document(
					rs.getLong("id"),
					rs.getString("name"),
	                rs.getString("seriy"),
	                rs.getString("organ"),
		            rs.getDate("data")));
}
rs.close();
request.setAttribute("documentsEdit",editdocuments);
}
else
{
System.out.println("Ошибка загрузки document");
}
} catch (Exception e) {
System.out.println(e);
}
} catch (Exception e) {
System.out.println(e);
}
userPath = request.getServletPath();
if("/editdocuments".equals(userPath)){
request.getRequestDispatcher("/view/Document/editdocument.jsp")
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
		String name = request.getParameter("name");
        String seriy = request.getParameter("seriy");
        String organ = request.getParameter("organ");
        Date data = Date.valueOf(request.getParameter("data"));
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(edit_document)) {
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, seriy);
			preparedStatement.setString(3, organ);
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