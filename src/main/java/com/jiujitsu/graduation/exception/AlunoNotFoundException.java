package com.jiujitsu.graduation.exception;


public class AlunoNotFoundException extends RuntimeException {

    public AlunoNotFoundException(Long id){
        super("Aluno não encontrado por ID: "  + id);
    }

    public AlunoNotFoundException(String email){
        super("Aluno não encontrado por email: "  + email);
    }
}
