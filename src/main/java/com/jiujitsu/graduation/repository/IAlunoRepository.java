package com.jiujitsu.graduation.repository;

import com.jiujitsu.graduation.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByEmail(String email);
}
