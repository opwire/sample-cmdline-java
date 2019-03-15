import com.beust.jcommander.Parameter;

public class CommandArgs {

    @Parameter(names = { "--input-format", "-i" })
    private String inputFormat = "json";

    @Parameter(names = { "--output-format", "-o" })
    private String outFormat = "json";

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public void setOutFormat(String outFormat) {
        this.outFormat = outFormat;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public String getOutFormat() {
        return outFormat;
    }
}
