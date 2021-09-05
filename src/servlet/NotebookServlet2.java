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

@WebServlet("/NotebookServlet2")
public class NotebookServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public NotebookServlet2() {
        super();
    }


	public void destroy() {
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(session!=null){
			List<NoteItem> list = new ArrayList<NoteItem>();
			//java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
			//创建对象item
			NoteItem item = new NoteItem();
			//将页面中用户输入的值以及获取到的用户id放到item中
			java.sql.Date date=java.sql.Date.valueOf(request.getParameter("date_paid"));
			item.setItem_text(request.getParameter("item_text"));
			item.setAmount(new BigDecimal(request.getParameter("amount")));
			item.setCategory_id(Integer.parseInt(request.getParameter("category_id")));
			item.setUser_id(Integer.parseInt(request.getParameter("user_id")));
			item.setDate_paid(date);
			list.add(item);
			boolean insert = new ItemDao().InsertItem(item);
			if(insert==true){
				Gson gson = new Gson();
				String json = gson.toJson(insert);
				out.print(json);
			}	
		    }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
