/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.go.cheeta.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
   static final String URL = "jdbc:mysql://localhost:3306/go_cheeta_db?autoReconnect=true&useSSL=false";
   static final String USERNAME = "kushanNew";
   static final String PASSWORD = "123";   
   
   private final Statement getStatement() {
      Statement statement = null;
       try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            
            return statement;
       }catch(SQLException e) {
            System.out.println(e);
       }
       
       return statement;
   }

    public boolean addCustomer(Customer customer) {
        try {        

            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            int rows = statement.executeUpdate("INSERT INTO customer "
                    + "(`CstID`, `CstName`, `CstEmail`, `CstMobile`, `CstPassword`) VALUES ('" 
                    + customer.getId() + "', '" + customer.getName() +  "', '" + customer.getEmail()+ "', '" + customer.getMobile()+ "', '" + customer.getPassword()+ "')");
            
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error Occured when adding customer" + e);
        }  
        return false;
    }   

public List<Customer> checkLoginCustomer(String email, String password) {
        List<Customer> customerDetails = new ArrayList<>();
        try {
            ResultSet resultSet = getStatement().executeQuery("SELECT * FROM `customer` WHERE (`CstEmail` = '" + email 
                    + "' and `CstPassword` = '" + password + "')");
            
            while(resultSet.next()) {
                Customer customer = new Customer(resultSet.getString("CstID"));
                customerDetails.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("error occured when retrieving customer details" + e);
        }    
        return customerDetails;
    }

public boolean addAdmin(Admin admin) {
        try {
            int rows = getStatement().executeUpdate("INSERT INTO admin"
                    + "(`AdID`, `AdName`, `AdEmail`, `AdMobile`, `AdPassword`) VALUES ('" 
                    + admin.getId() + "', '" + admin.getName() +  "', '" + admin.getEmail()+ "', '" + admin.getMobile()+ "', '" + admin.getPassword()+ "')");
            
            return rows > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }  
        return false;
    }

public boolean CheckAdminLogin(String email, String password) {
        try {
            ResultSet rs = getStatement().executeQuery("SELECT * FROM `admin` WHERE (`AdEmail` = '" + email 
                    + "' and `AdPassword` = '" + password + "');");
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error occured when logging in for admin" + e);
        }  
       return false;
    }

}