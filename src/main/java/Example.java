import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class Example {

    public static void main(String[] args) {

        Map<String, Object> commandArgs = commandArgs(args);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {

            Bootstrap example = new Bootstrap(commandArgs);
            example.loadEnv();
            example.loadInput();

            if ("json".equalsIgnoreCase(String.valueOf(commandArgs.get("outputFormat")))) {
                System.out.println(gson.toJson(example.getStore()));
            } else {
                System.out.println(example.getStore());
            }

        } catch (Exception e) {

            Map<String, Object> err = new HashMap<String, Object>();
            err.put("name", e.getClass().getSimpleName());
            err.put("message", e.getMessage());
            err.put("stack", e.getStackTrace());

            if ("json".equalsIgnoreCase(String.valueOf(commandArgs.get("outputFormat")))) {
                System.err.println(gson.toJson(err));
            } else {
                System.err.println(err);
            }

            System.exit(1);
        }
    }

    public static Map<String, Object> commandArgs(String[] args) {
        Map<String, Object> commandArgsMap = new HashMap<String, Object>();
        CommandArgs commandArgs = new CommandArgs();
        try {
            JCommander.newBuilder().addObject(commandArgs).build().parse(args);
        } catch (Exception e) {
            /* Process  */
        }
        commandArgsMap.put("inputFormat", commandArgs.getInputFormat());
        commandArgsMap.put("outputFormat", commandArgs.getOutFormat());

        return commandArgsMap;
    }
}
