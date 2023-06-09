public class Main {

    private static final String PUBLICATIONS_PROPERTIES_FILEPATH = "publications.properties";
    private static final String PUBLICATIONS_OUTPUT = "src/main/java/generation/publication.txt";

    private static final String SUBSCRIPTION_PROPERTIES_FILEPATH = "subscriptions.properties";
    private static final String SUBSCRIPTION_OUTPUT = "src/main/java/generation/subscription.txt";

    public static void main(String[] args) {
        
        PublicationDataManager manager = new PublicationDataManager(PUBLICATIONS_PROPERTIES_FILEPATH,
                PUBLICATIONS_OUTPUT, 4, 10000);
        manager.startWork();

//        SubscriptionDataManager manager = new SubscriptionDataManager(PUBLICATIONS_PROPERTIES_FILEPATH,
//                SUBSCRIPTION_PROPERTIES_FILEPATH, SUBSCRIPTION_OUTPUT, 4, 100);
//        manager.startWork();

    }


}
