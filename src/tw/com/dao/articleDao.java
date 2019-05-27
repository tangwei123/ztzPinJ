package tw.com.dao;

import java.util.List;

import tw.com.model.articleModel;

public interface articleDao {
	/**
	 * 新增一篇文章
	 * @return
	 */
	public abstract Integer add(articleModel articlemodel);
	
	/**
	 * 删除一篇文章
	 * @return
	 */
	public abstract Integer delete(int id);
	
	/**
	 * 编辑一篇文章
	 * @return
	 */
	public abstract Integer editOne(articleModel articlemodel);

	/**
	 * 获取所有的文章
	 * @return
	 */
	public abstract List<articleModel> getAllList();
	
	/**
	 * 获取一片文章
	 * @return
	 */
	public abstract articleModel getOneById(int id);
}

