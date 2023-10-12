package citizen;

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
import domain.Citizen;


@WebServlet("/citizens")
public class CitizenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ConnectionProperty prop;
    String INSERT_CITIZEN = "INSERT INTO citizen (document_id, person_id, first_name, second_name, last_name, number) VALUES (?, ?, ?, ?, ?, ?)";
    String selectAllCitizensQuery = "SELECT id, document_id, person_id, first_name, second_name, last_name, number FROM citizen";
    List<Citizen> citizens = new ArrayList<Citizen>();
    String userPath;
    
    public CitizenServlet() throws FileNotFoundException, IOException {
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        try (Connection conn = prop.getConnection()) {
            // Load all citizens
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
            } 
        }catch (Exception e) {
            System.out.println(e);
        }
        userPath = request.getServletPath();
		if("/citizens".equals(userPath)){
        // Forward the request to a JSP view for rendering
        request.getRequestDispatcher("/view/Citizen/citizen.jsp")
                .forward(request, response);
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST requests if needed
    	try (Connection conn = prop.getConnection()) {
    	    
    		long document_id = Long.parseLong(request.getParameter("document_id"));
    		long person_id = Long.parseLong(request.getParameter("person_id"));
            String first_name = request.getParameter("first_name");
            String second_name = request.getParameter("second_name");
            String last_name = request.getParameter("last_name");
            java.lang.String number = request.getParameter("number"); // Assuming "data" is a date field
            
            Citizen newCitizen = new Citizen(document_id, person_id, first_name, second_name, last_name, number);
            
            try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_CITIZEN)) {
            	preparedStatement.setLong(1, newCitizen.getDocumentId());
                preparedStatement.setLong(2, newCitizen.getPersonId());
                preparedStatement.setString(3, newCitizen.getFirstName());
                preparedStatement.setString(4, newCitizen.getSecondName());
                preparedStatement.setString(5, newCitizen.getLastName());
                preparedStatement.setString(6, newCitizen.getNumber());
                
                int result = preparedStatement.executeUpdate();
           
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            getServletContext().getRequestDispatcher("/view/Citizen/citizen.jsp")
    		.forward(request, response);
    		}
    		doGet(request, response);
    	}
    }
