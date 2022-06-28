package com.example.proyetoClinicaOdontologica.repository;

import com.example.proyetoClinicaOdontologica.domain.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoList implements IDAO<Turno>{

    private List<Turno> turnos;
    public TurnoList(){
        turnos = new ArrayList<>();
    }
    @Override
    public List<Turno> listarElementos() {
        return turnos;
    }

    @Override
    public Turno buscarid(Long id) {
        for (Turno turno:turnos) {
            if (turno.getId().equals(id)){
                return turno;
            }
        }
        return null;
    }

    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno actualizar(Turno turno) {
        eliminar(turno.getId());
        turnos.add(turno);
        return null;
    }

    @Override
    public void eliminar(Long id) {
        for (Turno turno:turnos) {
            //Recorre toda la lista, es poco performante
            if (turno.getId().equals(id)){
                turnos.remove(turno);
                return;
            }
        }
    }
}
