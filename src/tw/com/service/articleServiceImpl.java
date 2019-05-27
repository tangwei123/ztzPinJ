package tw.com.service;

import tw.com.dao.articleDao;
import tw.com.dao.factoryDao;
import tw.com.model.articleModel;

public class articleServiceImpl implements articleService {
	articleDao articledao = factoryDao.getArticleDao();
	@Override
	public articleModel getOneById(Integer id) {
		// TODO Auto-generated method stub
		return articledao.getOneById(id);
	}
	
	@Override
	public int editOne(articleModel articlemodel) {
		// TODO Auto-generated method stub
		return articledao.editOne(articlemodel);
	}

}
