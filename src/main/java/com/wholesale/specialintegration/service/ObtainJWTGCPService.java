package com.wholesale.specialintegration.service;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.wholesale.specialintegration.exception.ResultCodes;
import com.wholesale.specialintegration.exception.WholeException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ObtainJWTGCPService{

    @Value("whole.issuer")
    private String issuer;
    @Value("whole.audience")
    private String audience;
    @Value("whole.email")
    private String email;
    @Value("whole.time")
    private String time;


    public String obtainJWTGCP() throws WholeException {
         try {
             LocalDateTime now = LocalDateTime.now();
             // add seconds to expire token
             LocalDateTime expTime = LocalDateTime.now().plusMinutes(Integer.valueOf(time));

             JWTCreator.Builder token = JWT.create().withIssuedAt(Timestamp.valueOf(now))
                     .withExpiresAt(Timestamp.valueOf(expTime)).withIssuer(issuer).withAudience(audience)
                     .withSubject(issuer).withClaim(email, issuer);

             ServiceAccountCredentials cred = (ServiceAccountCredentials) GoogleCredentials.getApplicationDefault();
             RSAPrivateKey key = (RSAPrivateKey) cred.getPrivateKey();
             Algorithm algorithm = Algorithm.RSA256(null, key);

             return token.sign(algorithm);
         } catch (IOException ex) {
             throw new WholeException(ResultCodes.ERROR.getCode(), ex.getMessage());
         }
    }
       
}
