package com.inspire12.homepage.service.board;

import com.inspire12.homepage.domain.model.Board;
import com.inspire12.homepage.domain.service.BoardDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDomainService boardDomainService;

    public List<Board> findBoardList(){
        return boardDomainService.findBoardList();
    }

    public Board save(Board board){
        return boardDomainService.save(board);
    }
}
