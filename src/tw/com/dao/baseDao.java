package tw.com.dao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import tw.com.utils.c3p0Utils;

/**
 * 封装queryRunner，为上层的sql语句提供执行，并返回结果
 * @author tangwei
 *
 */
public class baseDao<TModel>{
	public QueryRunner queryRunner = new QueryRunner();
	
	public Class<TModel> clazz = null;

	public baseDao() {
		Type tmpType = this.getClass().getGenericSuperclass();
		System.out.println(tmpType);
		ParameterizedType parameterizedType = (ParameterizedType)tmpType;
		Type p = parameterizedType.getActualTypeArguments()[0];
		clazz = (Class<TModel>) p;
	}
	
	public Integer update(String sqlString, Object...args) {
		Integer num = 0;
		Connection connection = c3p0Utils.getConnection();
		try {
			num = queryRunner.update(connection, sqlString, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c3p0Utils.closeConnect(connection);
		return num;
	}
	
	public Integer delete(String sqlString, Object...args) {
		Integer num = 0;
		Connection connection = c3p0Utils.getConnection();
		try {
			num = queryRunner.update(connection, sqlString, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c3p0Utils.closeConnect(connection);
		return num;
	}
	
	public Integer insert(String sqlString, Object...args) {
		Integer num = 0;
		Connection connection = c3p0Utils.getConnection();
		try {
			num = queryRunner.update(connection, sqlString, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c3p0Utils.closeConnect(connection);
		return num;
	}
	
	public TModel getOne(String sqlString, Object...args) {
		Connection connection = c3p0Utils.getConnection();
		TModel res = null;
		try {
			res = queryRunner.query(connection, sqlString, new BeanHandler<TModel>(clazz), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c3p0Utils.closeConnect(connection);
		return res;
	}
	
	public List<TModel> getList(String sqlString, Object...args){
		Connection connection = c3p0Utils.getConnection();
		List<TModel> tmpList = null;
		try {
			tmpList = queryRunner.query(connection, sqlString, new BeanListHandler<TModel>(clazz), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c3p0Utils.closeConnect(connection);
		return tmpList;
	}
}


