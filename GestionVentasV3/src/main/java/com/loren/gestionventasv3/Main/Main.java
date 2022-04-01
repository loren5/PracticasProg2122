/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loren.gestionventasv3.Main;

import com.loren.gestionventasv3.DAO.ConexionBD;
import com.loren.gestionventasv3.DAO.FactoriaDAO;
import com.loren.gestionventasv3.POJO.Cliente;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author profesor
 */
public class Main {

    private static FactoriaDAO factoriaDAO;
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        int menuVentas = -1;
        try {
            while (menuVentas != 4) {
                menuVentas();
                menuVentas = Integer.parseInt(teclado.nextLine());
                switch (menuVentas) {
                    case 1: {
                        int menuCliente = -1;
                        while (menuCliente != 8) {
                            menuClientes();
                            menuCliente = Integer.parseInt(teclado.nextLine());
                            switch (menuCliente) {
                                case 1: {
                                    System.out.println("Dime el nombre del cliente: ");
                                    String nomAux = teclado.nextLine();
                                    System.out.println("Dime el apellido1 del cliente: ");
                                    String ape1Aux = teclado.nextLine();
                                    System.out.println("Dime el apellido2 del cliente: ");
                                    String ape2Aux = teclado.nextLine();
                                    System.out.println("Dime la ciudad del cliente: ");
                                    String ciuAux = teclado.nextLine();
                                    System.out.println("Dime la categoria del cliente: ");
                                    int catAux = Integer.valueOf(teclado.nextLine());
                                    Cliente a = new Cliente();
                                    a.setNombre(nomAux);
                                    a.setApellido1(ape1Aux);
                                    a.setApellido2(ape2Aux);
                                    a.setCiudad(ciuAux);
                                    a.setCategoria(catAux);
                                    int i = factoriaDAO.getClienteDAO().add(a);
                                    if (i > 0) {
                                        System.out.println("Se ha insertado " + i + " clientes.");
                                    } else {
                                        System.out.println("No se han insertado el cliente.");
                                    }
                                    break;
                                }
                                // borrar cliente
                                case 2: {
                                    mostrarClientes();
                                    Cliente a = buscarCliente("Dime el id del cliente a borrar: ");
                                    if(a!=null){
                                        System.out.println("¿Estas seguro de borrar el cliente (S/N):");
                                        System.out.println(a.toString());
                                        String  respuesta = teclado.nextLine();
                                        if(respuesta.equals("S")){
                                            int i = factoriaDAO.getClienteDAO().delete(a);
                                            if (i > 0) {
                                                System.out.println("Se ha borrado " + i + " clientes.");
                                            } else {
                                                System.out.println("No se ha borrado el cliente.");
                                            }
                                        }
                                    }
                                    break;                                    
                                }                                
                                // Actualizar cliente
                                case 3: {
                                    mostrarClientes();
                                    Cliente a = buscarCliente("Dime el id del cliente que vas a modificar: ");
                                    if(a!=null){
                                        System.out.println("Dime el nombre del cliente: ");
                                        String nomAux = teclado.nextLine();
                                        System.out.println("Dime el apellido1 del cliente: ");
                                        String ape1Aux = teclado.nextLine();
                                        System.out.println("Dime el apellido2 del cliente: ");
                                        String ape2Aux = teclado.nextLine();
                                        System.out.println("Dime la ciudad del cliente: ");
                                        String ciuAux = teclado.nextLine();
                                        System.out.println("Dime la categoria del cliente: ");
                                        String catAux = teclado.nextLine();         
                                        
                                        // Comprobamos que ha habido modificaciones
                                        a.setNombre((nomAux.trim().length() >0)? nomAux : a.getNombre());
                                        a.setApellido1((ape1Aux.trim().length() >0)? ape1Aux : a.getApellido1());
                                        a.setApellido2((ape2Aux.trim().length() >0)? ape2Aux : a.getApellido2());
                                        a.setCiudad((ciuAux.trim().length() >0)? ciuAux : a.getCiudad());
                                        a.setCategoria((catAux.trim().length() >0)? Integer.valueOf(catAux) : a.getCategoria());
                                       
                                        int i = factoriaDAO.getClienteDAO().update(a);
                                        if (i > 0) {
                                            System.out.println("Se ha actualizado " + i + " clientes.");
                                        } else {
                                            System.out.println("No se ha actualizado el cliente.");
                                        } 
                                    }
                                    break;
                                }
                                // Buscar cliente por id
                                case 4: {
                                    Cliente a = buscarCliente("Dime el id del cliente: ");
                                    if (a == null) {
                                        System.out.println("Cliente no encontrado");
                                    } else {
                                        System.out.println(a.toString());
                                    }
                                    break;
                                }
                                case 5: {
                                    System.out.println("Dime el nombre del cliente: ");
                                    String nomAux = teclado.nextLine();
                                    Cliente a = new Cliente();
                                    a.setNombre(nomAux);
                                    List<Cliente> lista = factoriaDAO.getClienteDAO().findByNombre(a);
                                    if ((lista != null) && (!lista.isEmpty())) {
                                        System.out.println("********************");
                                        System.out.println("*** LISTA DE CLIENTES POR NOMBRE ****");
                                        System.out.println("********************");
                                        for (Cliente cliente : lista) {
                                            System.out.println(cliente.toString());
                                        }
                                    } else {
                                        System.out.println("No es posible mostrar la lista de clientes.");
                                    }
                                    break;
                                }
                                case 6: {
                                    System.out.println("Dime el apellido del cliente: ");
                                    String ape1Aux = teclado.nextLine();
                                    Cliente a = new Cliente();
                                    a.setApellido1(ape1Aux);
                                    List<Cliente> lista = factoriaDAO.getClienteDAO().findByApellido(a);
                                    if ((lista != null) && (!lista.isEmpty())) {
                                        System.out.println("********************");
                                        System.out.println("*** LISTA DE CLIENTES POR APELLIDO ****");
                                        System.out.println("********************");
                                        for (Cliente cliente : lista) {
                                            System.out.println(cliente.toString());
                                        }
                                    } else {
                                        System.out.println("No es posible mostrar la lista de clientes.");
                                    }
                                    break;
                                }
                                case 7: {
                                    mostrarClientes();
                                    break;
                                }
                                case 8: {
                                    break;
                                }
                                default: {
                                    System.out.println("Opción no válida");
                                }
                            }
                        }
                        break;
                    }
                    case 4: {
                        break;
                    }
                    default: {
                        System.out.println("Opción incorrecta.");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionBD.closeConnection();
        }
    }

    public static void menuVentas() {
        System.out.println("********************");
        System.out.println("Menú de ventas");
        System.out.println("********************");
        System.out.println("1.- Gestión de clientes.");
        System.out.println("2.- Gestión de comerciales.");
        System.out.println("3.- Gestión de pedidos.");
        System.out.println("4.- Salir.");
    }

    public static void menuClientes() {
        System.out.println("********************");
        System.out.println("Menú de clientes");
        System.out.println("********************");
        System.out.println("1.- Alta de cliente.");
        System.out.println("2.- Baja de cliente.");
        System.out.println("3.- Actualizar cliente.");
        System.out.println("4.- Buscar cliente por ID.");
        System.out.println("5.- Buscar cliente por nombre.");
        System.out.println("6.- Buscar cliente por apellido con like.");
        System.out.println("7.- Listar clientes.");
        System.out.println("8.- Salir.");
    }

    private static void mostrarClientes() {
        List<Cliente> lista = factoriaDAO.getClienteDAO().getAll();
        if ((lista != null) && (!lista.isEmpty())) {
            System.out.println("********************");
            System.out.println("*** LISTA DE CLIENTES ****");
            System.out.println("********************");
            for (Cliente cliente : lista) {
                System.out.println(cliente.toString());
            }
        } else {
            System.out.println("No es posible mostrar la lista de clientes.");
        }
    }

    private static Cliente buscarCliente(String mensaje) {
        System.out.println(mensaje);
        Long idAux = Long.valueOf(teclado.nextLine());
        Cliente a = new Cliente();
        a.setId(idAux);
        a = factoriaDAO.getClienteDAO().findById(a);
        return a;
    }
}
