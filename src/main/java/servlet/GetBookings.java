package servlet;

import DB_tables.EditBooking;
import DB_tables.EditPet;
import com.google.gson.Gson;
import mainClasses.BookingInfo;
import mainClasses.Pet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


public class GetBookings extends HttpServlet {


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
            EditBooking eut = new EditBooking();

            var KeeperId = request.getParameter("keeperId");

            // Retrieve all PetKeepers from the database
            ArrayList<BookingInfo> Bookings = eut.getBookings(KeeperId);

            // Convert the list of PetKeepers to JSON
            Gson gson = new Gson();
            String json = gson.toJson(Bookings);

            // Send the JSON response
            out.println(json);
            response.setStatus(200);
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(500); // Internal Server Error
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            EditBooking editBooking = new EditBooking();

            // Parse request parameters
            String bookingId = request.getParameter("BookingId");
            String newStatus = request.getParameter("Status");
            System.out.println("BookingId: " + bookingId + "Status: " + newStatus);

            // Update the booking status in the database
            boolean updateSuccess = editBooking.updateBooking(bookingId, newStatus);

            // Prepare the response
            if (updateSuccess) {
                response.setStatus(200);
                out.println("{\"message\": \"Booking status updated successfully.\"}");
            } else {
                response.setStatus(400);
                out.println("{\"message\": \"Failed to update booking status.\"}");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(500); // Internal Server Error
        }
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
