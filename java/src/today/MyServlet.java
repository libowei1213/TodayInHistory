package today;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String date = req.getParameter("d");

        if (date != null) {
            Pattern pattern = Pattern.compile("(\\d{1,2})月(\\d{1,2})日");
            Matcher matcher = pattern.matcher(date);
            if (matcher.find()) {
                Integer month = Integer.parseInt(matcher.group(1));
                Integer day = Integer.parseInt(matcher.group(2));

                if (month <= 12 && day <= 31) {
                    date = String.format("%02d月%02d日", month, day);
                    System.out.println(String.format("%02d月%02d日", month, day));
                    req.setAttribute("date", date);
                }
            }
        }

        req.getRequestDispatcher("today.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doGet(req, resp);
    }

}
