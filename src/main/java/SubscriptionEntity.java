public class SubscriptionEntity {

    private String stationItem;
    private String cityItem;
    private String directionItem;
    private String dateItem;
    private String tempItem;
    private String rainItem;
    private String windItem;

    public SubscriptionEntity(SubscriptionBuilder builder) {

        this.stationItem = builder.stationItem;
        this.cityItem = builder.cityItem;
        this.directionItem = builder.directionItem;
        this.dateItem = builder.dateItem;
        this.tempItem = builder.tempItem;
        this.rainItem = builder.rainItem;
        this.windItem = builder.windItem;
    }

    public String getStationItem() {
        return stationItem;
    }

    public void setStationItem(String stationItem) {
        this.stationItem = stationItem;
    }

    public static SubscriptionBuilder getBuilder() {
        return new SubscriptionBuilder();
    }

    public static class SubscriptionBuilder {

        private String stationItem;
        private String cityItem;
        private String directionItem;
        private String dateItem;
        private String tempItem;
        private String rainItem;
        private String windItem;

        public SubscriptionBuilder withStationItem(String stationItem) {
            this.stationItem = stationItem;
            return this;
        }

        public SubscriptionBuilder withCityItem(String cityItem) {
            this.cityItem = cityItem;
            return this;
        }

        public SubscriptionBuilder withDirectionItem(String directionItem) {
            this.directionItem = directionItem;
            return this;
        }

        public SubscriptionBuilder withDateItem(String dateItem) {
            this.dateItem = dateItem;
            return this;
        }

        public SubscriptionBuilder withTempItem(String tempItem) {
            this.tempItem = tempItem;
            return this;
        }

        public SubscriptionBuilder withRainItem(String rainItem) {
            this.rainItem = rainItem;
            return this;
        }

        public SubscriptionBuilder withWindItem(String windItem) {
            this.windItem = windItem;
            return this;
        }

        public SubscriptionEntity build() {
            return new SubscriptionEntity(this);
        }
    }

    @Override
    public String toString() {
        return "{" +
                (stationItem != null ? stationItem + ";" : "") +
                (cityItem != null ? cityItem + ";" : "") +
                (directionItem != null ? directionItem + ";" : "") +
                (dateItem != null ? dateItem + ";" : "") +
                (tempItem != null ? tempItem + ";" : "") +
                (rainItem != null ? rainItem + ";" : "") +
                (windItem != null ? windItem + ";" : "") +
                '}';
    }
}
