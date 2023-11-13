package com.wholesale.specialintegration.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class UsuarioDTO {
    
    private String nombre;
    private String correo;
    private String contrasena;
    private List<TelefonoDTO> telefonos;
    private Integer id;
    private String creado;
    private String modificado;
    private String ultimoLogin;
    private String token;
    private String activo;
}
