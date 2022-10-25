package com.insert.register.Address;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address user){
        return addressRepository.save(user);
    }

    @Override
    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }

    @Override
    public Address getAddress(int id){
        return getAllAddresses().stream().filter(a -> a.getAddressID().equals(id)).findFirst().get();
    }
}