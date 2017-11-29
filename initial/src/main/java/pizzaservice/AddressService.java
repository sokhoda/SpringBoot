package pizzaservice;

import businessdomain.Address;

import java.util.List;

public interface AddressService {
    Address find(Long id);

    List<Address> findByCityName(String city);

    Address save(Address address);
}