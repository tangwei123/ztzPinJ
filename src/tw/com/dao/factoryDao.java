package tw.com.dao;

public class factoryDao {
	
	public static articleDao getArticleDao() {
		return new articleDaoImpl();
	}
}
