package document;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ConnectionProperty;
import domain.Document;

@WebServlet("/documents")
public class DocumentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ConnectionProperty prop;
    String selectAllDocumentsQuery = "SELECT id, name, seriy, organ, data FROM documents";
    String INSERT_DOCUMENT = "INSERT INTO documents (name, seriy, organ, data) VALUES (?, ?, ?, ?)";
    List<Document> documents = new ArrayList<Document>();
    String userPath;
    
    public DocumentServlet() throws FileNotFoundException,IOException {
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        ConnectionProperty builder = new ConnectionProperty();
        
        try (Connection conn = builder.getConnection()) {
            // Load all documents
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
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        userPath = request.getServletPath();
        if("/documents".equals(userPath)){
        // Forward the request to a JSP view for rendering
        request.getRequestDispatcher("/view/Document/document.jsp")
                .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionProperty builder = new ConnectionProperty();
        
        try (Connection conn = builder.getConnection()) {
            String name = request.getParameter("name");
            String seriy = request.getParameter("seriy");
            String organ = request.getParameter("organ");
            Date data = Date.valueOf(request.getParameter("data")); // Assuming "data" is a date field
            
            Document newDocument = new Document(name, seriy, organ, data);
            
            try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_DOCUMENT)) {
                preparedStatement.setString(1, newDocument.getName());
                preparedStatement.setString(2, newDocument.getSeriy());
                preparedStatement.setString(3, newDocument.getOrgan());
                preparedStatement.setDate(4, (Date) newDocument.getData());
                
                int result = preparedStatement.executeUpdate();
           
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            getServletContext().getRequestDispatcher("/view/Document/document.jsp")
    		.forward(request, response);
    		}
    		doGet(request, response);
    	}
    }
