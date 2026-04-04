package dev.vinicius.transfer_api.dto;

import java.math.BigDecimal;

public record AccountResponseDto(
    Integer id,
    String titularName,
    BigDecimal balance){

}
