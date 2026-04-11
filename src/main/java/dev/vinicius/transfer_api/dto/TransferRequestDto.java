package dev.vinicius.transfer_api.dto;

import java.math.BigDecimal;

public record TransferRequestDto(Integer sourceAccountTitularId,
                                 Integer destinationAccountTitularId,
                                 BigDecimal value) {
}
