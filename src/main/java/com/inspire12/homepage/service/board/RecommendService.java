package com.inspire12.homepage.service.board;

import com.inspire12.homepage.domain.model.Recommend;
import com.inspire12.homepage.domain.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final RecommendRepository recommendRepository;

    public boolean recommendToggleArticle(Long id, String username) {
        if (recommendRepository.existsById(id)) {
            recommendRepository.deleteById(id);
        }else {
            Recommend recommend = Recommend.create(id, username);
            recommendRepository.save(recommend);
        }
        return true;
    }
}
