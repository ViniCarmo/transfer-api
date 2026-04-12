    package dev.vinicius.transfer_api.dto;

    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Positive;

    import java.math.BigDecimal;

    public record TransferRequestDto(
            @NotNull(message = "Source account titular ID is required")
            Integer sourceAccountTitularId,

            @NotNull(message = "Destination account titular ID is required")
            Integer destinationAccountTitularId,

            @NotNull(message = "Transfer value is required")
            @Positive(message = "Transfer value must be greater than zero")
            BigDecimal value) {
    }
