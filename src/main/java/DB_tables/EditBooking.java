package DB_tables;

import DB_Connection.Connect;
import com.google.gson.Gson;
import mainClasses.BookingInfo;
import mainClasses.Pet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditBooking {

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
