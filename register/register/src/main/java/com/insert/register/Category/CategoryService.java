package com.insert.register.Category;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    public Integer saveAddress(int x);
    public List<Category> getAllCategories();
    public Category getCategory(int id);
    public void removeCategory(int id);
    public List<Category> getMovieWithCategory(String category);
    public  Integer getMovieFromCategory(String query, int id);
}