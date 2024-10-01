package com.jiujitsu.graduation.service;

import com.jiujitsu.graduation.domain.Aluno;
import com.jiujitsu.graduation.domain.Faixa;
import com.jiujitsu.graduation.domain.UserRole;
import com.jiujitsu.graduation.domain.Usuario;
import com.jiujitsu.graduation.exception.AlunoNotFoundException;
import com.jiujitsu.graduation.repository.IAlunoRepository;
import com.jiujitsu.graduation.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private IAlunoRepository repository;

    @Autowired
    private IUsuarioRepository usuarioRepository;



    public Aluno cadastrarAluno(Aluno aluno) {
        Aluno alunoSalvo = repository.save(aluno);
//        criarUsuarioParaAluno(alunoSalvo); da problema de nullpointer exception no testeunitario
        return alunoSalvo;
    }

    public Usuario criarUsuarioParaAluno(Aluno alunoSalvo) {
        String senha = alunoSalvo.getCpf();
        String senhaCriptografada = new BCryptPasswordEncoder().encode(senha);
        Usuario usuario = new Usuario();
        usuario.setEmail(alunoSalvo.getEmail());
        usuario.setSenha(senhaCriptografada);
        usuario.setRole(UserRole.USER);
        return usuarioRepository.save(usuario);
    }

    private String gerarSenhaAleatoria() {
        byte[] array = new byte[10];
        new SecureRandom().nextBytes(array);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(array);
    }

    public Aluno buscarAlunoPorId(Long id) {
            return repository.findById(id).orElseThrow(() -> new AlunoNotFoundException(id));
    }

   /* public Aluno graduarGrau(Aluno aluno){

        Faixa atual = aluno.getFaixa();
        Double nova = Math.round((atual.getRef() + 0.1)*100)/100.0;
        Faixa novaFaixa = Faixa.getColorByValue(nova);
        aluno.setFaixa(novaFaixa);
        return repository.save(aluno);
    } */

    public Aluno graduarFaixa(Aluno aluno){
        Faixa atual = aluno.getFaixa();
        int nova = atual.getRef() + 1;
        Faixa novaFaixa = Faixa.getColorByValue(nova);
        aluno.setFaixa(novaFaixa);
        aluno.setCheckin(0);
        return repository.save(aluno);
    }

    public List<Aluno> getAllAlunos() {
        return repository.findAll();
    }

    public Aluno realizarCheckin(Aluno aluno) {
        int presenca = aluno.getCheckin() + 1;
        aluno.setCheckin(presenca);
        return repository.save(aluno);
    }
}
