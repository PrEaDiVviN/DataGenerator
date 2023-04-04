import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PublicationThread extends Thread implements WorkLoadProvider<PublicationEntity> {

    private final List<PublicationEntity> publicationEntityList;
    private final Random random;
    private final PublicationProperties properties;
    private int numberOfPublications;

    // added to increase speed
    private final int stationIdSize;
    private final int citySize;
    private final int directionSize;
    private final int dateSize;

    public PublicationThread(PublicationProperties properties, int numberOfPublications) {

        this.publicationEntityList = new ArrayList<>();
        this.random = new Random();

        this.properties = properties;
        this.numberOfPublications = numberOfPublications;

        this.stationIdSize = properties.getStationIdList().size();
        this.citySize = properties.getCityList().size();
        this.directionSize = properties.getDirectionList().size();
        this.dateSize = properties.getDateList().size();
    }

    @Override
    public void run() {
        super.run();

        for (int i = 0; i < numberOfPublications; i++) {

            PublicationEntity entity = generateEntity();
            publicationEntityList.add(entity);
        }
    }

    private PublicationEntity generateEntity() {

        int stationId = properties.getStationIdList().get(random.nextInt(stationIdSize));
        String city = properties.getCityList().get(random.nextInt(citySize));
        String direction =  properties.getDirectionList().get(random.nextInt(directionSize));
        Date date = properties.getDateList().get(random.nextInt(dateSize));
        int temperature = random.nextInt(properties.getTempMin(),properties.getTempMax());
        double rain = random.nextDouble(properties.getRainMin(),properties.getRainMax());
        int wind = random.nextInt(properties.getWindMin(),properties.getWindMax());

        return new PublicationEntity(stationId, city, temperature, rain, wind, direction, date);
    }

    @Override
    public List<PublicationEntity> getWork() {

        return this.publicationEntityList;
    }

    public void setNumberOfPublications(int numberOfPublications) {

        this.numberOfPublications = numberOfPublications;
    }
}
