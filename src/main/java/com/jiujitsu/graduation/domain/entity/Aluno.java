package com.jiujitsu.graduation.domain.entity;


import com.jiujitsu.graduation.domain.Enum.Faixa;
import com.jiujitsu.graduation.domain.dto.AlunoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_aluno")
public class Aluno implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", unique = false, nullable = false)
    private String nome;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    private String cpf;
    @Column(name = "faixa", unique = false, nullable = false)
    @Enumerated
    private Faixa faixa;
    @Column(name = "checkins")
    private int checkin;

    public Aluno(AlunoDto alunoDto){
		nome = alunoDto.nome();
        email = alunoDto.email();
        cpf = alunoDto.cpf();
        faixa = alunoDto.faixa();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", faixa=" + faixa +
                '}';
    }
}
