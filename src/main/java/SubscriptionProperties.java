public class SubscriptionProperties {

    private final int stationPercentage;
    private final int stationEqualPercentage;
    private final int cityPercentage;
    private final int directionPercentage;
    private final int datePercentage;
    private final int tempPercentage;
    private final int rainPercentage;
    private final int windPercentage;

    public int getStationPercentage() {
        return stationPercentage;
    }

    public int getStationEqualPercentage() {
        return stationEqualPercentage;
    }

    public int getCityPercentage() {
        return cityPercentage;
    }

    public int getDirectionPercentage() {
        return directionPercentage;
    }

    public int getDatePercentage() {
        return datePercentage;
    }

    public int getTempPercentage() {
        return tempPercentage;
    }

    public int getRainPercentage() {
        return rainPercentage;
    }

    public int getWindPercentage() {
        return windPercentage;
    }

    private SubscriptionProperties(SubscriptionPropertiesBuilder builder) {

        this.stationPercentage = builder.stationPercentage;
        this.stationEqualPercentage = builder.stationEqualPercentage;
        this.cityPercentage = builder.cityPercentage;
        this.directionPercentage = builder.directionPercentage;
        this.datePercentage = builder.datePercentage;
        this.tempPercentage = builder.tempPercentage;
        this.rainPercentage = builder.rainPercentage;
        this.windPercentage = builder.windPercentage;
    }

    public static SubscriptionPropertiesBuilder getBuilder() {
        return new SubscriptionPropertiesBuilder();
    }

    public static class SubscriptionPropertiesBuilder {

        private int stationPercentage;
        private int stationEqualPercentage;
        private int cityPercentage;
        private int directionPercentage;
        private int datePercentage;
        private int tempPercentage;
        private int rainPercentage;
        private int windPercentage;

        public SubscriptionPropertiesBuilder withStationPercentage(int stationPercentage) {
            this.stationPercentage = stationPercentage;
            return this;
        }

        public SubscriptionPropertiesBuilder withStationEqualPercentage(int stationEqualPercentage) {
            this.stationEqualPercentage = stationEqualPercentage;
            return this;
        }

        public SubscriptionPropertiesBuilder withCityPercentage(int cityPercentage) {
            this.cityPercentage = cityPercentage;
            return this;
        }


        public SubscriptionPropertiesBuilder withDirectionPercentage(int directionPercentage) {
            this.directionPercentage = directionPercentage;
            return this;
        }

        public SubscriptionPropertiesBuilder withDatePercentage(int datePercentage) {
            this.datePercentage = datePercentage;
            return this;
        }

        public SubscriptionPropertiesBuilder withTempPercentage(int tempPercentage) {
            this.tempPercentage = tempPercentage;
            return this;
        }

        public SubscriptionPropertiesBuilder withRainPercentage(int rainPercentage) {
            this.rainPercentage = rainPercentage;
            return this;
        }

        public SubscriptionPropertiesBuilder withWindPercentage(int windPercentage) {
            this.windPercentage = windPercentage;
            return this;
        }

        public SubscriptionProperties build() {
            return new SubscriptionProperties(this);
        }
    }
}
