package com.insert.register.Category;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    public List<Category> getAllCategories();
    public void removeCategory(int id);
}