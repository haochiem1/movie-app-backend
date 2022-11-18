package com.insert.register.Category;

import java.lang.management.ThreadInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryID;

    @Column(name="action")
    private Integer action;
    @Column(name="adult")
    private Integer adult;
    @Column(name="adventure")
    private Integer adventure;
    @Column(name="anime")
    private Integer anime;
    @Column(name="experimental")
    private Integer experimental;
    @Column(name="children")
    private Integer children;
    @Column(name="comedy")
    private Integer comedy;
    @Column(name="comedyDrama")
    private Integer comedyDrama;
    @Column(name="crime")
    private Integer crime;
    @Column(name="drama")
    private Integer drama;
    @Column(name="epic")
    private Integer epic;
    @Column(name="fantasy")
    private Integer fantasy;
    @Column(name="historical")
    private Integer historical;
    @Column(name="horror")
    private Integer horror;
    @Column(name="musical")
    private Integer musical;
    @Column(name="mystery")
    private Integer mystery;
    @Column(name="romance")
    private Integer romance;
    @Column(name="scienceFiction")
    private Integer scienceFiction;
    @Column(name="spy")
    private Integer spy;
    @Column(name="thriller")
    private Integer thriller;
    @Column(name="war")
    private Integer war;
    @Column(name="western")
    private Integer western;

    public Category(Integer action, Integer adult, Integer adventure, Integer anime, Integer experimental, Integer children, Integer comedy, Integer comedyDrama, Integer crime, Integer drama, Integer epic, Integer fantasy, Integer historical, Integer horror, Integer musical, Integer mystery, Integer romance, Integer scienceFiction, Integer spy, Integer thriller, Integer war, Integer western) {
        this.action = action;
        this.adult = adult;
        this.adventure = adventure;
        this.anime = anime;
        this.experimental = experimental;
        this.children = children;
        this.comedy = comedy;
        this.comedyDrama = comedyDrama;
        this.crime = crime;
        this.drama = drama;
        this.epic = epic;
        this.fantasy = fantasy;
        this.historical = historical;
        this.horror = horror;
        this.musical = musical;
        this.mystery = mystery;
        this.romance = romance;
        this.scienceFiction = scienceFiction;
        this.spy = spy;
        this.thriller = thriller;
        this.war = war;
        this.western = western;
    }

    public Category() {}

    //Setters
    public void setId(Integer id) {
        this.categoryID = id;
    }

    public void setAction(Integer x) {
        this.action = x;
    }

    public void setAdult(Integer x) {
        this.adult = x;
    }

    public void setAdventure(Integer x) {
        this.adventure = x;
    }

    public void setAnime(Integer x) {
        this.anime = x;
    }

    public void setExperimental(Integer x) {
        this.experimental = x;
    }

    public void setChildren(Integer x) {
        this.children = x;
    }

    public void setComedy(Integer x) {
        this.comedy = x;
    }

    public void setComedyDrama(Integer x) {
        this.comedyDrama = x;
    }

    public void setCrime(Integer x) {
        this.crime = x;
    }

    public void setDrama(Integer x) {
        this.drama = x;
    }

    public void setEpic(Integer x) {
        this.epic = x;
    }

    public void setFantasy(Integer x) {
        this.fantasy = x;
    }

    public void setHistorical(Integer x) {
        this.historical = x;
    }

    public void setHorror(Integer x) {
        this.horror = x;
    }

    public void setMusical(Integer x) {
        this.musical = x;
    }

    public void setRomance(Integer x) {
        this.romance = x;
    }

    public void setScienceFiction(Integer x) {
        this.scienceFiction = x;
    }

    public void setSpy(Integer x) {
        this.spy = x;
    }

    public void setThriller(Integer x) {
        this.thriller = x;
    }

    public void setWar(Integer x) {
        this.war = x;
    }

    public void setWestern(Integer x) {
        this.western = x;
    }

    //Getters

    public Integer getCategoryID() {
        return categoryID;
    }

    public Integer getAction() {
        return action;
    }

    public Integer getAdult() {
        return adult;
    }

    public Integer getAdventure() {
        return adventure;
    }

    public Integer getAnime() {
        return anime;
    }

    public Integer getExperimental() {
        return experimental;
    }

    public Integer getChildren() {
        return children;
    }

    public Integer getComedy() {
        return comedy;
    }

    public Integer getComedyDrama() {
        return comedyDrama;
    }

    public Integer getCrime() {
        return crime;
    }

    public Integer getDrama() {
        return drama;
    }

    public Integer getEpic() {
        return epic;
    }

    public Integer getFantasy() {
        return fantasy;
    }

    public Integer getHistorical() {
        return historical;
    }

    public Integer getHorror() {
        return horror;
    }

    public Integer getMusical() {
        return musical;
    }

    public Integer getMystery() {
        return mystery;
    }

    public Integer getRomance() {
        return romance;
    }

    public Integer getScienceFiction() {
        return scienceFiction;
    }

    public Integer getSpy() {
        return spy;
    }

    public Integer getThriller() {
        return thriller;
    }

    public Integer getWar() {
        return war;
    }

    public Integer getWestern() {
        return western;
    }
}

