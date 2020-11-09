package com.inspire12.homepage.util;

public class ArticleUtil {
    public static String getArticleCategory(int id){
        if (id == 1){
            return "잡담";
        } else if(id == 2){
            return "정보";
        } else if(id == 3){
            return "스터디";
        } else if(id == 4){
            return "알고리즘";
        } else if(id == 5){
            return "AI";
        } else if(id == 6){
            return "개발지식";
        }
        return "잡담";
    }
}
