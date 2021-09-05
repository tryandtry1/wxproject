package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ItemDao;
import vo.NoteItem;

@WebServlet("/NotebookServlet5")
public class NotebookServlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public NotebookServlet5() {
        super();
    }


	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//后台获取到前端修改的数据
		NoteItem item = new NoteItem();
		//将页面中用户修改的值放到item中
		java.sql.Date date=java.sql.Date.valueOf(request.getParameter("date_paid"));
		item.setId(Integer.valueOf(request.getParameter("id")));//这里的id为行id
		item.setItem_text(request.getParameter("item_text"));
		item.setAmount(new BigDecimal(request.getParameter("amount")));
		item.setDate_paid(date);
		boolean update= new ItemDao().update(Integer.valueOf(request.getParameter("id")), item); //执行修改操作
		int user_id = Integer.parseInt(request.getParameter("user_id"));//这里的user_id为用户id
		if(update==true){  //修改成功   
			List<NoteItem> list = new ItemDao().getUserItem(user_id);  //执行查询操作
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.print(json);
		}
		else{
			out.print(0);
		}
		/*item.setId(25);
		item.setItem_text("see you");
		item.setAmount(new BigDecimal(500));
		item.setCategory_id(2);
		java.sql.Date date=java.sql.Date.valueOf("2020-11-15");
		item.setDate_paid(date);
		boolean update= new ItemDao().update(25, item); //执行修改操作
		if(update==true){  //修改成功 
			HttpSession session = request.getSession();
			int user_id = (int) session.getAttribute("user_id");  
			List<NoteItem> list = new ItemDao().getUserItem(user_id);  //执行查询操作
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.print(json);
		}*/
		out.flush();
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
