import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SubscriptionPropertiesSetup {

    public static final String SEPARATOR = "=";
    private String fileName;

    public SubscriptionPropertiesSetup(String fileName) {

        this.fileName = fileName;
    }

    public SubscriptionProperties initPropertiesEntity() {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if(inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))  {

                return constructSubscriptionProperties(reader);
            } catch (IOException exception) {

                exception.printStackTrace();
            }
        }

        throw new RuntimeException("Entity could not be initialized!");
    }

    private SubscriptionProperties constructSubscriptionProperties(BufferedReader reader) {

        SubscriptionProperties.SubscriptionPropertiesBuilder builder = SubscriptionProperties.getBuilder();

        for(String line: reader.lines().toList()) {

            handleLine(line, builder);
        }

        return builder.build();
    }

    private void handleLine(String line, SubscriptionProperties.SubscriptionPropertiesBuilder builder) {

        if (line.startsWith("stationPercentage=")) {

            builder.withStationPercentage(Integer.parseInt(line.split(SEPARATOR)[1]));
        }
        else if (line.startsWith("stationEqualPercentage=")) {

            builder.withStationEqualPercentage(Integer.parseInt(line.split(SEPARATOR)[1]));
        }
        else if (line.startsWith("cityPercentage=")) {

            builder.withCityPercentage(Integer.parseInt(line.split(SEPARATOR)[1]));
        }
        else if (line.startsWith("directionPercentage=")) {

            builder.withDirectionPercentage(Integer.parseInt(line.split(SEPARATOR)[1]));
        }
        else if (line.startsWith("datePercentage=")) {

            builder.withDatePercentage(Integer.parseInt(line.split(SEPARATOR)[1]));
        }
        else if (line.startsWith("tempPercentage=")) {

            builder.withTempPercentage(Integer.parseInt(line.split(SEPARATOR)[1]));
        }
        else if (line.startsWith("rainPercentage=")) {

            builder.withRainPercentage(Integer.parseInt(line.split(SEPARATOR)[1]));
        }
        else if (line.startsWith("windPercentage=")) {

            builder.withWindPercentage(Integer.parseInt(line.split(SEPARATOR)[1]));
        }
    }
}
