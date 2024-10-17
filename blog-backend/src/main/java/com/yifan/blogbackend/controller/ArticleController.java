package com.yifan.blogbackend.controller;

import com.yifan.blogbackend.common.BaseResponse;
import com.yifan.blogbackend.common.ResultUtils;
import com.yifan.blogbackend.model.entity.Article;
import com.yifan.blogbackend.service.impl.ArticlesServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Resource
    ArticlesServiceImpl articlesService;

    @PostMapping("/add")
    public BaseResponse<String> addArticle(@RequestBody HashMap<String, String> contentMap) {
        Long l = articlesService.addArticle(contentMap.get("title"), contentMap.get("category"),
                contentMap.get("intro"), contentMap.get("content"));

        return l > 0 ? ResultUtils.success("成功") : ResultUtils.success("失败");

    }


    @PostMapping("/queryArticleByTitle")
    public BaseResponse<Article> queryArticleByTitle(@RequestBody HashMap<String, String> queryMap) {
        Article article = articlesService.queryArticleByTitle(queryMap.get("title"));


        return ResultUtils.success(article);


    }

    @PostMapping("/queryArticleById")
    public BaseResponse<Article> queryArticleById(@RequestBody HashMap<String, String> queryMap) {
        System.out.println(Long.valueOf(queryMap.get("id")));
        Article article = articlesService.queryArticleById(Long.valueOf(queryMap.get("id")));


        return ResultUtils.success(article);


    }

    @PostMapping("/queryArticleByCategory")
    public BaseResponse<List<Article>> queryArticleByCategory(@RequestBody HashMap<String, String> queryMap) {
        List<Article> articleList = articlesService.queryArticleByCategory(queryMap.get("category"));


        return ResultUtils.success(articleList);


    }


    @PostMapping("/queryRecentArticle")
    public BaseResponse<List<Article>> queryRecentArticle(@RequestBody HashMap<String, String> queryMap) {
        List<Article> num = articlesService.queryRecentArticle(queryMap.get("num"));


        return ResultUtils.success(num);


    }


}
