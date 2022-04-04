/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loren.gestionventasv3.DAO;

import com.loren.gestionventasv3.POJO.Comercial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author profesor
 */
public class ComercialDAO implements IOperationsCRUD<Comercial>{
    
    // Conexion compartida para todos los metodos
    private Connection conn = ConexionBD.getConnection();
    
    
    @Override
    public List<Comercial> getAll() {
        // Creamos la conexion
        List<Comercial> lista =  new ArrayList<Comercial>();
        try{
            // Creamos un objeto para ejecutar la consulta
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM comercial";
            // Obtenemos los resultados de la consulta
            ResultSet rs = st.executeQuery(sql);
            // Recorremos los resultados
            while(rs.next()){
                Long idAux = rs.getLong("id");
                String nomAux = rs.getString("nombre");
                String ape1Aux = rs.getString("apellido1");
                String ape2Aux = rs.getString("apellido2");
                float comAux = rs.getFloat("comision");                
                // Crea el cliente y lo añade a la lista
                Comercial a = new Comercial(idAux, nomAux, ape1Aux, ape2Aux, comAux);
                lista.add(a);
            }
            // Cerrar los objetos que usan para las consultas
            rs.close();
            st.close();
            // Devolvemos la lista
            return lista;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }   

    @Override
    public Comercial findById(Comercial object) {
        String sql = "SELECT * FROM comercial WHERE id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, object.getId());
            ResultSet rs = ps.executeQuery();
            Comercial a = null;
            while (rs.next()){
                Long idAux = rs.getLong("id");
                String nomAux = rs.getString("nombre");
                String ape1Aux = rs.getString("apellido1");
                String ape2Aux = rs.getString("apellido2");
                float comAux = rs.getFloat("comision");  
                // Crea el cliente y lo añade a la lista
                a = new Comercial(idAux, nomAux, ape1Aux, ape2Aux, comAux);            
            }
            rs.close();
            ps.close();            
            return a;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }    

    @Override
    public int add(Comercial object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Comercial object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Comercial object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
