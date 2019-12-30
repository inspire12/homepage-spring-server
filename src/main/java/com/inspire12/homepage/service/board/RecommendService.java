package com.inspire12.homepage.service.board;

import com.inspire12.homepage.model.entity.Recommend;
import com.inspire12.homepage.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendService {

    @Autowired
    RecommendRepository recommendRepository;

    public boolean recommendToggleArticle(int id, String username) {
        if (recommendRepository.existsById(id)) {
            recommendRepository.deleteById(id);
        }else {
            Recommend recommend = Recommend.create(id, username);
            recommendRepository.save(recommend);
        }
        return true;
    }
}
