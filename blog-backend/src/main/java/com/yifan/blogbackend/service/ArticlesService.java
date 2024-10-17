package com.yifan.blogbackend.service;

import com.yifan.blogbackend.model.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 周逸凡
* @description 针对表【articles(文章)】的数据库操作Service
* @createDate 2024-10-13 15:11:21
*/
public interface ArticlesService extends IService<Article> {

    /**
     * 添加文章
     * @param title 文章标题
     * @param category 文章类别
     * @param content 文章内容
     * @return
     */
    public Long addArticle(String title,String category,String intro,String content);


    /**
     * 根据title查询文章
     * @param title 文章标题
     * @return
     */
    public Article queryArticleByTitle(String title);


    /**
     * 根据id查询文章
     * @param id 文章id
     * @return
     */
    public Article queryArticleById(Long id);

    /**
     * 根据类别查询文章
     * @param category 文章类别
     * @return
     */
    public List<Article> queryArticleByCategory(String category);


    /**
     * 查询最新的文章
     * @param num 文章数量
     * @return
     */
    public List<Article> queryRecentArticle(String num);

}
