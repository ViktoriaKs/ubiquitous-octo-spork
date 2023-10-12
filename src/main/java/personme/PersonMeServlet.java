package personme;

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
import domain.PersonMe;

@WebServlet("/personsme")
public class PersonMeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ConnectionProperty prop;
    String selectAllPersonsQuery = "SELECT id, shifer, inn, type, data FROM person_me";
    String INSERT_PERSON = "INSERT INTO person_me (shifer, inn, type, data) VALUES (?, ?, ?, ?)";
    List<PersonMe> persons = new ArrayList<PersonMe>();
    String userPath;
    
    public PersonMeServlet() throws FileNotFoundException, IOException {
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        try (Connection conn = prop.getConnection()) {
            // Load all persons
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
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        userPath = request.getServletPath();
        if("/personsme".equals(userPath)){
        request.getRequestDispatcher("/view/PersonMe/personme.jsp")
                .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST requests if needed
    	try (Connection conn = prop.getConnection()) {
      
            String shifer = request.getParameter("shifer");
            String inn = request.getParameter("inn");
            String type = request.getParameter("type");
            Date data = Date.valueOf(request.getParameter("data")); // Assuming "data" is a date field
            
            PersonMe newPersonMe = new PersonMe(shifer, inn, type, data);
            
            try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PERSON)) {
                preparedStatement.setString(1, newPersonMe.getShifer());
                preparedStatement.setString(2, newPersonMe.getInn());
                preparedStatement.setString(3, newPersonMe.getType());
                preparedStatement.setDate(4, (Date) newPersonMe.getData());
                
                int result = preparedStatement.executeUpdate();
           
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            getServletContext().getRequestDispatcher("/view/personme.jsp")
    		.forward(request, response);
    		}
    		doGet(request, response);
    	}
    }
