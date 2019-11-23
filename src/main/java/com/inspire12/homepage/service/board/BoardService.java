package com.inspire12.homepage.service.board;

import com.inspire12.homepage.model.entity.Board;
import com.inspire12.homepage.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public List<Board> findBoardList(){
        return boardRepository.findAll();
    }

    public Board save(Board board){
        return boardRepository.save(board);
    }
}
