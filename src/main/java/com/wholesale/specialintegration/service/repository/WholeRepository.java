package com.wholesale.specialintegration.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wholesale.specialintegration.domain.Usuario;

public interface WholeRepository extends JpaRepository<Usuario, Integer> {
   
    @Query(value = "SELECT * FROM usuario WHERE correo = :correo", nativeQuery = true)
    Usuario searchEmail(@Param("correo") String correo);
}
