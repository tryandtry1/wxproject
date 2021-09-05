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

@WebServlet("/NotebookServlet4")
public class NotebookServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public NotebookServlet4() {
        super();
    }


	public void destroy() {
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int id = Integer.valueOf(request.getParameter("id"));//后台获取到前端删除的行的id
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		boolean delete = new ItemDao().DeleteItem(id);  //执行删除操作
		if(delete==true){  //删除成功 
			List<NoteItem> list = new ItemDao().getUserItem(user_id);  //执行查询操作
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.print(json);
		}
		out.flush();
		out.close();
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
