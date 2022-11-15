/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raca.connectionexampleoracle.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Ricardo Carmona
 */
public class Conexion {
    
    public Connection conectar(){
        Connection conexion = null;
         try{
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
             
            //nombre del servidor
            String nombre_servidor = "127.0.0.1";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "xe";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String usuario = "HR";
            String password = "HR";
 
            //Obtiene la conexion
            conexion = DriverManager.getConnection( url, usuario, password);
        }catch( Exception e ){
            e.printStackTrace();
            return null;
        }
         return conexion;
    }
           
}
