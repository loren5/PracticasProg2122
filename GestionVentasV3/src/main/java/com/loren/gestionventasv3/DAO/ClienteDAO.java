/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loren.gestionventasv3.DAO;

import com.loren.gestionventasv3.POJO.Cliente;
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
public class ClienteDAO implements IOperationsCRUD<Cliente>{

    // Conexion compartida para todos los metodos
    private Connection conn = ConexionBD.getConnection();
    
    
    @Override
    public List<Cliente> getAll() {
        // Creamos la conexion
        List<Cliente> lista =  new ArrayList<Cliente>();
        try{
            // Creamos un objeto para ejecutar la consulta
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM cliente";
            // Obtenemos los resultados de la consulta
            ResultSet rs = st.executeQuery(sql);
            // Recorremos los resultados
            while(rs.next()){
                Long idAux = rs.getLong("id");
                String nomAux = rs.getString("nombre");
                String ape1Aux = rs.getString("apellido1");
                String ape2Aux = rs.getString("apellido2");
                String ciuAux = rs.getString("ciudad");
                int catAux = rs.getInt("categoria");
                // Crea el cliente y lo añade a la lista
                Cliente a = new Cliente(idAux, nomAux, ape1Aux, ape2Aux, ciuAux, catAux);
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
    public Cliente findById(Cliente object) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, object.getId());
            ResultSet rs = ps.executeQuery();
            Cliente a = null;
            while (rs.next()){
                Long idAux = rs.getLong("id");
                String nomAux = rs.getString("nombre");
                String ape1Aux = rs.getString("apellido1");
                String ape2Aux = rs.getString("apellido2");
                String ciuAux = rs.getString("ciudad");
                int catAux = rs.getInt("categoria");
                // Crea el cliente y lo añade a la lista
                a = new Cliente(idAux, nomAux, ape1Aux, ape2Aux, ciuAux, catAux);            
            }
            rs.close();
            ps.close();            
            return a;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }

    // Operacion propia del DAO
    public List<Cliente> findByNombre(Cliente object) {
        String sql = "SELECT * FROM cliente WHERE nombre = ?";
        List<Cliente> lista =  new ArrayList<Cliente>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, object.getNombre());
            ResultSet rs = ps.executeQuery();
            Cliente a = null;
            while (rs.next()){
                Long idAux = rs.getLong("id");
                String nomAux = rs.getString("nombre");
                String ape1Aux = rs.getString("apellido1");
                String ape2Aux = rs.getString("apellido2");
                String ciuAux = rs.getString("ciudad");
                int catAux = rs.getInt("categoria");
                // Crea el cliente y lo añade a la lista
                a = new Cliente(idAux, nomAux, ape1Aux, ape2Aux, ciuAux, catAux);            
                lista.add(a);
            }
            rs.close();
            ps.close();
            return lista;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }            
    }

    // Operacion propia del DAO
    public List<Cliente> findByApellido(Cliente object) {
        String sql = "SELECT * FROM cliente WHERE apellido1 LIKE ?";
        List<Cliente> lista =  new ArrayList<Cliente>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + object.getApellido1() + "%");
            ResultSet rs = ps.executeQuery();
            Cliente a = null;
            while (rs.next()){
                Long idAux = rs.getLong("id");
                String nomAux = rs.getString("nombre");
                String ape1Aux = rs.getString("apellido1");
                String ape2Aux = rs.getString("apellido2");
                String ciuAux = rs.getString("ciudad");
                int catAux = rs.getInt("categoria");
                // Crea el cliente y lo añade a la lista
                a = new Cliente(idAux, nomAux, ape1Aux, ape2Aux, ciuAux, catAux);            
                lista.add(a);
            }
            rs.close();
            ps.close();
            return lista;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }            
    }

    @Override
    public int add(Cliente object) {
        String sql = "INSERT INTO cliente(nombre, apellido1, apellido2, ciudad, categoria) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getApellido1());
            ps.setString(3, object.getApellido2());
            ps.setString(4, object.getCiudad());
            ps.setInt(5, object.getCategoria());            
            int i = ps.executeUpdate();
            ps.close();
            return i;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(Cliente object) {
        String sql = "UPDATE cliente SET nombre=?, apellido1=?, apellido2=?, "
                + "ciudad=?, categoria=? WHERE id=?;";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getApellido1());
            ps.setString(3, object.getApellido2());
            ps.setString(4, object.getCiudad());
            ps.setInt(5, object.getCategoria());            
            ps.setLong(6, object.getId()); 
            int i = ps.executeUpdate();
            ps.close();
            return i;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(Cliente object) {
        String sql = "DELETE FROM cliente WHERE id=?;";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);            
            ps.setLong(1, object.getId()); 
            int i = ps.executeUpdate();
            ps.close();
            return i;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
