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

import dao.User_infoDao;
import vo.NoteUser_info;

@WebServlet("/NotebookServlet1")
public class NotebookServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NotebookServlet1() {
        super();
        
    }
    
    public void destroy(){
    	super.destroy();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		//获取表单参数
		String user_name = (String) request.getParameter("user_name");
		String user_psw = (String)request.getParameter("user_psw");
		
		int user_id = -1;
		//通过User_infoDao获取数据库中保存的用户名以及密码
		List<NoteUser_info> list = new User_infoDao().getUser_info();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getUsername().equals(user_name)&&list.get(i).getPassword().equals(user_psw)){
				user_id = list.get(i).getId();
				session.setAttribute("user_id", user_id);
				/*NoteUser_info user_info = new NoteUser_info();
				user_info.setId(user_id);
				user_info.setUsername(user_name);
				user_info.setPassword(user_psw);
				Gson gson=new Gson();
				String info = gson.toJson(1);*/
			}
		}
		out.println(user_id);
		//out.print(request.getAttribute("name"));
	    out.flush();
	    out.close();
		/*List<NoteUser_info> list = new User_infoDao().login(user_name, user_psw);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json);
		System.out.println(json);*/
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
