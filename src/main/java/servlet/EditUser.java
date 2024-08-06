package servlet;

import DB_tables.EditPetKeepersTable;
import DB_tables.EditPetOwnersTable;
import com.google.gson.Gson;
import mainClasses.EditedInfo;
import mainClasses.PetKeeper;
import mainClasses.PetOwner;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class EditUser extends HttpServlet {
    @Override
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

        Gson gson = new Gson();

        EditedInfo user = gson.fromJson(jsonString, EditedInfo.class);

        try ( PrintWriter out = response.getWriter()) {


            if (jsonString.contains("owner")) {


                try {
                    EditPetOwnersTable eut = new EditPetOwnersTable();

                    eut.updatePetOwner(user.getUser(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail());

                    response.setStatus(200);
                    out.println("{ \"status\": \"success\", \"message\": \"Registration successful.\" }");

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    response.setStatus(500);
                    out.println("{ \"status\": \"error\", \"message\": \"Internal Server Error.\" }");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } else {

                EditPetKeepersTable eut = new EditPetKeepersTable();

                try {
                    eut.updatePetKeeper(user.getUser(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail());

                    response.setStatus(200);
                    out.println("{ \"status\": \"success\", \"message\": \"Registration successful.\" }");

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    response.setStatus(500);
                    out.println("{ \"status\": \"error\", \"message\": \"Internal Server Error.\" }");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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