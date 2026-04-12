package dev.vinicius.transfer_api.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountRequestDto(
        @NotBlank(message = "Titular name is required")
        String titularName) {
}
