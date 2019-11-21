package com.inspire12.homepage.util;

public class ArticleUtil {
    public static String getArticleType(int id){
        if (id == 1){
            return "공지";
        } else if(id == 2){
            return "족보";
        }
        return "잡담";
    }
}
