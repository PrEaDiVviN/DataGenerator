import java.util.ArrayList;
import java.util.List;

public class SubscriptionDataGenerator {

    private PublicationProperties propertiesValues;
    private WorkBatch workBatch;
    private List<String> selectedKeys;
    private List<String> unselectedKeys;
    private int numberThreads;
    private int numberOfSubscriptions;
    private int stationIdEqualCount;

    public SubscriptionDataGenerator(PublicationProperties publicationProperties, WorkBatch workBatch,
                                     List<String> selectedKeys, List<String> unselectedKeys, int numberOfSubscriptions,
                                     int numberThreads, int stationIdEqualCount) {

        this.propertiesValues = publicationProperties;
        this.numberOfSubscriptions = numberOfSubscriptions;
        this.numberThreads = numberThreads;
        this.selectedKeys = selectedKeys;
        this.unselectedKeys = unselectedKeys;
        this.workBatch = workBatch;
        this.stationIdEqualCount = stationIdEqualCount;
    }

    public List<SubscriptionEntity> getWorkLoad() {

        List<SubscriptionEntity> subscriptionEntityList = new ArrayList<>();

        List<SubscriptionThread> subscriptionThreads = new ArrayList<>();
        for (int i = 0; i < numberThreads; i++) {

            subscriptionThreads.add(new SubscriptionThread(propertiesValues, workBatch, selectedKeys, unselectedKeys, numberOfSubscriptions / numberThreads));
        }
        subscriptionThreads.get(0).setNumberOfSubscription((numberOfSubscriptions / numberThreads) + (numberOfSubscriptions % numberThreads));

        for (int i = 0; i < numberThreads; i++) {

            subscriptionThreads.get(i).start();
        }

        for (int i = 0; i < numberThreads; i++) {

            try {
                subscriptionThreads.get(i).join();
                subscriptionEntityList.addAll(subscriptionThreads.get(i).getWork());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return subscriptionEntityList;
    }
}
