/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.go.cheeta.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("customer")
public class CustomerService {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(String json) {
        Gson gson = new GsonBuilder().create();
        DBUtils dBUtils = new DBUtils();
        Customer customer = gson.fromJson(json, Customer.class);
        boolean isSuccess = dBUtils.addCustomer(customer);
        if (isSuccess) {
            return Response.status(201).entity("Sucess").build();
        }
        return Response.status(500).entity("An Error Occured when adding a new customer").build();
    }   

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerDetails(String json) {
        Gson gson = new GsonBuilder().create();
        DBUtils dBUtils = new DBUtils();
        Customer customer = gson.fromJson(json, Customer.class);
        List<Customer> customerList = dBUtils.checkLoginCustomer(customer.getEmail(), customer.getPassword());
        
        return gson.toJson(customerList);
    }
}