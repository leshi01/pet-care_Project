package servlet;

import DB_tables.EditPetOwnersTable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "DeleteOwner", urlPatterns = {"/DeleteOwner"})
public class DeleteOwner extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        String id = request.getParameter("id");
        System.out.println(id + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        try (PrintWriter out = response.getWriter()) {
            EditPetOwnersTable eut = new EditPetOwnersTable();

            try {
                eut.deletePetOwner(Integer.parseInt(id));
                response.setStatus(200);
                out.println("{ \"status\": \"success\", \"message\": \"Owner deleted successfully.\" }");
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DeleteOwner.class.getName()).log(Level.SEVERE, null, ex);
                response.setStatus(500);
                out.println("{ \"status\": \"error\", \"message\": \"Internal Server Error.\" }");
            }
        } catch (Exception ex) {
            Logger.getLogger(DeleteOwner.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(500);
            try (PrintWriter out = response.getWriter()) {
                out.println("{ \"status\": \"error\", \"message\": \"Internal Server Error.\" }");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "DeleteOwner servlet";
    }
}