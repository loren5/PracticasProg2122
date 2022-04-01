/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.loren.gestionventasv3.DAO;

import java.util.List;

/**
 *
 * @author profesor
 */
public interface IOperationsCRUD<T> {
    public List<T> getAll();
    public T findById(T object);
    public int add(T object);
    public int update(T object);
    public int delete(T object);
}
