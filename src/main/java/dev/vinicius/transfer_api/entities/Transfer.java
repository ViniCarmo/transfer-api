package dev.vinicius.transfer_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    private Integer id;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "sourceAccount")
    private Integer sourceAccountId;

    @Column(name = "destinationAccount")
    private Integer destinationAccountId;

}
