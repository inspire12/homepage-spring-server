package com.inspire12.homepage.controller;

import com.inspire12.homepage.controller.community.ArticleController;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(ArticleController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class WebLayerTest {

}
