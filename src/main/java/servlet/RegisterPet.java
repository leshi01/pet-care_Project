package servlet;

import DB_tables.EditPet;
import com.google.gson.Gson;
import mainClasses.Pet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegisterPet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        Pet newPet;

        StringBuilder sb = new StringBuilder();
        try ( BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String jsonString = sb.toString();

        Gson gson = new Gson();


        try ( PrintWriter out = response.getWriter()) {

                newPet = gson.fromJson(jsonString, Pet.class);

                EditPet eut = new EditPet();


                try {
                    eut.addNewPet(newPet);

                    response.setStatus(200);
                    out.println("{ \"status\": \"success\", \"message\": \"Registration successful.\" }" + newPet);

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    response.setStatus(500);
                    out.println("{ \"status\": \"error\", \"message\": \"Internal Server Error.\" }");
                }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "RegisterPetKeeper servlet";
    }

}
