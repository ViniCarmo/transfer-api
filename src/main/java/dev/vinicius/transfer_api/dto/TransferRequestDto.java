package dev.vinicius.transfer_api.dto;

public record TransferRequestDto(String sourceAccountTitularName,
                                 String destinationAccountTitularName,
                                 String value) {
}
