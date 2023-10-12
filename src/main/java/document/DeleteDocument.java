package document;

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
import domain.Document;

@WebServlet("/deletedocuments")
public class DeleteDocument extends HttpServlet {
private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String selectAllDocumentsQuery = "SELECT id, name, seriy, organ, data FROM documents ORDER BY name ASC";
	String select_document_ById = "SELECT id, name, seriy, organ, data FROM documents WHERE id = ?";
	String delete_document = "DELETE FROM documents WHERE id = ?";
	ArrayList<Document> documents = new ArrayList<Document>();
	ArrayList<Document> deletedocuments = new ArrayList<Document>();
	String userPath;

 public DeleteDocument() throws FileNotFoundException,
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
	ResultSet rs = stmt.executeQuery(selectAllDocumentsQuery);
if (rs != null) {
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
} else {
System.out.println("Ошибка загрузки document");
}
try (PreparedStatement preparedStatement =
conn.prepareStatement(select_document_ById)) {
preparedStatement.setLong(1, id);
rs = preparedStatement.executeQuery();
if (rs != null) {
	deletedocuments.clear();
while (rs.next()) {
	deletedocuments.add(new
			Document(
					rs.getLong("id"),
					rs.getString("name"),
	                rs.getString("seriy"),
	                rs.getString("organ"),
		            rs.getDate("data")
		            ));
}
rs.close();
request.setAttribute("documentsDelete",
		deletedocuments);
} else {
System.out.println("Ошибка загрузки document");

}
} catch (Exception e) {
System.out.println(e);
}
} catch (Exception e) {
System.out.println(e);
}
userPath = request.getServletPath();
if ("/deletedocuments".equals(userPath)) {
request.getRequestDispatcher("/view/Document/deletedocument.jsp")
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
	conn.prepareStatement(delete_document)) {
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
