package com.inspire12.homepage.service.board;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.model.FileMeta;
import com.inspire12.homepage.domain.repository.FileMetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileMetaService {

    private final FileMetaRepository fileMetaRepository;

    private void saveFileMetas(List<FileMeta> fileMetas) {
        for(FileMeta fileMeta : fileMetas) {
            fileMetaRepository.save(fileMeta);
        }
    }

    public boolean saveFileMetas(ArrayNode files, Article article) {
        List<FileMeta> fileMetas = new ArrayList<>();
        for(JsonNode file: files){
            FileMeta fileMeta = FileMeta.create(file, article);
            fileMetas.add(fileMeta);
        }
        saveFileMetas(fileMetas);
        return true;
    }

    public void deleteFileMeta(Long id) {
        fileMetaRepository.deleteById(id);
    }
    public FileMeta getFileMeta(Long id) {
        return fileMetaRepository.findById(id).get();
    }
}
