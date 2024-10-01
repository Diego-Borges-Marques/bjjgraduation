package com.jiujitsu.graduation.controller;

import com.jiujitsu.graduation.domain.Aluno;
import com.jiujitsu.graduation.service.AlunoService;
import com.jiujitsu.graduation.utils.AlunoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/jiujitsu/alunos")
@RestController
public class AlunoController {

    @Autowired
    private AlunoService service;

    @Autowired
    private AlunoUtils utils;

    @PostMapping
    public ResponseEntity<Aluno> criar (@RequestBody Aluno aluno){
        Aluno entity = service.cadastrarAluno(aluno);
        service.criarUsuarioParaAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }



    @PostMapping("/checkin/{id}")
    public ResponseEntity<?> checkin (@PathVariable Long id){
        Aluno aluno = service.buscarAlunoPorId(id);
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
