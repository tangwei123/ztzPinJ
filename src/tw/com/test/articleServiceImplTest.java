package tw.com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tw.com.model.articleModel;
import tw.com.service.articleService;
import tw.com.service.articleServiceImpl;
import tw.com.service.factoryService;

class articleServiceImplTest {

	@Test
	void testGetOneById() {
		articleService asticleservice = factoryService.getArticleService();
		articleModel articlemodel = asticleservice.getOneById(2);
		System.out.println(articlemodel.toString());
	}

}
