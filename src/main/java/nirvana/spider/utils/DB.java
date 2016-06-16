package nirvana.spider.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * DB数据库操作类
 * @author nirvna
 *
 */
public  abstract class DB   {
	private DB(){
		int a = 0xff;
		int x = 211;
		System.out.println(x);
	}
	
	private final static ProperyReader READER = ProperyReader.getReader();
	
	public  abstract Connection getConnection();
	
	public abstract Integer persist(String sql,List<Object> params);
	
	public abstract int[] persistBatch(String sql,List<List<Object>> params);
	
	public void close(AutoCloseable obj){
		if(obj != null){
			try {
				obj.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static class MysqlDB extends DB{
		@Override
		public Connection getConnection() {
			try {
				String driver = READER.getValByKey("mysql.db.driver");
				Class.forName(driver);
				String jdbcUrl = READER.getValByKey("mysql.db.url");
				String user = READER.getValByKey("mysql.db.user");
				String password = READER.getValByKey("mysql.db.password");
				Connection conn = DriverManager.getConnection(jdbcUrl,user,password);
				return conn;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 
		 */
		@Override
		public Integer persist(String sql,List<Object> params) {
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			 
			try {
				connection = this.getConnection();
			    prepareStatement = connection.prepareStatement(sql);
				int length = params.size();
				for(int i=0;i<length;i++){
					prepareStatement.setObject(i+1, params.get(i));
				}
			  return	prepareStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				super.close(prepareStatement);
				super.close(connection);
			}
			return null;
		}
		
			
		public int[] persistBatch(String sql,List<List<Object>> batchParams) {
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			 
			try {
				connection = this.getConnection();
			    prepareStatement = connection.prepareStatement(sql);
				for(List<Object>  params : batchParams){
					int size = params.size();
					for(int i=0;i<size;i++){
						prepareStatement.setObject(i+1, params.get(i));
					}
					prepareStatement.addBatch();
				}
				return prepareStatement.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				super.close(prepareStatement);
				super.close(connection);
			}
			return null;
		}
	}
	
	public static DB getSingleton(){
		return Singleton.db;
	}
	
 	  
	
	private static class Singleton{
		 final static DB db = new DB.MysqlDB();
	}
    
	public static void main(String[] args) {
		System.out.println(DB.getSingleton().getConnection());;
	}
  
		
}
