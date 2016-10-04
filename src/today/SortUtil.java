package today;

import java.util.ArrayList;
import java.util.List;

public class SortUtil {

    public static List<Event> getEventByType(List<Event> events, String type) {
        List<Event> list = new ArrayList<Event>();
        for (Event event : events) {
            if (type.equals(event.getType())) {
                list.add(event);
            }
        }
        return list;
    }

}
