package com.jiujitsu.graduation.repository;

import com.jiujitsu.graduation.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}
