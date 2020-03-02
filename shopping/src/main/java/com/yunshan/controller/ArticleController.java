package com.yunshan.controller;

import java.util.List;

import com.yunshan.pojo.Article;
import com.yunshan.pojo.ArticleType;
import com.yunshan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@RequestMapping("/index")
	public String articleIndex(Model model,String typeCode,String keyword) {
	
		//查询出所有一级目录
		List<ArticleType> articleTypes=articleService.findAllFirstArticleType();
		//存放在域中，让前端页面去取
		model.addAttribute("articleTypes", articleTypes);
		
		//点击一级目录，带出二级目录
		if(typeCode!=null && !typeCode.equals("")) {
			String code=typeCode.substring(0,4);
			List<ArticleType> seArticleTypes=articleService.findAllSecondArticleTypes(code+"%");
			model.addAttribute("secondArticleTypes", seArticleTypes);
		}

		
		//实现3种情况，一级目录查询，关键字查询，或者不查询并显示所有的情况
		List<Article> articles=articleService.findAllArticle(typeCode == null ? null : typeCode+"%",keyword==null? null:"%"+keyword+"%");
		model.addAttribute("articles", articles);	
		
		return "articleIndex";
		
	}
	
	//根据Id查看商品详情
	@RequestMapping("/detail")
	public String articleDetail(Integer id,Model model) {
		Article article	=articleService.getArticleById(id);
		model.addAttribute("article", article);
		return "articleDetail";
		
	}
	

}
