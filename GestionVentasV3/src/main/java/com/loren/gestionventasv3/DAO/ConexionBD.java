/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loren.gestionventasv3.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author profesor
 */
public class ConexionBD {
    
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String URL = 
            "jdbc:mysql://localhost:3306/ventas";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection conexion = null;
    
    // Cambio la visibilidad para controlar la construccion
    private ConexionBD(){

        try{
            // Carga del driver
            Class.forName(DRIVER);
            // Establecer la conexión con la BD
            conexion = 
                    DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Mi propio constructor
    public static Connection getConnection() {
        if(conexion == null){
            new ConexionBD();
        }
        return conexion;

    }
    
    public static void closeConnection(){
        try{
            conexion.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
