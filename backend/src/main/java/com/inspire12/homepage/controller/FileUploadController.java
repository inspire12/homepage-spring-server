package com.inspire12.homepage.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.interceptor.UserLevel;
import com.inspire12.homepage.model.entity.FileMeta;
import com.inspire12.homepage.service.board.FileMetaService;
import com.inspire12.homepage.service.storage.FileSystemStorageService;
import com.inspire12.homepage.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class FileUploadController {

    private final FileSystemStorageService storageService;

    @Autowired
    private FileMetaService fileMetaService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public FileUploadController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }

//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }

    @UserLevel(allow = UserLevel.UserRole.USER)
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @UserLevel(allow = UserLevel.UserRole.USER)
    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }

    @UserLevel(allow = UserLevel.UserRole.USER)
    @PostMapping("/files")
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file,
                                           RedirectAttributes redirectAttributes) {
        // file type 확인
        String uploadUrl = storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        ObjectNode body = objectMapper.createObjectNode();
        body.put("upload-file", uploadUrl);
        return ResponseEntity.ok().body(body);
    }

    @UserLevel(allow = UserLevel.UserRole.USER)
    @DeleteMapping("/files")
    public ResponseEntity deleteFileUpload(@RequestParam("id") Integer id,
                                           RedirectAttributes redirectAttributes) {
        // file type 확인
        FileMeta fileMeta = fileMetaService.getFileMeta(id);
        fileMetaService.deleteFileMeta(id);

        return ResponseEntity.ok().body(id);
    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
