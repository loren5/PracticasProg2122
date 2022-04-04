package com.loren.gestionventasv3.DAO;

public class FactoriaDAO {
    
    private static ClienteDAO clienteDAO = null;
    private static ComercialDAO comerciaDAO = null;
    private static PedidoDAO pedidoDAO = null;
    
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
    
    public static PedidoDAO getPedidoDAO(){
        if(pedidoDAO == null){
            pedidoDAO = new PedidoDAO();
        }
        return pedidoDAO;
    }      
}
