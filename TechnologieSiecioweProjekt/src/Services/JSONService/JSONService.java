package Services.JSONService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.List;

public class JSONService {
    private Dictionary<String,String> fields = new Hashtable<>();

    public JSONService(String JSONRow) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Deserializacja JSON-a do Mapy
            Map<String, Object> fieldsMap = objectMapper.readValue(JSONRow, new TypeReference<Map<String, Object>>() {});

            // Iteracja po mapie i dodanie wartości do słownika
            for (Map.Entry<String, Object> entry : fieldsMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                // Jeżeli wartość jest obiektem (np. lista, mapa), możesz to traktować jak JSON String
                if (value instanceof String) {
                    fields.put(key, (String) value);
                } else {
                    // Zmieniamy inne obiekty na JSON String
                    fields.put(key, objectMapper.writeValueAsString(value));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public String getJSONFieldValue(String fieldName) {
        for (Enumeration<String> keys = fields.keys(); keys.hasMoreElements(); ) {
            String key = keys.nextElement();
            String value = fields.get(key);
            if(fieldName.equals(key)) return value;
        }
        return null;
    }
}
