package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ItemDao;
import dao.User_infoDao;
import vo.NoteItem;
import vo.NoteUser_info;

@WebServlet("/NotebookServlet3")
public class NotebookServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NotebookServlet3() {
        super();
        
    }
    
    public void destroy(){
    	super.destroy();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		List<NoteItem> list = new ItemDao().getUserItem(user_id);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
