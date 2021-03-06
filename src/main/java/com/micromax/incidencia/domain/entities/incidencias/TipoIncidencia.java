package com.micromax.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.domain.entities.ItemLista;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tipo_incidencia")
public class TipoIncidencia extends ItemLista implements Serializable {

    @Transient
    private static final long serialVersionUID = 5L;
}
