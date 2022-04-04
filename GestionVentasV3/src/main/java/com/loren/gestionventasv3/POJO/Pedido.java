package com.loren.gestionventasv3.POJO;

import java.sql.Date;


public class Pedido {
    
    private Long id;
    private double total;
    private Date fecha;
    //private Long idCliente;
    //private Long idComercial;
    private Cliente cliente;
    private Comercial comercial;

    public Pedido() {
    }

    public Pedido(Long id, double total, Date fecha, Cliente cliente, Comercial comercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comercial = comercial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comercial getComercial() {
        return comercial;
    }

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", total=" + total + ", fecha=" + fecha + ", cliente=" + cliente + ", comercial=" + comercial + '}';
    }
    
    
    
}
