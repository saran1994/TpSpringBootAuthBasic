package com.kalanso.coaching.Service;

import com.kalanso.coaching.Model.BaseDeConnaissances;
import com.kalanso.coaching.Repository.BaseDeConnaissancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseDeConnaissancesService {

    @Autowired
    private BaseDeConnaissancesRepository baseDeConnaissancesRepository;

    public List<BaseDeConnaissances> getAllArticles() {
        return baseDeConnaissancesRepository.findAll();
    }

    public BaseDeConnaissances getArticleById(Long id) {
        return baseDeConnaissancesRepository.findById(id).orElse(null);
    }

    public BaseDeConnaissances createArticle(BaseDeConnaissances article) {
        return baseDeConnaissancesRepository.save(article);
    }




    public void deleteArticle(Long id) {
        baseDeConnaissancesRepository.deleteById(id);
    }
}
