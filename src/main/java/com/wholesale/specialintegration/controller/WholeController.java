package com.wholesale.specialintegration.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wholesale.specialintegration.domain.ErrorMessage;
import com.wholesale.specialintegration.domain.TokenInformation;
import com.wholesale.specialintegration.domain.Usuario;
import com.wholesale.specialintegration.domain.UsuarioDTO;
import com.wholesale.specialintegration.exception.WholeException;
import com.wholesale.specialintegration.service.ObtainJWTGCPService;
import com.wholesale.specialintegration.service.WholeService;

import lombok.RequiredArgsConstructor;

@RestController
//@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/whole")
public class WholeController {

    private final WholeService wholeService;
    private final ObtainJWTGCPService jwt;
    @Value("${spring.message.apikey}")
    private String messageErrorApikey;
    @Value("${spring.message.token}")
    private String messageErrorToken;
    
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>tokenWhole( @RequestHeader(value = "api_key", required = true) String apiKey){ 
       //Para pruebas validaremos apikey aqui 
       if(!apiKey.equalsIgnoreCase("APIkeyWhole")) {
           ErrorMessage error= new ErrorMessage();     
           error.setMessage(messageErrorApikey);
           return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
       }   
       TokenInformation token= new TokenInformation();
       token.setToken("toKenWhOlE");
       //se comenta metodo para pruebas
  //     token.setToken(jwt.obtainJWTGCP());
       
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
     
    @PostMapping(value = "/postsale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>postWhole(@RequestBody UsuarioDTO request, @RequestHeader(value = "authorization", required = true) String authentication) throws WholeException{        
        //validamos token manual para pruebas
        if(!authentication.equalsIgnoreCase("Bearer"+" toKenWhOlE")) {
            ErrorMessage error= new ErrorMessage();     
            error.setMessage(messageErrorToken);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }           
        Usuario requestSanitized = new Usuario();
        request.setToken(authentication);
        request.setCreado(new Date().toString());
        request.setUltimoLogin(new Date().toString());
        BeanUtils.copyProperties(request, requestSanitized);
        return new ResponseEntity<>(wholeService.postWhole(requestSanitized),HttpStatus.OK);
    }
       
    @GetMapping(value = "/getsale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>getWhole(@RequestBody UsuarioDTO request, @RequestHeader(value = "authorization", required = true) String authentication) throws WholeException{        
        if(!authentication.equalsIgnoreCase("Bearer"+" toKenWhOlE"))  {
            ErrorMessage error= new ErrorMessage();     
            error.setMessage(messageErrorToken);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }             
        Usuario requestSanitized = new Usuario();
        BeanUtils.copyProperties(request, requestSanitized);
        return new ResponseEntity<>(wholeService.getWhole(requestSanitized),HttpStatus.OK);
    }
        
    @PutMapping(value = "/putsale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>putWhole(@RequestBody UsuarioDTO request, @RequestHeader(value = "authorization", required = true) String authentication) throws WholeException{        
        if(!authentication.equalsIgnoreCase("Bearer"+" toKenWhOlE"))  {
            ErrorMessage error= new ErrorMessage();     
            error.setMessage(messageErrorToken);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        request.setUltimoLogin(new Date().toString());
        request.setModificado(new Date().toString());
        Usuario requestSanitized = new Usuario();
        BeanUtils.copyProperties(request, requestSanitized);
        return new ResponseEntity<>(wholeService.putWhole(requestSanitized),HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/deletesale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>deleteWhole(@RequestBody UsuarioDTO request, @RequestHeader(value = "authorization", required = true) String authentication) throws WholeException{        
        if(!authentication.equalsIgnoreCase("Bearer"+" toKenWhOlE"))  {
            ErrorMessage error= new ErrorMessage();     
            error.setMessage(messageErrorToken);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }     
        Usuario requestSanitized = new Usuario();
        BeanUtils.copyProperties(request, requestSanitized);
        return new ResponseEntity<>(wholeService.deleteWhole(requestSanitized),HttpStatus.OK);
    }
}
