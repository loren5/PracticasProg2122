package com.loren.gestionventasv3.DAO.Pedido;

import com.loren.gestionventasv3.DAO.ConexionBD;
import com.loren.gestionventasv3.DAO.FactoriaDAO;
import com.loren.gestionventasv3.DAO.Pedido.PedidoDAO;
import com.loren.gestionventasv3.POJO.Cliente;
import com.loren.gestionventasv3.POJO.Comercial;
import com.loren.gestionventasv3.POJO.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PedidoDAOImpl implements PedidoDAO{
    
    // Conexion compartida para todos los metodos
    private Connection conn = ConexionBD.getConnection();    

    @Override
    public List<Pedido> getAll() {
        // Creamos la conexion
        List<Pedido> lista =  new ArrayList<Pedido>();
        try{
            // Creamos un objeto para ejecutar la consulta
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM pedido";
            // Obtenemos los resultados de la consulta
            ResultSet rs = st.executeQuery(sql);
            // Recorremos los resultados
            while(rs.next()){
                Long idAux = rs.getLong("id");
                double totAux = rs.getDouble("total");
                Date fecAux = rs.getDate("fecha");
                Long cliAux = rs.getLong("id_cliente");
                Long comAux = rs.getLong("id_comercial");

                // Localizamos el cliente
                Cliente cliente = new Cliente();
                cliente.setId(cliAux);
                cliente = FactoriaDAO.getClienteDAO().findById(cliente);
                
                // Localizamos el comercial
                Comercial comercial = new Comercial();
                comercial.setId(comAux);
                comercial = FactoriaDAO.getComercialDAO().findById(comercial);
                
                // Suponemos que las relaciones en la BD son correctas
                // Crea el pedido y lo añade a la lista                
                Pedido pedido = new Pedido(idAux, totAux, fecAux, cliente, comercial);
                lista.add(pedido);
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
    public Pedido findById(Pedido object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int add(Pedido pedido) {
        String sql = "INSERT INTO pedido(total, fecha, id_cliente, id_comercial) VALUES (?,?,?,?);";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, pedido.getTotal());
            ps.setDate(2, pedido.getFecha());
            ps.setLong(3, pedido.getCliente().getId());
            ps.setLong(4, pedido.getComercial().getId());          
            int i = ps.executeUpdate();
            ps.close();
            return i;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(Pedido object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void loadPedidosByNombreCliente(Cliente cliente){
    //    String sql = "select a.* from pedido a, cliente b where a.id_cliente=b.id and " +
    //                " b.nombre = ? and b.id = " + String.valueOf(cliente.getId());
    // Consulta optimizada
    String sql = "select a.* from pedido a where a.id_cliente = ?";
        //List<Pedido> lista =  new ArrayList<Pedido>(); No hace falta
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, cliente.getId());
            ResultSet rs = ps.executeQuery();
            Pedido a = null;
            while (rs.next()){
                Long idAux = rs.getLong("id");
                double totAux = rs.getDouble("total");
                Date fecAux = rs.getDate("fecha");
                //Long cliAux = rs.getLong("id_cliente"); No hace falta
                Long comAux = rs.getLong("id_comercial");                
                
                // Localizamos el comercial
                Comercial comercial = new Comercial();
                comercial.setId(comAux);
                comercial = FactoriaDAO.getComercialDAO().findById(comercial);
    
                // Crea el pedido y lo añade a la lista
                a = new Pedido(idAux, totAux, fecAux, cliente, comercial);
                cliente.getListaPedidos().add(a);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }            
    }

    @Override
    public int delete(Pedido object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
