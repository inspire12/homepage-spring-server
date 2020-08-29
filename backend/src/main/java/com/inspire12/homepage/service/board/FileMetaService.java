package com.inspire12.homepage.service.board;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.inspire12.homepage.model.entity.Article;
import com.inspire12.homepage.model.entity.FileMeta;
import com.inspire12.homepage.repository.FileMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileMetaService {

    @Autowired
    FileMetaRepository fileMetaRepository;

    private void saveFileMetas(List<FileMeta> fileMetas) {
        for (FileMeta fileMeta : fileMetas) {
            fileMetaRepository.save(fileMeta);
        }
    }

    public boolean saveFileMetas(ArrayNode files, Article article) {
        List<FileMeta> fileMetas = new ArrayList<>();
        for (JsonNode file : files) {
            FileMeta fileMeta = FileMeta.create(file, article);
            fileMetas.add(fileMeta);
        }
        saveFileMetas(fileMetas);
        return true;
    }

    public void deleteFileMeta(Integer id) {
        fileMetaRepository.deleteById(id);
    }

    public FileMeta getFileMeta(Integer id) {
        return fileMetaRepository.findById(id).get();
    }
}
