package com.yunshan.mapper;

import java.util.List;

import com.yunshan.pojo.ArticleType;
import org.apache.ibatis.annotations.Select;

public interface ArticleTypeMapper {
	
	//查出一级目录下的商品信息
	@Select("Select * from ec_article_type where length(code)=4")
	List<ArticleType> findAllFirstArticleType();
	//查出二级目录下的商品信息
	@Select("Select * from ec_article_type where code like #{typeCode} and length(code) =8")
	List<ArticleType> findAllSecondArticleTypes(String typeCode);

	
}