package com.loren.gestionventasv3.DAO;

import com.loren.gestionventasv3.DAO.Pedido.PedidoDAOImpl;

public class FactoriaDAO {
    
    private static ClienteDAO clienteDAO = null;
    private static ComercialDAO comerciaDAO = null;
    private static PedidoDAOImpl pedidoDAO = null;
    
    public static ClienteDAO getClienteDAO(){
        if(clienteDAO == null){
            clienteDAO = new ClienteDAO();
        }
        return clienteDAO;
    }
    
    
    public static ComercialDAO getComercialDAO(){
        if(comerciaDAO == null){
            comerciaDAO = new ComercialDAO();
        }
        return comerciaDAO;
    }    
    
    public static PedidoDAOImpl getPedidoDAO(){
        if(pedidoDAO == null){
            pedidoDAO = new PedidoDAOImpl();
        }
        return pedidoDAO;
    }      
}
