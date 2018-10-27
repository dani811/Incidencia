package com.micromax.incidencia.domain.entities.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("C")
public class Cliente extends Usuario implements Serializable {

    @Transient
    private static final long serialVersionUID = 6L;

    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "denom_comercial")
    private String denominacionComercial;

    @Column(name = "rif")
    @Length(max = 10, min = 10)
    private String rif;

}
