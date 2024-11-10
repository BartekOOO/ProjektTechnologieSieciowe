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
            Map<String, Object> fieldsMap = objectMapper.readValue(JSONRow, new TypeReference<Map<String, Object>>() {});

            for (Map.Entry<String, Object> entry : fieldsMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value instanceof String) {
                    fields.put(key, (String) value);
                } else {
                    fields.put(key, objectMapper.writeValueAsString(value));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public String GetJSONFieldValue(String fieldName) {
        for (Enumeration<String> keys = fields.keys(); keys.hasMoreElements(); ) {
            String key = keys.nextElement();
            String value = fields.get(key);
            if(fieldName.equals(key)) return value;
        }
        return null;
    }
}
