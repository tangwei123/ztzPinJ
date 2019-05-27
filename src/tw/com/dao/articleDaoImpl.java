package tw.com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mysql.cj.xdevapi.SqlStatement;

import tw.com.model.articleModel;

public class articleDaoImpl extends baseDao<articleModel> implements articleDao  {

	/**
	 * 设置好当前操作的表明
	 */
	private String tableName = "article";
	
	public articleDaoImpl() {
		super();
	}
	
	@Override
	public Integer add(articleModel articlemodel) {
		String sqlString = "INSERT INTO "+tableName+" (`articleCatId`, `title`, `des`, `indexImg`, `content`, `addTime`, `sort`, `isDeleted`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		return super.update(sqlString, articlemodel.getArticleCatId(), articlemodel.getTitle(), articlemodel.getDes(), articlemodel.getIndexImg(), articlemodel.getContent(), articlemodel.getAddTime(), articlemodel.getSort(), articlemodel.getIsDeleted());
	}

	@Override
	public Integer delete(int id) {
		String sqString = "DELETE FROM "+tableName+" where id = ?";
		return super.delete(sqString, id);
	}

	@Override
	public Integer editOne(articleModel articlemodel) {
		String sqlString = "UPDATE "+tableName+" SET `articleCatId` = ?, `title` = ?, `des` = ?, `indexImg`= ?, `content` = ?, `sort` = ?, `isDeleted` = ? WHERE `id`  = ?";
		return super.update(sqlString, articlemodel.getArticleCatId(), articlemodel.getTitle(), articlemodel.getDes(), articlemodel.getIndexImg(), articlemodel.getContent(),articlemodel.getSort(), articlemodel.getIsDeleted(), articlemodel.getId());
	}

	@Override
	public List<articleModel> getAllList() {
		String sqlString = "SELECT * FROM "+tableName+" WHERE `isDeleted` = 0 ";
		return super.getList(sqlString);
	}

	@Override
	public articleModel getOneById(int id) {
		
		String sqlString = "SELECT * FROM "+tableName+ " WHERE `id` = ?";
		return super.getOne(sqlString, id);
	}

}
