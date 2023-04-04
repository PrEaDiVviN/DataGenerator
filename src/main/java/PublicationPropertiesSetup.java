import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class PublicationPropertiesSetup {

    private final String CSV_SPLIT_CHAR = ",";

    private String fileName;


    public PublicationPropertiesSetup(String fileName) {

        this.fileName = fileName;
    }

    public PublicationProperties initPropertiesEntity() {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if(inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))  {

                return constructPublicationProperties(reader);
            } catch (IOException exception) {

                exception.printStackTrace();
            }
        }

        throw new RuntimeException("Entity could not be initialized!");
    }

    private PublicationProperties constructPublicationProperties(BufferedReader reader) {

        PublicationProperties.PublicationPropertiesBuilder builder = PublicationProperties.getBuilder();

        for(String line: reader.lines().toList()) {

            handleLine(line, builder);
        }

        return builder.build();
    }

    private void handleLine(String line,
            PublicationProperties.PublicationPropertiesBuilder builder) {

        String[] csvEntries = new String [] {"stationId=", "city=", "direction=", "date=", "operators="};
        for (String entry: csvEntries) {

            if(line.startsWith(entry)) {
                line = line.replace(entry, "");

                if (entry.equals(csvEntries[0])) {
                    builder.withStationIdList(Arrays.stream(line.split(CSV_SPLIT_CHAR)).map(Integer::valueOf).toList());
                }
                else if (entry.equals(csvEntries[1])) {
                    builder.withCityList(Arrays.stream(line.split(CSV_SPLIT_CHAR)).toList());
                }
                else if (entry.equals(csvEntries[2])) {
                    builder.withDirectionList(Arrays.stream(line.split(CSV_SPLIT_CHAR)).toList());
                }
                else if(entry.equals(csvEntries[4])) {
                    builder.withOperatorsList(Arrays.stream(line.split(CSV_SPLIT_CHAR)).toList());
                }
                else if (entry.equals(csvEntries[3])) {
                    builder.withDateList(Arrays.stream(line.split(CSV_SPLIT_CHAR)).map((item) -> {
                        try {
                            return new SimpleDateFormat("yyyy-MM-dd").parse(item);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }).toList());
                }
            }

        }

        String[] rangeEntries = new String [] {"tempRange=", "rainRange=", "windRange="};
        for (String entry: rangeEntries) {
            if(line.startsWith(entry)) {
                line = line.split("=")[1];
                line = line.replace("[","").replace("]","");

                if(entry.equals(rangeEntries[0])) {

                    int tempMin = Integer.parseInt(line.split(CSV_SPLIT_CHAR)[0]);
                    int tempMax = Integer.parseInt(line.split(CSV_SPLIT_CHAR)[1]);

                    builder.withTemp(tempMin, tempMax);
                }
                else if(entry.equals(rangeEntries[1])) {

                    double rainMin = Double.parseDouble(line.split(CSV_SPLIT_CHAR)[0]);
                    double rainMax = Double.parseDouble(line.split(CSV_SPLIT_CHAR)[1]);

                    builder.withRain(rainMin, rainMax);
                }
                else if(entry.equals(rangeEntries[2])) {

                    int windMin = Integer.parseInt(line.split(CSV_SPLIT_CHAR)[0]);
                    int windMax = Integer.parseInt(line.split(CSV_SPLIT_CHAR)[1]);

                    builder.withWind(windMin, windMax);
                }
            }
        }
    }

}
