package tw.com.service;

import java.util.List;

import tw.com.model.articleModel;

public interface articleService {
	public abstract articleModel getOneById(Integer id);
	
	public abstract int editOne(articleModel articlemodel);
	
	public abstract int addOne(articleModel articlemodel);
	
	public abstract List<articleModel> getList(String page);
}
