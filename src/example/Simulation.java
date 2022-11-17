package example;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Simulation {

    public static List<Map<String, Object>> getTableContent(String table) {
        List<Map<String, Object>> list = new LinkedList<>();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("mitarbeiterID", 0);
        map.put("vorname", "Hans");
        map.put("nachname", "Peter");
        list.add(map);

        map = new LinkedHashMap<>();
        map.put("mitarbeiterID", 1);
        map.put("vorname", "Jochen");
        map.put("nachname", "Schweizer");
        list.add(map);

        map = new LinkedHashMap<>();
        map.put("mitarbeiterID", 2);
        map.put("vorname", "Franzi");
        map.put("nachname", "Müller");
        list.add(map);

        return list;
    }

}
