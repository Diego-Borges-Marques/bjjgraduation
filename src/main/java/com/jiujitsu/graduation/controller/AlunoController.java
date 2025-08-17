package com.jiujitsu.graduation.controller;

import com.jiujitsu.graduation.domain.Aluno;
import com.jiujitsu.graduation.domain.Usuario;
import com.jiujitsu.graduation.repository.IUsuarioRepository;
import com.jiujitsu.graduation.service.AlunoService;
import com.jiujitsu.graduation.utils.AlunoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/jiujitsu/alunos")
@RestController
public class AlunoController {

    @Autowired
    private AlunoService service;

    @Autowired
    private AlunoUtils utils;

    @PostMapping("/create")
    public ResponseEntity<Aluno> criar (@RequestBody Aluno aluno){
        Aluno entity = service.cadastrarAluno(aluno);
        service.criarUsuarioParaAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
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
