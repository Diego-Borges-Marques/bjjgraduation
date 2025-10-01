package com.jiujitsu.graduation.domain.dto;

import com.jiujitsu.graduation.domain.Enum.Faixa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoDto(
		@NotBlank
		String nome,
		@Email
		String email,
		@NotBlank
		String cpf,
		@NotNull
		Faixa faixa) {
}
