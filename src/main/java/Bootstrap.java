import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Bootstrap {
    private Map<String, Object> store;
    private static final String[] ENVINROMENT_NAMES = {"OPWIRE_REQUEST"};
    private Map<String, Object> args;

    public Bootstrap(Map<String, Object> args) {
        this.store = new HashMap<String, Object>();
        this.args = args;
    }

    public void loadEnv() {
        for (String envName : ENVINROMENT_NAMES) {
            Object envData = System.getenv(envName);
            if (envData instanceof String) {
                store.put(envName, new Gson().fromJson((String) envData, Map.class));
            } else {
                store.put(envName, envData);
            }
        }
    }

    public void loadInput() throws IOException {
        String content = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content += inputLine;
            }
            in.close();
        } catch (IOException e) {
            throw e;
        }

        if (content.length() > 0) {
            try {
                if ("json".equalsIgnoreCase(String.valueOf(args.get("inputFormat")))) {
                    store.put("input", new Gson().fromJson(content, Map.class));
                } else {
                    store.put("input", content);
                }
            } catch (JsonParseException e) {
                throw e;
            }
        }
    }

    public Map<String, Object> getStore() {
        return store;
    }
}
