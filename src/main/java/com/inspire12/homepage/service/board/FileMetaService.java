package com.inspire12.homepage.service.board;

import com.inspire12.homepage.model.entity.FileMeta;
import com.inspire12.homepage.repository.FileMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileMetaService {
    @Autowired
    FileMetaRepository fileMetaRepository;

    public void saveFileMetas(List<FileMeta> fileMetas) {
        for(FileMeta fileMeta : fileMetas) {
            fileMetaRepository.save(fileMeta);
        }
    }
}
