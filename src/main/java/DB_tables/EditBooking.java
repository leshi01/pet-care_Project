package DB_tables;

import DB_Connection.Connect;
import com.google.gson.Gson;
import mainClasses.BookingInfo;
import mainClasses.Pet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditBooking {
    public void deleteBookingOwner(int id) throws SQLException, ClassNotFoundException {
        Connection con = Connect.getConnection();
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM bookings WHERE owner_id = '" + id + "'";
        stmt.executeUpdate(delete);
    }

    public void deleteBookingKeeper(int id) throws SQLException, ClassNotFoundException {
        Connection con = Connect.getConnection();
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM bookings WHERE keeper_id = '" + id + "'";
        stmt.executeUpdate(delete);
    }

    public ArrayList<BookingInfo> getBookings(String keeperId) throws SQLException, ClassNotFoundException {
        Connection con = Connect.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<BookingInfo> Bookings = new ArrayList<BookingInfo>();
        ResultSet rs = null;
        try {

            rs = stmt.executeQuery("SELECT * FROM `bookings` WHERE `keeper_id` = '" + keeperId + "'");
            while (rs.next()) {
                String json = Connect.getResultsToJSON(rs);
                Gson gson = new Gson();
                BookingInfo booking = gson.fromJson(json, BookingInfo.class);
                Bookings.add(booking);
            }
            return Bookings;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public BookingInfo jsonToBooking(String json) {
        Gson gson = new Gson();

        BookingInfo booking = gson.fromJson(json, BookingInfo.class);
        return booking;
    }

    public String bookingToJSON(BookingInfo booking) {
        Gson gson = new Gson();

        String json = gson.toJson(booking, BookingInfo.class);
        return json;
    }

    public boolean updateBooking(String bookingId, String status) throws SQLException, ClassNotFoundException {
        Connection con = Connect.getConnection();
        Statement stmt = con.createStatement();

        String update;
        update = "UPDATE bookings SET status='" + status + "' WHERE booking_id = '" + bookingId + "'";
        stmt.executeUpdate(update);

        return true;
    }

    public void addNewBooking(BookingInfo booking) throws ClassNotFoundException {
        try {
            Connection con = Connect.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + "bookings (owner_id, pet_id, keeper_id, fromdate, todate, status, price)"
                    + " VALUES ("
                    + "'" + booking.getOwner_id() + "',"
                    + "'" + booking.getPet_id() + "',"
                    + "'" + booking.getKeeper_id() + "',"
                    + "'" + booking.getFromdate() + "',"
                    + "'" + booking.getTodate() + "',"
                    + "'" + "requested" + "',"
                    + "'" + booking.getPrice() + "'"
                    + ")";
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The booking was successfully Requested.");

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditPet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
