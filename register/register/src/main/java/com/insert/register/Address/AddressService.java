package com.insert.register.Address;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    public Address saveAddress(Address address);
    public List<Address> getAllAddresses();
    public Address getAddress(int id);
    public Address updateAddress(int id, String street, String aptNum, String city, String state, String zipcode);
}