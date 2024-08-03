package servlet;

import DB_tables.EditBooking;
import DB_tables.EditPetOwnersTable;
import com.google.gson.Gson;
import mainClasses.BookingInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Booking extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");


        StringBuilder sb = new StringBuilder();
        try ( BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String jsonString = sb.toString();

        System.out.println(jsonString);
        Gson gson = new Gson();
        BookingInfo booking = gson.fromJson(jsonString, BookingInfo.class);

        EditBooking eut = new EditBooking();

        System.out.println(
                booking.getOwner_id() + " " +
                        booking.getPet_id() + " " +
                        booking.getKeeper_id() + " " +
                        booking.getFromdate() + " " +
                        booking.getTodate() + " " +
                        booking.getPrice()
        );

        try ( PrintWriter out = response.getWriter()) {

            eut.addNewBooking(booking);
            response.setStatus(200);
            out.println("{ \"status\": \"success\", \"message\": \"Registration successful.\" }");


        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "RegisterPetKeeper servlet";
    }
}
