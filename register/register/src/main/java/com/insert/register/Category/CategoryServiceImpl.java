package com.insert.register.Category;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.NoSuchElementException;

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
}

