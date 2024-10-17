package com.yifan.blogbackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yifan.blogbackend.common.ErrorCode;
import com.yifan.blogbackend.exception.BusinessException;
import com.yifan.blogbackend.model.entity.Article;
import com.yifan.blogbackend.service.ArticlesService;
import com.yifan.blogbackend.mapper.ArticlesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 周逸凡
 * @description 针对表【articles(文章)】的数据库操作Service实现
 * @createDate 2024-10-13 15:11:21
 */
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Article>
        implements ArticlesService {


    @Resource
    ArticlesMapper articlesMapper;

    @Override
    public Long addArticle(String title, String category, String intro, String content) {
        //校验
        if (StrUtil.hasBlank(title, category, intro, content)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        Article articles = new Article();
        articles.setTitle(title);
        articles.setCategory(category);
        articles.setIntro(intro);
        articles.setContent(content);
        boolean save = this.save(articles);
        return save ? 1L : 0L;
    }

    @Override
    public Article queryArticleByTitle(String title) {
        //校验
        if (StrUtil.hasBlank(title)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }

        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("title", title);
        Article res = this.getOne(articleQueryWrapper);
        if (res.getId() == null || res.getId() <= 0) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该文章不存在");
        }

        return res;

    }

    @Override
    public Article queryArticleById(Long id) {
        //校验
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }

        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("id", id);
        Article res = this.getOne(articleQueryWrapper);
        if (StrUtil.hasBlank(res.getTitle())) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该文章不存在");
        }

        return res;
    }

    @Override
    public List<Article> queryArticleByCategory(String category) {
        //校验
        if (StrUtil.hasBlank(category)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }

        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("category", category);
        List<Article> res = articlesMapper.selectList(articleQueryWrapper);
        if (ObjectUtil.isEmpty(res)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该文章不存在");
        }

        return res;
    }

    @Override
    public List<Article> queryRecentArticle(String num) {
        // 创建QueryWrapper
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        // 对createTime进行降序排序
        queryWrapper.orderByDesc("createTime");
        // 直接拼接SQL的LIMIT语句
        queryWrapper.last("LIMIT 0, " + num);
        // 执行查询
        return articlesMapper.selectList(queryWrapper);
    }
}




