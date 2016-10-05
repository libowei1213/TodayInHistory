package today;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String date = null;
		Matcher matcher = Pattern.compile("^(\\d{1,2})月(\\d{1,2})日$").matcher(param);
		if (matcher.find()) {
			int month = Integer.parseInt(matcher.group(1));
			int day = Integer.parseInt(matcher.group(2));
			if ((month >= 1 && month <= 12) && (day >= 1 && day <= 31)) {
				date = month + "月" + day + "日";
			}
		}
		List<Event> list;
		ApiResponse reponse = new ApiResponse();
		if (date != null) {
			EventDao dao = new EventDao();
			list = dao.getEventByDate(date);
			if (list != null && list.size() > 0) {
				System.out.println(list.size());
				reponse.setStatus(true);
				reponse.setMessage("调用成功");
				reponse.setList(list);
			} else {
				reponse.setStatus(false);
				reponse.setMessage("调用失败");
			}
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
