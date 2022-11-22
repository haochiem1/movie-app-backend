package com.insert.register.Promo;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PromoService {
    public Promo savePromo(Promo promo);
    public List<Promo> getAllpromo();
}