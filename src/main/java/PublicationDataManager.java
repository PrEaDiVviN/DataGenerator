import java.util.List;

public class PublicationDataManager {

    private String outputFile;
    private PublicationProperties properties;
    private int numberThreads;
    private int numberOfPublications;

    public PublicationDataManager(String propertiesFile, String outputFile, int numberThreads, int numberOfPublications) {

        PublicationPropertiesSetup publicationPropertiesSetup = new PublicationPropertiesSetup(propertiesFile);
        this.properties = publicationPropertiesSetup.initPropertiesEntity();

        this.outputFile = outputFile;
        this.numberThreads = numberThreads;
        this.numberOfPublications = numberOfPublications;
    }

    public void startWork() {

        PublicationDataGenerator generator = new PublicationDataGenerator(properties, numberOfPublications, numberThreads);

        List<PublicationEntity> entityList= generator.getWorkLoad();

        DataWriter<PublicationEntity> writer = new DataWriter<>(entityList, outputFile);

        writer.saveData();
    }
}
