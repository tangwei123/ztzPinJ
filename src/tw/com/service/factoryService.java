package tw.com.service;

public class factoryService {
	public static articleService getArticleService() {
		return new articleServiceImpl();
	}
}
