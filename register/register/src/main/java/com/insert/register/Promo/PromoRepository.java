package com.insert.register.Promo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Integer>{
}
