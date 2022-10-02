/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.go.cheeta.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("admin")
public class AdminService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAdmin(String json) {
        Gson gson = new GsonBuilder().create();
        DBUtils dBUtils = new DBUtils();
        Admin admin = gson.fromJson(json, Admin.class);

        boolean isSuccess = dBUtils.addAdmin(admin);
        if (isSuccess) {
            return Response.status(201).entity("Successfully Added Admin").build();
        }
        return Response.status(500).entity("An Error Occured when adding a new admin").build();
    }   
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAdminDetails(String json) {
        Gson gson = new GsonBuilder().create();
        DBUtils dBUtils = new DBUtils();
        Admin admin = gson.fromJson(json, Admin.class);
        boolean isSuccess = dBUtils.CheckAdminLogin(admin.getEmail(), admin.getPassword());
        if (isSuccess) {
            return Response.status(201).entity("Successfully Logged in admin").build();
        }
        return Response.status(500).entity("An Error Occured when admin login").build();
    }
}
