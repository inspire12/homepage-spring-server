package com.inspire12.homepage.util;

public class ArticleUtil {
    public static String getArticleCategory(int id){
        if (id == 1){
            return "공지";
        } else if(id == 2){
            return "정보";
        } else if(id == 3){
            return "족보";
        }
        return "잡담";
    }
}
