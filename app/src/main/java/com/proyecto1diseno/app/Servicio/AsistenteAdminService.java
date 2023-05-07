package com.proyecto1diseno.app.Servicio;

import java.util.Collection;

import com.proyecto1diseno.app.Modelo.Actividad;
import com.proyecto1diseno.app.Modelo.EquipoGuia;
import com.proyecto1diseno.app.Modelo.PlanTrabajo;
import com.proyecto1diseno.app.Modelo.Profesor;

public class AsistenteAdminService {

    EquipoGuia equipoGuia;
    Actividad actividad;
    PlanTrabajo planTrabajo;
    Collection<Profesor> profesores;

    public boolean registrarNuevoIngreso(Profesor profesor){
        if(profesor == null){
            return false;
        }
        for (Profesor prof : profesores ){
            if (prof.getCodigo() == profesor.getCodigo()){
                return false;
            }
        }
        profesores.add(profesor);
        return true;
    }
    
    public boolean darDeBaja(Profesor profesor){
        //Hay que poner algo para señalar a los que están dados de baja porque
        //no se deben eliminar del todo
        return true;
    }

    public boolean editarMiembro (Profesor profesor){
        if(profesor == null){
            return false;
        }
        for (Profesor prof : profesores){
            if (prof.getCodigo() == profesor.getCodigo()){
                prof.setCelular(profesor.getCelular());
                prof.setFotografia(profesor.getFotografia());
                prof.setNombre(profesor.getNombre());
                prof.setTelOficina(profesor.getTelOficina());
                return true;
            }
        }
        return false;
    }

    public EquipoGuia obtenerDetalleEquipo(){
        return equipoGuia;
    }

    public boolean nombrarCoordinador(Profesor profesor){
        if(profesor == null){
            return false;
        }
        for (Profesor prof : profesores){
            if (prof.getCodigo() == profesor.getCodigo()){
                prof.setCoordinador(true);
                return true;
            }
        }
        return false;
    }

    public PlanTrabajo consultarPlanTrabajo(){
        return planTrabajo;
    }

    public Actividad verDetalleProxAct(){
        return actividad;
    }
}
