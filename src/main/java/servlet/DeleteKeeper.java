package servlet;



import DB_tables.EditBooking;
import DB_tables.EditPetKeepersTable;
import DB_tables.EditPetOwnersTable;
import DB_tables.EditRevieus;

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

@WebServlet(name = "DeleteKeeper", urlPatterns = {"/DeleteKeeper"})
public class DeleteKeeper extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        String id = request.getParameter("id");
        System.out.println(id + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        try (PrintWriter out = response.getWriter()) {
            EditPetKeepersTable eut = new EditPetKeepersTable();
            EditBooking eutB = new EditBooking();
            EditRevieus eutR = new EditRevieus();


            try {
                eut.deletePetKeeper(Integer.parseInt(id));
                eutB.deleteBookingKeeper(Integer.parseInt(id));
                eutR.deleteReviewKeeper(Integer.parseInt(id));
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
        return "DeleteKeeper servlet";
    }
}