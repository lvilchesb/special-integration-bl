package com.wholesale.specialintegration.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wholesale.specialintegration.domain.UsuarioDTO;
import com.wholesale.specialintegration.service.WholeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/whole")
public class WholeController {

    private final WholeService wholeService;
     
    @PostMapping(value = "/postsale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>postWhole(@RequestBody UsuarioDTO request){        
        UsuarioDTO requestSanitized = new UsuarioDTO();
        BeanUtils.copyProperties(request, requestSanitized);
        return new ResponseEntity<>(wholeService.postWhole(requestSanitized),HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/getsale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>getWhole(@RequestBody UsuarioDTO request){        
        UsuarioDTO requestSanitized = new UsuarioDTO();
        BeanUtils.copyProperties(request, requestSanitized);
        return new ResponseEntity<>(wholeService.getWhole(requestSanitized),HttpStatus.OK);
    }
    
    
    @PutMapping(value = "/putsale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>putWhole(@RequestBody UsuarioDTO request){        
        UsuarioDTO requestSanitized = new UsuarioDTO();
        BeanUtils.copyProperties(request, requestSanitized);
        return new ResponseEntity<>(wholeService.putWhole(requestSanitized),HttpStatus.OK);
    }
    
    
    @DeleteMapping(value = "/deletesale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>deleteWhole(@RequestBody UsuarioDTO request){        
        UsuarioDTO requestSanitized = new UsuarioDTO();
        BeanUtils.copyProperties(request, requestSanitized);
        return new ResponseEntity<>(wholeService.deleteWhole(requestSanitized),HttpStatus.OK);
    }
}
