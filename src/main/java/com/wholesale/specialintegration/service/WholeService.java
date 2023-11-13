package com.wholesale.specialintegration.service;

import com.wholesale.specialintegration.domain.UsuarioDTO;


public interface WholeService {
    
    Object getWhole(UsuarioDTO request);
    
    Object postWhole(UsuarioDTO request);

    Object putWhole(UsuarioDTO request);
    
    Object deleteWhole(UsuarioDTO request);
   
}
