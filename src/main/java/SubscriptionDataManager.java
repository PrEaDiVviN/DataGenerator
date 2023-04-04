import java.util.*;

public class SubscriptionDataManager {

    private String outputFile;
    private PublicationProperties propertiesValues;
    private SubscriptionProperties propertiesKeys;
    private int numberThreads;
    private int numberOfSubscriptions;
    private int stationIdEqualCount;
    private List<String> selectedKeyList;
    private List<String> unselectedKeyList;
    private WorkBatch workBatch;

    public SubscriptionDataManager(String publicationFile, String subscriptionFile, String outputFile, int numberThreads, int numberOfSubscriptions) {

        PublicationPropertiesSetup publicationPropertiesSetup = new PublicationPropertiesSetup(publicationFile);
        SubscriptionPropertiesSetup subscriptionPropertiesSetup = new SubscriptionPropertiesSetup(subscriptionFile);
        this.propertiesValues = publicationPropertiesSetup.initPropertiesEntity();
        this.propertiesKeys = subscriptionPropertiesSetup.initPropertiesEntity();

        this.outputFile = outputFile;
        this.numberThreads = numberThreads;
        this.numberOfSubscriptions = numberOfSubscriptions;

        stationIdEqualCount = (int)Math.ceil(numberOfSubscriptions * propertiesKeys.getStationPercentage() / 100f * propertiesKeys.getStationEqualPercentage() / 100f);

        initKeysLists();
        initWorkBatch();
    }

    private void initKeysLists() {
        selectedKeyList = new ArrayList<>();
        unselectedKeyList = new ArrayList<>();

        Map<String, Integer> mapPropertyToValue = new HashMap<>();
        mapPropertyToValue.put("stationId", propertiesKeys.getStationPercentage());
        mapPropertyToValue.put("city", propertiesKeys.getCityPercentage());
        mapPropertyToValue.put("direction", propertiesKeys.getDirectionPercentage());
        mapPropertyToValue.put("date", propertiesKeys.getDatePercentage());
        mapPropertyToValue.put("temperature", propertiesKeys.getTempPercentage());
        mapPropertyToValue.put("rain", propertiesKeys.getRainPercentage());
        mapPropertyToValue.put("wind", propertiesKeys.getWindPercentage());

        List<String> keyList = Arrays.asList("stationId", "city", "direction", "date", "temperature", "rain", "wind");
        for(String key: keyList) {

            if(mapPropertyToValue.get(key) == -1) {
                unselectedKeyList.add(key);
            }
            else {
                selectedKeyList.add(key);
            }
        }
    }

    private void initWorkBatch() {

        Map<String, Integer> mapKeyToCounter = new HashMap<>();
        if(propertiesKeys.getStationPercentage() != -1)
            mapKeyToCounter.put("stationId", numberOfSubscriptions * propertiesKeys.getStationPercentage() / 100);
        if(propertiesKeys.getCityPercentage() != -1)
            mapKeyToCounter.put("city", numberOfSubscriptions * propertiesKeys.getCityPercentage() / 100);
        if(propertiesKeys.getDirectionPercentage() != -1)
            mapKeyToCounter.put("direction", numberOfSubscriptions * propertiesKeys.getDirectionPercentage() / 100);
        if(propertiesKeys.getDatePercentage() != -1)
            mapKeyToCounter.put("date", numberOfSubscriptions * propertiesKeys.getDatePercentage() / 100);
        if(propertiesKeys.getTempPercentage() != -1)
            mapKeyToCounter.put("temperature", numberOfSubscriptions * propertiesKeys.getTempPercentage() / 100);
        if(propertiesKeys.getRainPercentage() != -1)
            mapKeyToCounter.put("rain", numberOfSubscriptions * propertiesKeys.getRainPercentage() / 100);
        if(propertiesKeys.getWindPercentage() != -1)
            mapKeyToCounter.put("wind", numberOfSubscriptions * propertiesKeys.getWindPercentage() / 100);

        workBatch = new WorkBatch(mapKeyToCounter, stationIdEqualCount);
    }

    public void startWork() {

        SubscriptionDataGenerator generator = new SubscriptionDataGenerator(propertiesValues, workBatch, selectedKeyList,
                unselectedKeyList, numberOfSubscriptions, numberThreads, stationIdEqualCount);

        List<SubscriptionEntity> entityList = generator.getWorkLoad();

        DataWriter<SubscriptionEntity> writer = new DataWriter<>(entityList, outputFile);

        writer.saveData();
    }
}
