import java.util.*;

public class SubscriptionThread extends Thread implements WorkLoadProvider<SubscriptionEntity> {

    private List<SubscriptionEntity> subscriptionEntityList;
    private final Random random;
    private final PublicationProperties propertiesValues;
    private int numberOfSubscription;
    private WorkBatch workBatch;
    private List<String> selectedKeys;
    private List<String> unselectedKeys;


    // added to increase speed
    private final int stationIdSize;
    private final int citySize;
    private final int directionSize;
    private final int dateSize;
    private final int operatorSize;

    public SubscriptionThread(PublicationProperties propertiesValues, WorkBatch workBatch, List<String> selectedKeys,
             List<String> unselectedKeys, int numberOfSubscription) {

        this.subscriptionEntityList = new ArrayList<>();
        this.random = new Random();

        this.propertiesValues = propertiesValues;
        this.numberOfSubscription = numberOfSubscription;
        this.workBatch = workBatch;
        this.selectedKeys = selectedKeys;
        this.unselectedKeys = unselectedKeys;

        this.stationIdSize = propertiesValues.getStationIdList().size();
        this.citySize = propertiesValues.getCityList().size();
        this.directionSize = propertiesValues.getDirectionList().size();
        this.dateSize = propertiesValues.getDateList().size();
        this.operatorSize = propertiesValues.getOperatorList().size();
    }

    @Override
    public void run() {
        super.run();

        for (int i = 0; i < numberOfSubscription; i++) {

            List<String> keyList = generateKeyList();
            SubscriptionEntity entity = generateEntity(keyList);
            subscriptionEntityList.add(entity);
        }

        setEqualKey();

        Collections.shuffle(subscriptionEntityList);
    }

    private void setEqualKey() {

        for (int i = 0; i < subscriptionEntityList.size(); i++) {
            if(subscriptionEntityList.get(i).getStationItem() != null) {

                if(!workBatch.decrementEqualStationIfExists())
                    return ;

                List<String> operators = List.of(",<,",",>,",",!=,",",>=,",",<=,", ",=,");
                for (String op: operators) {
                    if (subscriptionEntityList.get(i).getStationItem().contains(op)) {
                        subscriptionEntityList.get(i).setStationItem(subscriptionEntityList.get(i).getStationItem().replace(op, ",=,"));
                        break;
                    }
                }
            }

        }
    }

    private List<String> generateKeyList() {

        List<String> generateList = new ArrayList<>();

        for (String mandatory: selectedKeys) {
            boolean existsKey = workBatch.decrementPropertyIfExists(mandatory);
            if(!existsKey) continue;
            generateList.add(mandatory);
        }

        for (String optional: unselectedKeys) {
            boolean existsKey = random.nextBoolean();
            if(!existsKey) continue;
            generateList.add(optional);
        }

        if(generateList.size() == 0 && unselectedKeys.size() > 0) {
            generateList.add(unselectedKeys.get(random.nextInt(unselectedKeys.size())));
        }

        return generateList;
    }

    private SubscriptionEntity generateEntity(List<String> keys) {

        SubscriptionEntity.SubscriptionBuilder subscriptionEntityBuilder = SubscriptionEntity.getBuilder();

        String operator = propertiesValues.getOperatorList().get(random.nextInt(operatorSize));

        for (String key: keys) {
            if(key.equals("stationId")) {
                int stationId = propertiesValues.getStationIdList().get(random.nextInt(stationIdSize));
                subscriptionEntityBuilder.withStationItem(new SubscriptionItem(key, operator, stationId).toString());
            }

            else if(key.equals("city")) {
                String city = propertiesValues.getCityList().get(random.nextInt(citySize));
                subscriptionEntityBuilder.withCityItem(new SubscriptionItem(key, operator, city).toString());
            }

            else if(key.equals("direction")) {
                String direction = propertiesValues.getDirectionList().get(random.nextInt(directionSize));
                subscriptionEntityBuilder.withDirectionItem(new SubscriptionItem(key, operator, direction).toString());
            }

            else if(key.equals("date")) {
                Date date = propertiesValues.getDateList().get(random.nextInt(dateSize));
                subscriptionEntityBuilder.withDateItem(new SubscriptionItem(key, operator, date).toString());
            }

            else if(key.equals("temperature")) {
                int temperature = random.nextInt(propertiesValues.getTempMin(),propertiesValues.getTempMax());
                subscriptionEntityBuilder.withTempItem(new SubscriptionItem(key, operator, temperature).toString());
            }

            else if(key.equals("rain")) {
                double rain =  random.nextDouble(propertiesValues.getRainMin(),propertiesValues.getRainMax());
                subscriptionEntityBuilder.withRainItem(new SubscriptionItem(key, operator, rain).toString());
            }

            else if(key.equals("wind")) {
                int wind = random.nextInt(propertiesValues.getWindMin(),propertiesValues.getWindMax());
                subscriptionEntityBuilder.withWindItem(new SubscriptionItem(key, operator, wind).toString());
            }
        }

        return new SubscriptionEntity(subscriptionEntityBuilder);
    }

    @Override
    public List<SubscriptionEntity> getWork() {

        return this.subscriptionEntityList;
    }

    public void setNumberOfSubscription(int numberOfSubscription) {
        this.numberOfSubscription = numberOfSubscription;
    }
}
