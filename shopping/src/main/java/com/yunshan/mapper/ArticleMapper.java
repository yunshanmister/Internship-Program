package com.yunshan.mapper;



import com.yunshan.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ArticleMapper {

	//查询所有商品
	List<Article> findAllArticle(@Param("typeCode") String typeCode, @Param("keyword") String keyword);
	
	//根据商品id查询出商品信息
	@Select("select * from ec_article where id=#{id}")
	Article getArticleById(Integer id);


}