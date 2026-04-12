package dev.vinicius.transfer_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferResponseDto(Integer id,
                                  Integer sourceAccountTitularName,
                                  Integer destinationAccountTitularName,
                                  BigDecimal value,
                                  LocalDateTime timestamp) {
}
