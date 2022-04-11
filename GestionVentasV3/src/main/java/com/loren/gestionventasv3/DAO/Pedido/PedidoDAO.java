/*
 * Pedidoo change this license header, choose License Headers in Project Properties.
 * Pedidoo change this template file, choose Pedidoools | Pedidoemplates
 * and open the template in the editor.
 */
package com.loren.gestionventasv3.DAO.Pedido;

import com.loren.gestionventasv3.DAO.IOperationsCRUD;
import com.loren.gestionventasv3.POJO.Cliente;
import com.loren.gestionventasv3.POJO.Pedido;
import java.util.List;

/**
 *
 * @author lodiade
 */
public interface PedidoDAO extends IOperationsCRUD<Pedido>{ 
    public void loadPedidosByNombreCliente(Cliente cliente);
}