package com.yunshan.service.impl;

import java.util.List;

import com.yunshan.mapper.ArticleMapper;
import com.yunshan.mapper.ArticleTypeMapper;
import com.yunshan.pojo.Article;
import com.yunshan.pojo.ArticleType;
import com.yunshan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper articleMapper;
	
	@Autowired
	ArticleTypeMapper articleTypeMapper;

	@Override
	public List<ArticleType> findAllFirstArticleType() {
		//查询一级分类
		List<ArticleType> articleTypes=articleTypeMapper.findAllFirstArticleType();
		return articleTypes;
	}

	@Override
	public List<Article> findAllArticle(String typeCode, String keyword) {
		//3种查询情况
		List<Article> articles=articleMapper.findAllArticle(typeCode,keyword);
		return articles;
	}
	
	//查询出二级目录
	@Override
	public List<ArticleType> findAllSecondArticleTypes(String typeCode) {
		List<ArticleType> articleTypes=articleTypeMapper.findAllSecondArticleTypes(typeCode);
		return articleTypes;
	}
	
	//展示商品详情
	@Override
	public Article getArticleById(Integer id) {
		Article article=articleMapper.getArticleById(id);
		return article;
	}
	
}
