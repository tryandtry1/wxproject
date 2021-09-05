package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import util.DBUtil;
import vo.NoteItem;
import vo.NoteUser_info;

public class User_infoDao {
	 
	//获取数据库中用户名以及用户密码的类方法
	public List<NoteUser_info> getUser_info(){
		DBUtil dbUtil = new DBUtil();
		List<NoteUser_info> list = new ArrayList<NoteUser_info>();
		Connection conn = dbUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from user_info"; 
		try {
			pst = conn.prepareStatement(sql);			
			rs = pst.executeQuery();
			while (rs.next()) {
				NoteUser_info noteuser_info = new NoteUser_info();
				noteuser_info.setId(rs.getInt("id"));
				noteuser_info.setUsername(rs.getString("username"));
				noteuser_info.setPassword(rs.getString("password"));
				list.add(noteuser_info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.closeAll(rs, pst, conn);
		}
		return list;
	}
	public List<NoteUser_info> login(String username,String userpsw){
		DBUtil dbUtil = new DBUtil();
		List<NoteUser_info> list = new ArrayList<NoteUser_info>();
		Connection conn = dbUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from user_info where username=? and password=?"; 
		try {
			pst = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		//动态sql语句的参数赋值
		try {
			pst.setString(1, username);
			pst.setString(2, userpsw);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//执行语句，返回结果集
		try {			
			rs = pst.executeQuery();
			while (rs.next()) {
				NoteUser_info info = new NoteUser_info();
				info.setId(rs.getInt("id"));
				info.setUsername(rs.getString("username"));
				info.setPassword(rs.getString("password"));
				list.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.closeAll(rs, pst, conn);
		}
		return list;
	}

	//将list转换为json格式
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List<NoteUser_info> list = new User_infoDao().getUser_info();
		List<NoteUser_info> list = new User_infoDao().login("gyj", "19981119");
		Gson gson = new Gson();
		String json_str = gson.toJson(list);
		System.out.println(json_str);
		System.out.println(list.toString());
	}
}

