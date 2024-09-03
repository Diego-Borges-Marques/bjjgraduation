package com.jiujitsu.graduation;

import com.jiujitsu.graduation.domain.Aluno;
import com.jiujitsu.graduation.domain.Faixa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraduationApplication {


	public static void main(String[] args) {
		SpringApplication.run(GraduationApplication.class, args);

		Aluno aluno = new Aluno();

		aluno.setFaixa(Faixa.getColorByValue(1));
		aluno.setNome("Diego");
		aluno.setCpf("0000000");
		System.out.println(aluno);
		System.out.println(aluno);


	}
}
