import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter<T> {

    private List<T> data;
    private String outputFile;

    public DataWriter(List<T> data, String outputFile) {

        this.data = data;
        this.outputFile = outputFile;
    }

    public void saveData() {

        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i < data.size(); i++) {
            stringBuilder.append(data.get(i).toString()).append("\n");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(stringBuilder.toString());

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
