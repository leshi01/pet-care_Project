package DB_tables;

import DB_Connection.Connect;
import com.google.gson.Gson;
import mainClasses.Pet;
import mainClasses.PetOwner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditPet {

    public Pet jsonToPet(String json) {
        Gson gson = new Gson();

        Pet pet = gson.fromJson(json, Pet.class);
        return pet;
    }

    public String petToJSON(Pet pet) {
        Gson gson = new Gson();

        String json = gson.toJson(pet, Pet.class);
        return json;
    }

    public void addNewPet(Pet pet) throws ClassNotFoundException {
        try {
            Connection con = Connect.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + "pets (pet_id, owner_id, name, type, breed, gender, birthyear, weight, description, photo)"
                    + " VALUES ("
                    + "'" + generateID(pet.getOwner_id(), pet.getBirthyear(), pet.getWeight()) + "',"
                    + "'" + pet.getOwner_id() + "',"
                    + "'" + pet.getName() + "',"
                    + "'" + pet.getType() + "',"
                    + "'" + pet.getBreed() + "',"
                    + "'" + pet.getGender() + "',"
                    + "'" + pet.getBirthyear() + "',"
                    + "'" + pet.getWeight() + "',"
                    + "'" + pet.getDescription() + "',"
                    + "'" + pet.getPhoto() + "'"
                    + ")";
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The pet was successfully added in the database.");

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditPet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String generateID(int int1, int int2, double dbl) {
        int result = (int) (int1 * int2 * dbl);
        String id = String.valueOf(result);

        // Ensure the ID is exactly 10 characters long
        if (id.length() > 10) {
            id = id.substring(0, 10);
        } else {
            while (id.length() < 10) {
                id += "0";
            }
        }

        return id;
    }

}
