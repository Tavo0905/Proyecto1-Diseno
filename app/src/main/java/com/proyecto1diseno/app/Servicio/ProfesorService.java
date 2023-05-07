package com.proyecto1diseno.app.Servicio;

import java.util.Collection;

import com.proyecto1diseno.app.Modelo.Profesor;

public class ProfesorService {
    Collection<Profesor> profesores;

    public boolean editarInfo(Profesor profesor){
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
}
