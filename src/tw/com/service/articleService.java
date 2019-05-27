package tw.com.service;

import tw.com.model.articleModel;

public interface articleService {
	public abstract articleModel getOneById(Integer id);
	
	public abstract int editOne(articleModel articlemodel);
}
