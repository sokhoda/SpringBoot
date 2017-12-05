package businessdomain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import validators.javax.CustomerCheck;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customerId +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", loyaltyCard=" + loyaltyCard +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (name != null ? !name.equals(customer.name) : customer.name != null)
            return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null)
            return false;
        return loyaltyCard != null ? loyaltyCard.equals(customer.loyaltyCard) : customer.loyaltyCard == null;

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long id) {
        this.customerId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoyaltyCard getLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCard(LoyaltyCard loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

}
