package com.insert.register.Category;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    public Integer saveAddress(int x) {
        return 1;
    }
    
    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void removeCategory(int id) {
        Category movie = getAllCategories().stream().filter(a -> a.getCategoryID().equals(id)).findFirst().get();
        categoryRepository.delete(movie);
    }

    @Override
    public Category getCategory(int id){
        return categoryRepository.findAll().stream().filter(a -> a.getCategoryID().equals(id)).findFirst().get();
    }

    @Override
    public List<Category> getMovieWithCategory(String cat){
        String loweredCat = cat.toLowerCase();
        List<Category> categories = new ArrayList<>();
        if (loweredCat.equals("action")) {
            return categoryRepository.findAll().stream().filter(a -> a.getAction().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("adult")) {
            return categoryRepository.findAll().stream().filter(a -> a.getAdult().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("adventure")) {
            return categoryRepository.findAll().stream().filter(a -> a.getAdventure().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("anime")) {
            return categoryRepository.findAll().stream().filter(a -> a.getAnime().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("experimental")) {
            return categoryRepository.findAll().stream().filter(a -> a.getExperimental().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("epic")) {
            return categoryRepository.findAll().stream().filter(a -> a.getEpic().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("children")) {
            return categoryRepository.findAll().stream().filter(a -> a.getChildren().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("comedy")) {
            return categoryRepository.findAll().stream().filter(a -> a.getComedy().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("comedy drama")) {
            return categoryRepository.findAll().stream().filter(a -> a.getComedyDrama().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("crime")) {
            return categoryRepository.findAll().stream().filter(a -> a.getCrime().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("drama")) {
            return categoryRepository.findAll().stream().filter(a -> a.getDrama().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("fantasy")) {
            return categoryRepository.findAll().stream().filter(a -> a.getFantasy().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("historical")) {
            return categoryRepository.findAll().stream().filter(a -> a.getHistorical().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("horror")) {
            return categoryRepository.findAll().stream().filter(a -> a.getHorror().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("musical")) {
            return categoryRepository.findAll().stream().filter(a -> a.getMusical().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("mystery")) {
            return categoryRepository.findAll().stream().filter(a -> a.getMystery().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("romance")) {
            return categoryRepository.findAll().stream().filter(a -> a.getRomance().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("science fiction")) {
            return categoryRepository.findAll().stream().filter(a -> a.getScienceFiction().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("science")) {
            return categoryRepository.findAll().stream().filter(a -> a.getScienceFiction().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("spy")) {
            return categoryRepository.findAll().stream().filter(a -> a.getSpy().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("thriller")) {
            return categoryRepository.findAll().stream().filter(a -> a.getThriller().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("war")) {
            return categoryRepository.findAll().stream().filter(a -> a.getWar().equals(1)).collect(Collectors.toList());
        } else if (loweredCat.equals("western")) {
            return categoryRepository.findAll().stream().filter(a -> a.getWestern().equals(1)).collect(Collectors.toList());
        }
        return categories;
    }

    @Override
    public Integer getMovieFromCategory(String query, int id){
        String loweredQ = query.toLowerCase();
        Category movieCat = getCategory(id);
        if (movieCat.getAction() == 1 && loweredQ.equals("action")) {
            return 1;
        } else if (movieCat.getAdult() == 1 && loweredQ.equals("adult")) {
            return 1;
        } else if (movieCat.getAdventure() == 1 && loweredQ.equals("adventure")) {
            return 1;
        } else if (movieCat.getAnime() == 1 && loweredQ.equals("anime")) {
            return 1;
        } else if (movieCat.getExperimental() == 1 && loweredQ.equals("experimental")) {
            return 1;
        } else if (movieCat.getChildren() == 1 && loweredQ.equals("children")) {
            return 1;
        } else if (movieCat.getComedy() == 1 && loweredQ.equals("comedy")) {
            return 1;
        } else if (movieCat.getComedyDrama() == 1 && loweredQ.equals("comedy drama")) {
            return 1;
        } else if (movieCat.getCrime() == 1 && loweredQ.equals("crime")) {
            return 1;
        } else if (movieCat.getDrama() == 1 && loweredQ.equals("drama")) {
            return 1;
        } else if (movieCat.getEpic() == 1 && loweredQ.equals("epic")) {
            return 1;
        } else if (movieCat.getFantasy() == 1 && loweredQ.equals("fantasy")) {
            return 1;
        } else if (movieCat.getHistorical() == 1 && loweredQ.equals("historical")) {
            return 1;
        } else if (movieCat.getHorror() == 1 && loweredQ.equals("horror")) {
            return 1;
        } else if (movieCat.getMusical() == 1 && loweredQ.equals("musical")) {
            return 1;
        } else if (movieCat.getMystery() == 1 && loweredQ.equals("mystery")) {
            return 1;
        } else if (movieCat.getRomance() == 1 && loweredQ.equals("romance")) {
            return 1;
        } else if (movieCat.getScienceFiction() == 1 && loweredQ.equals("science")) {
            return 1;
        } else if (movieCat.getScienceFiction() == 1 && loweredQ.equals("science fiction")) {
            return 1;
        } else if (movieCat.getSpy() == 1 && loweredQ.equals("spy")) {
            return 1;
        } else if (movieCat.getThriller() == 1 && loweredQ.equals("thriller")) {
            return 1;
        } else if (movieCat.getWar() == 1 && loweredQ.equals("war")) {
            return 1;
        } else if (movieCat.getWestern() == 1 && loweredQ.equals("western")) {
            return 1;
        } else 
        
        return 0;
    }

}

