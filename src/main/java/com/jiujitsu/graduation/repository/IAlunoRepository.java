package com.jiujitsu.graduation.repository;

import com.jiujitsu.graduation.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {
}
