package tw.com.service;

import java.util.List;

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
	
	@Override
	public int addOne(articleModel articlemodel) {
		return articledao.add(articlemodel);
	}
	
	@Override
	public List<articleModel> getList(String page) {
		int nowpage = Integer.parseInt(page);
		int pageSize = 10;
		int preLimit = (nowpage - 1) * 10;
		String limitString = "LIMIT "+preLimit+","+pageSize;
		return articledao.getAllList(limitString);
	}

}
