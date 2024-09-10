package DB_tables;

import DB_Connection.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditRevieus {
    public void deleteReviewOwner(int id) throws SQLException, ClassNotFoundException {
        Connection con = Connect.getConnection();
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM reviews WHERE owner_id = '" + id + "'";
        stmt.executeUpdate(delete);
    }

    public void deleteReviewKeeper(int id) throws SQLException, ClassNotFoundException {
        Connection con = Connect.getConnection();
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM reviews WHERE keeper_id = '" + id + "'";
        stmt.executeUpdate(delete);
    }
}
