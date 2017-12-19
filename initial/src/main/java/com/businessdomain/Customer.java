package com.businessdomain;

import com.validators.javax.CustomerCheck;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Customer implements Serializable {
    private static final String EMAIL_PATTERN = ".+@.+\\.[a-z]+";

    @Id
    @TableGenerator(
            name = "customerGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "CUSTOMER_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customerGen")
    private Long customerId;

    @NotBlank(groups = {CustomerCheck.class})
    private String name;

    @Pattern(regexp = EMAIL_PATTERN, message = "{email.not.valid}", groups = {CustomerCheck.class})
    private String email;

    @Embedded
    @Valid
    private Address address;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "LoyalCard_ID")
    private LoyaltyCard loyaltyCard;


    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }


    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, LoyaltyCard loyaltyCard) {
        this.name = name;
        this.loyaltyCard = loyaltyCard;
    }
}
