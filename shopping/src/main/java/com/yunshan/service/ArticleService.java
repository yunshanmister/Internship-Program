package com.yunshan.service;

import com.yunshan.pojo.Article;
import com.yunshan.pojo.ArticleType;

import java.util.List;


public interface ArticleService {
	
	//查询出所有一级目录
	List<ArticleType> findAllFirstArticleType();
	
	//3种情况查询。一级、关键字、无查询
	List<Article> findAllArticle(String typeCode, String keyword);

	//根据一级目录，带出二级目录
	List<ArticleType> findAllSecondArticleTypes(String typeCode);
	
	//根据id查询商品信息
	Article getArticleById(Integer id);



}
