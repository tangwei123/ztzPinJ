package tw.com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import tw.com.dao.articleDaoImpl;
import tw.com.model.articleModel;

class articleDaoImplTest {

	@Test
	void testAdd() {
		articleModel articlemodel = new articleModel();
		articlemodel.setArticleCatId(10);
		articlemodel.setTitle("测试新增文章标题");
		articlemodel.setDes("测试新增文章描述");
		articlemodel.setIndexImg("测试新增文章头图");
		articlemodel.setContent("测试新增文章");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addTime = dateFormat.format(date);
		articlemodel.setAddTime(addTime);
		articlemodel.setSort(1);
		articlemodel.setIsDeleted(0);
		articleDaoImpl articledaoimpl = new articleDaoImpl();
		int num = articledaoimpl.add(articlemodel);
		System.out.println(num);
	}

	@Test
	void testDeleteInt() {
		articleDaoImpl articledaoimpl = new articleDaoImpl();
		int num = articledaoimpl.delete(1);
		System.out.println(num);
	}

	@Test
	void testEditOne() {
		articleDaoImpl articledaoimpl = new articleDaoImpl();
		articleModel articleInfo = articledaoimpl.getOneById(2);
		System.out.println(articleInfo.toString());
		articleInfo.setArticleCatId(10);
		articleInfo.setTitle("测试新增文章标题");
		articleInfo.setDes("测试新增文章描述");
		articleInfo.setIndexImg("测试新增文章头图");
		articleInfo.setContent("测试新增文章");
		articleInfo.setSort(1);
		articleInfo.setIsDeleted(0);
		int num = articledaoimpl.editOne(articleInfo);
		System.out.println(num);
	}

	@Test
	void testGetAllList() {
		articleDaoImpl articledaoimpl = new articleDaoImpl();
		List<articleModel> articleList = articledaoimpl.getAllList();
		for(articleModel articlemodel:articleList) {
			System.out.println(articlemodel.toString());
		}
	}

	@Test
	void testGetOneById() {
		articleDaoImpl articledaoimpl = new articleDaoImpl();
		articleModel articlemodel = articledaoimpl.getOneById(2);
		System.out.println(articlemodel.toString());
	}

}
