package com.businessdomain;

import com.validators.javax.AddressCheck;
import com.validators.javax.CheckZipCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Address {
    private static final String CITY_REGEXP = "^[a-zA-Z]+$";

    @Size(min = 4, max = 6, message = "{zipcode.length.not.valid}", groups = {AddressCheck.class})
    @CheckZipCode(AddressType.OFFICE)
    private String zipCode;

    @Pattern(regexp = CITY_REGEXP, message = "{city.not.valid}", groups = {AddressCheck.class})
    private String city;

    private String streetName;
    private String buildingNo;
    private String appNo;
    @Enumerated(EnumType.STRING)
    private AddressType type;

    public Address() {
    }

    public Address(String zipCode, String city, String streetName, AddressType type, String buildingNo, String appNo) {
        this.zipCode = zipCode;
        this.city = city;
        this.streetName = streetName;
        this.type = type;
        this.buildingNo = buildingNo;
        this.appNo = appNo;
    }
}
