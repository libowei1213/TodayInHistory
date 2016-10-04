package today;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class ApiServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		
		String param = req.getParameter("date");
		String date = param;
		EventDao dao = new EventDao();

		List<Event> list = dao.getEventByDate(date);
		ApiResponse reponse = new ApiResponse();

		if (list != null && list.size() > 0) {
			System.out.println(list.size());
			reponse.setStatus(true);
			reponse.setMessage("调用成功");
			reponse.setList(list);
		} else {
			reponse.setStatus(false);
			reponse.setMessage("调用失败");
		}

		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(JSON.toJSONString(reponse));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
