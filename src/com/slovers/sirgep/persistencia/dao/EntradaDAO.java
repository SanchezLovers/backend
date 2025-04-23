/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slovers.sirgep.persistencia.dao;
import com.slovers.sirgep.dominio.models.ventas.Entrada;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author willi
 */
public interface EntradaDAO {
    void insertar(Entrada entrada) throws SQLException, IOException;
    void actualizar(Entrada entrada) throws SQLException, IOException;
    void eliminar(int id) throws SQLException, IOException;
    ArrayList<Entrada> obtenerTodos() throws SQLException, IOException;
    Entrada obtenerPorId(int id) throws SQLException, IOException;
}
