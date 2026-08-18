package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataDriven {
    private static final String FILE_PATH = "src/test/resources/testData/testData.json";

    public static JSONObject jsonReader() throws IOException {
        FileReader reader = new FileReader(FILE_PATH);
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject jsonObject = new JSONObject(tokener);
        reader.close();
        return jsonObject;
    }

    public static JSONObject getObject(String key) throws IOException {
        return jsonReader().getJSONObject(key);
    }

    public static List<String> getStringList(String key) throws IOException {
        JSONArray array = jsonReader().getJSONArray(key);
        List<String> values = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            values.add(array.getString(i));
        }
        return values;
    }
}
