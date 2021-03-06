package com.micromax.incidencia.dto;

import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.TiempoEstimado;
import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.incidencias.TipoIncidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IncidenciaDTO {

    private String id;
    private String titulo;
    private String descripcion;
    private Categoria categoria;
    private Status status;
    private TipoIncidencia tipoIncidencia;
    private TiempoEstimado tiempoEstimado;
    private long categoriaId;
    private List<Tecnico> asignados;
    private String comentario;
    private Usuario creador;

    public IncidenciaDTO(){}

    public IncidenciaDTO(Incidencia incidencia){
        id = incidencia.getIdIncidencia();
        titulo = incidencia.getTitulo();
        descripcion = incidencia.getDescripcion();
        categoria = incidencia.getCategoria();
        status = incidencia.getStatus();
        tipoIncidencia = incidencia.getTipoIncidencia();
        asignados = new ArrayList<>();
        asignados.addAll(incidencia.getAsignados());
        tiempoEstimado = incidencia.getTiempoEstimado();
        creador = incidencia.getCreador();
    }

}
