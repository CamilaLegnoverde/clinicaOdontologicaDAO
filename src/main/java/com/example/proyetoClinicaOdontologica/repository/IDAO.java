package com.example.proyetoClinicaOdontologica.repository;

import java.util.List;

public interface IDAO <T>{
    List<T> listarElementos();
    T buscarid(Long id);
    T guardar(T t);
    T actualizar(T t);

    void eliminar(Long id);

}
