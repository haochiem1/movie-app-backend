package com.insert.register.Promo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
@Table(name = "promotion")
public class Promo {

    @Id
    @Column(name="promotionID")
    private int promotionID;
    @Column(name="promoCode")
    private String promoCode;
    @Column(name="promoPercent")
    private String promoPercent;


    public Promo(String promoCode, String promoPercent) {
        this.promoCode = promoCode;
        this.promoPercent = promoPercent;
    }

    public Promo()
    {
    }

    public void setpromotionID (int promotionID) {
        this.promotionID= promotionID;
    }

    public void setpromoCode (String promoCode) {
        this.promoCode = promoCode ;
    }

    public void setpromoPercent (String promoPercent) {
        this.promoPercent = promoPercent;
    }



    public Integer getpromotionID() {
        return promotionID;
    }

    public String getpromoCode() {
        return promoCode;
    }

    public String getPromoPercent() {
        return promoPercent;
    }

}
