package dev.vinicius.transfer_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferResponseDto(Integer id,
                                  Integer sourceAccountTitularId,
                                  Integer destinationAccountTitularId,
                                  BigDecimal value,
                                  LocalDateTime timestamp) {
}
