/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raca.connectionexampleoracle.Controller;

import com.raca.connectionexampleoracle.Model.Employee;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Ricardo Carmona
 */
public class EmployeeController {
    
     Conexion conexion = new Conexion();
    
     public String registrarEmployee(Employee employee) throws SQLException{
         String nom = "";
          Connection connection = conexion.conectar();
        try {
           
            CallableStatement st = connection.prepareCall("{? = call PKG_EJERCICIO.REGISTRAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?)}");
            st.registerOutParameter(1, java.sql.Types.VARCHAR);
            st.setInt(2, employee.getEmployee_id());
            st.setString(3, employee.getFirst_name());
            st.setString(4, employee.getLast_name());
            st.setString(5, employee.getEmail());
            st.setString(6, employee.getPhone_number());
            st.setDate(7, new java.sql.Date(employee.getHire_date().getTime()));
            st.setString(8, employee.getJob_id());
            st.setDouble(9, employee.getSalary());
            st.setDouble(10, employee.getCommission_pct());
            st.setInt(11, employee.getManager_id());
            st.setInt(12, employee.getDepartament_id());
            st.executeQuery();
            nom = st.getString(1);

        } catch (SQLException ex) {
            throw new SQLException("Error al ejecutar Sentencia !!!!"+ex.getMessage());
        }finally{
            connection.close();
        }
        return nom;
     }
     
     public List<Employee> listadoEmpleadosPorDepartamento(int departmentId) throws SQLException{
          Connection connection = conexion.conectar();
          List<Employee> listadoEmpleados = new ArrayList();
         try {
             CallableStatement st = connection.prepareCall("{? = call PKG_EJERCICIO.LISTAR_EMP_POR_DEPARTAMENTO(?)}");
             st.registerOutParameter(1, OracleTypes.CURSOR);
             st.setInt(2, departmentId);
             st.executeQuery();
             
             ResultSet cursor = ((ResultSet) st.getObject(1));
             
             while(cursor.next()){
                 Employee employee = new Employee();
                 employee.setEmployee_id(Integer.parseInt(cursor.getString("EMPLOYEE_ID")));
                 employee.setFirst_name(cursor.getString("FIRST_NAME"));
                 employee.setLast_name(cursor.getString("LAST_NAME"));
                 employee.setEmail(cursor.getString("EMAIL"));
                 employee.setPhone_number(cursor.getString("PHONE_NUMBER"));
                 employee.setHire_date(cursor.getDate("HIRE_DATE"));
                 employee.setJob_id(cursor.getString("JOB_ID"));
                 employee.setSalary(cursor.getDouble("SALARY"));
                 employee.setCommission_pct(cursor.getDouble("COMMISSION_PCT"));
                 employee.setManager_id(cursor.getInt("MANAGER_ID"));
                 employee.setDepartament_id(cursor.getInt("DEPARTMENT_ID"));
                 listadoEmpleados.add(employee);
             }
             
             
         } catch (SQLException ex) {
            throw new SQLException("Error al ejecutar Sentencia !!!!"+ex.getMessage());
         }finally{
            connection.close();
        }
       
         return listadoEmpleados;
     }
     
     public List<Employee> listadoEmpleados() throws SQLException{
          Connection connection = conexion.conectar();
          List<Employee> listadoEmpleados = new ArrayList();
         try {
             CallableStatement st = connection.prepareCall("{? = call PKG_EJERCICIO.LISTAR_EMPLEADOS}");
             st.registerOutParameter(1, OracleTypes.CURSOR);
             st.executeQuery();
             
             ResultSet cursor = ((ResultSet) st.getObject(1));
             
             while(cursor.next()){
                 Employee employee = new Employee();
                 employee.setEmployee_id(Integer.parseInt(cursor.getString("EMPLOYEE_ID")));
                 employee.setFirst_name(cursor.getString("FIRST_NAME"));
                 employee.setLast_name(cursor.getString("LAST_NAME"));
                 employee.setEmail(cursor.getString("EMAIL"));
                 employee.setPhone_number(cursor.getString("PHONE_NUMBER"));
                 employee.setHire_date(cursor.getDate("HIRE_DATE"));
                 employee.setJob_id(cursor.getString("JOB_ID"));
                 employee.setJob_name(cursor.getString("JOB_NAME"));
                 employee.setSalary(cursor.getDouble("SALARY"));
                 employee.setCommission_pct(cursor.getDouble("COMMISSION_PCT"));
                 employee.setManager_id(cursor.getInt("MANAGER_ID"));
                 employee.setManager_name(cursor.getString("MANAGER_NAME"));
                 employee.setDepartament_id(cursor.getInt("DEPARTMENT_ID"));
                 employee.setDepartment_name(cursor.getString("DEPARTMENT_NAME"));
                 listadoEmpleados.add(employee);
             }
             
             
         } catch (SQLException ex) {
             System.err.println(ex.getStackTrace()[0].toString());
            throw new SQLException("Error al ejecutar Sentencia !!!!"+ex.getMessage());
         }finally{
            connection.close();
        }
       
         return listadoEmpleados;
     }
     
    
    
    
}
