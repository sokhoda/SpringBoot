package com.businessdomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoyaltyCard implements Serializable {
    @Id
    @TableGenerator(
            name = "LOYALTYCARDGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "LCARD_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "LOYALTYCARDGen")
    private Long id;

    @OneToOne(mappedBy = "loyaltyCard")
    private Customer customer;

    private Double sum;

    public LoyaltyCard() {
    }

    public LoyaltyCard(Double sum) {
        this.sum = sum;
    }
}
