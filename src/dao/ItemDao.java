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

public class ItemDao {
	public List<NoteItem> getItem(){
		DBUtil dbUtil = new DBUtil();
		List<NoteItem> list = new ArrayList<NoteItem>();
		Connection conn = dbUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from item"; 
		//生成语句对象
		try {
			pst = conn.prepareStatement(sql);			
			rs = pst.executeQuery();
			while (rs.next()) {
				NoteItem note = new NoteItem();
				note.setId(rs.getInt("id"));
				note.setItem_text(rs.getString("item_text"));
				note.setAmount(rs.getBigDecimal("amount"));
				note.setDate_paid(rs.getDate("date_paid"));
				note.setCategory_id(rs.getInt("category_id"));
				note.setUser_id(rs.getInt("user_id"));
				list.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.closeAll(rs, pst, conn);
		}
		return list;
	}
	
	public List<NoteItem> getUserItem(int user_id){
		DBUtil dbUtil = new DBUtil();
		List<NoteItem> list = new ArrayList<NoteItem>(); 
		Connection conn = dbUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select A.*,B.category_text from item A left join Category B on A.category_id = B.id where A.user_id = ? order by id desc";
		//生成语句对象
		try {
			pst = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		//动态sql语句的参数赋值
		try {
			pst.setInt(1, user_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//执行语句，返回结果集
		try {			
			rs = pst.executeQuery();
			while (rs.next()) {
				NoteItem note = new NoteItem();
				note.setId(rs.getInt("id"));
				note.setItem_text(rs.getString("item_text"));
				note.setAmount(rs.getBigDecimal("amount"));
				note.setDate_paid(rs.getDate("date_paid"));
				note.setCategory_id(rs.getInt("category_id"));
				note.setUser_id(rs.getInt("user_id"));
				note.setCategory_text(rs.getString("category_text"));
				list.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.closeAll(rs, pst, conn);
		}
		return list;
	}
	public boolean InsertItem(NoteItem item){
		
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		String sql = "insert into item(item_text,amount,category_id,date_paid,user_id) values (?,?,?,?,?)"; 
		try {
			pst = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {
			pst.setString(1, item.getItem_text());
			pst.setString(2, (item.getAmount()).toString());
			pst.setInt(3, item.getCategory_id());
			pst.setDate(4, item.getDate_paid());
			pst.setInt(5, item.getUser_id());		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//执行语句
		int i = 0;
		try {
			i = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//5:返回结果
		if(i>0){
			return true;
		}else{
			return false;
		}
		
		
	}
public boolean DeleteItem(int item_id){
		
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		//1:构造sql语句（删除记录）
		String sql = "delete from item where id=?";
		//2:生成预处理语句对象
		try {
			pst = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3:参数赋值
		try {
			pst.setInt(1, item_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//4:执行语句
		int i = 0;
		try {
			i = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5:返回结果
		if(i>0){
			System.out.println("删除成功！！！");
			return true;
		}else{
			System.out.println("删除失败！！！");
			return false;
		}
		
	}
    public boolean update(int id,NoteItem item){
    	DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		//1:构造sql语句（修改记录）
		String sql = "update item set item_text=?,amount=?,date_paid=?";
		sql = sql + " where id=?";
		//2:生成预处理语句对象
		try {
			pst = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3:参数赋值
		try {
			pst.setString(1, item.getItem_text());
			pst.setString(2, (item.getAmount()).toString());
			pst.setDate(3, item.getDate_paid());
			pst.setInt(4, item.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//4:执行语句
		int i = 0;
		try {
			i = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5:返回结果
		if(i>0){
			System.out.println("更新成功！！！");
			return true;
		}else{
			System.out.println("更新失败！！！");
				return false;
			}
		}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<NoteItem> list = new ItemDao().getItem();
		Gson gson = new Gson();
		String json_str = gson.toJson(list);
		System.out.println(json_str);
		System.out.println(list.toString());
	}*/
}
