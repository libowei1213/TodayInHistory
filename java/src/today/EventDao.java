package today;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    public List<Event> getEventByDate(String date) {
        List<Event> list = new ArrayList<Event>();
        Connection conn = DBUtil.getConnection();
        String sql = "select * from event where date=? order by year desc";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                String year = rs.getString("year");
                String message = rs.getString("message");
                Event event = new Event();
                event.setType(type);
                event.setYear(year);
                event.setMessage(message);
                list.add(event);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return list;
    }

}
