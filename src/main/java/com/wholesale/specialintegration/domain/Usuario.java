package com.wholesale.specialintegration.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Usuario")
@Table(name = "usuario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario implements Serializable {
    private static final long serialVersionUID = -954777490373922121L;
    
    @Id 
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String nombre;
    private String correo;
    private String contrasena;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "userId",  cascade = CascadeType.ALL)
    private List<Telefono> telefonos = new ArrayList<>();
    private String creado;
    private String modificado;
    private String ultimoLogin;
    private String token;
    private String activo;
    
}
