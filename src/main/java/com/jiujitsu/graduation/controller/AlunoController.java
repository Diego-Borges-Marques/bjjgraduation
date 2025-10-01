package com.jiujitsu.graduation.controller;

import com.jiujitsu.graduation.domain.entity.Aluno;
import com.jiujitsu.graduation.domain.dto.AlunoDto;
import com.jiujitsu.graduation.service.AlunoService;
import com.jiujitsu.graduation.utils.AlunoUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/jiujitsu/alunos")
@RestController
public class AlunoController {

    @Autowired
    private AlunoService service;

    @Autowired
    private AlunoUtils utils;

    @PostMapping("/create")
    public ResponseEntity criar (@RequestBody @Valid AlunoDto alunoDto){
        service.cadastrarAluno(alunoDto);
//        service.criarUsuarioParaAluno(alunoDto);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/checkin")
    public ResponseEntity<?> checkin (@AuthenticationPrincipal UserDetails userDetails){

        Aluno aluno = service.buscarAlunoPorEmail(userDetails.getUsername());
        Aluno entity = service.realizarCheckin(aluno);
        return ResponseEntity.ok(
                "check-in realizado! Agora você possui: "
                        + entity.getCheckin() + " aulas realizadas na faixa "
                        + entity.getFaixa()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar (@PathVariable Long id){
        Aluno aluno = service.buscarAlunoPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listar(){
        List<Aluno> lista = service.getAllAlunos();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/graduar/{id}")
    public ResponseEntity<Aluno> graduar (@PathVariable Long id){
        Aluno aluno = service.buscarAlunoPorId(id);
        Aluno entity = service.graduarFaixa(aluno);
        return ResponseEntity.ok(entity);
    }
}
