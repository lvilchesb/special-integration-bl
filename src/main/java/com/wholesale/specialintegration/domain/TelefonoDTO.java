package com.wholesale.specialintegration.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class TelefonoDTO {
    
    private Integer phoneId;
    private String numero;
    private String codigoCiudad;
    private String codigoPais;
}
