package com.jiujitsu.graduation.service;

import com.jiujitsu.graduation.domain.Aluno;
import com.jiujitsu.graduation.domain.Faixa;
import com.jiujitsu.graduation.repository.IAlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private IAlunoRepository repository;

    private Aluno aluno1;
    private Aluno aluno2;

    @BeforeEach
    public void setUp() {
        aluno1 = new Aluno();
        aluno1.setNome("Diego");
        aluno1.setEmail("diego@email.com");
        aluno1.setFaixa(Faixa.BRANCA);
        aluno1.setCpf("48467709820");

        aluno2 = new Aluno();
        aluno2.setNome("Bob");
        aluno2.setEmail("bob@example.com");
        aluno2.setFaixa(Faixa.ROXA);
        aluno1.setCpf("48467709821");


    }

    @Test
    public void testCadastrarAlunoSuccess() {
        when(repository.save((any(Aluno.class)))).thenReturn(aluno1);

        Aluno result = alunoService.cadastrarAluno(aluno1);

        assertEquals(aluno1, result);
        assertNotNull(result);
        assertEquals("Diego", result.getNome());
        assertEquals("diego@email.com", result.getEmail());
        verify(repository, times(1)).save(aluno1);

    }

}