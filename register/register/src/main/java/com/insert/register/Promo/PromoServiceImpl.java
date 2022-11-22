package com.insert.register.Promo;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoRepository promoRepository;

    @Override
    public Promo savePromo(Promo user){
        return promoRepository.save(user);
    }

    @Override
    public List<Promo> getAllpromo(){
        return promoRepository.findAll();
    }


}