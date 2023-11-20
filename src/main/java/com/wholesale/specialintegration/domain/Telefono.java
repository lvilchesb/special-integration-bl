package com.wholesale.specialintegration.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Telefono")
@Table(name = "telefono")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "phoneId")
public class Telefono  implements Serializable {
    private static final long serialVersionUID = -954777490373922121L;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer phoneId;
    private String numero;
    private String codigoCiudad;
    private String codigoPais;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id")
    private Usuario userId;
}
