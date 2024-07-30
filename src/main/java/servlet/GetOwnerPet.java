package servlet;

import DB_tables.EditPet;
import com.google.gson.Gson;
import mainClasses.Pet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetOwnerPet extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        //
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            EditPet eut = new EditPet();

            var OwnerId = request.getParameter("ownerId");

            // Retrieve all PetKeepers from the database
            ArrayList<Pet> pets = eut.getPets(OwnerId);

            // Convert the list of PetKeepers to JSON
            Gson gson = new Gson();
            String json = gson.toJson(pets);

            // Send the JSON response
            out.println(json);
            response.setStatus(200);
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(500); // Internal Server Error
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
