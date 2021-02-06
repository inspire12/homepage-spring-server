package com.inspire12.homepage.domain.service;

import com.inspire12.homepage.domain.model.Board;
import com.inspire12.homepage.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardDomainService {

    private final BoardRepository boardRepository;

    public List<Board> findBoardList(){
        return boardRepository.findAll();
    }

    public Board save(Board board){
        return boardRepository.save(board);
    }
}
