package dev.vinicius.transfer_api.dto;

import java.math.BigDecimal;

public record TransferResponseDto(Integer id,
                                  String sourceAccountTitularName,
                                  String destinationAccountTitularName,
                                  BigDecimal value) {
}
