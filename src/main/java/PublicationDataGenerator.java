import java.util.ArrayList;
import java.util.List;

public class PublicationDataGenerator {

    private PublicationProperties properties;
    private int numberThreads;
    private int numberOfPublications;

    public PublicationDataGenerator(PublicationProperties publicationProperties, int numberOfPublications, int numberThreads) {

        this.properties = publicationProperties;
        this.numberOfPublications = numberOfPublications;
        this.numberThreads = numberThreads;
    }

    public List<PublicationEntity> getWorkLoad() {

        List<PublicationEntity> publicationEntityList = new ArrayList<>();

        List<PublicationThread> publicationThreadList = new ArrayList<>();
        for (int i = 0; i < numberThreads; i++) {

            publicationThreadList.add(new PublicationThread(properties, numberOfPublications / numberThreads));
        }
        publicationThreadList.get(0).setNumberOfPublications((numberOfPublications / numberThreads) + (numberOfPublications % numberThreads));

        for (int i = 0; i < numberThreads; i++) {

            publicationThreadList.get(i).start();
        }

        for (int i = 0; i < numberThreads; i++) {

            try {
                publicationThreadList.get(i).join();
                publicationEntityList.addAll(publicationThreadList.get(i).getWork());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return publicationEntityList;
    }
}
