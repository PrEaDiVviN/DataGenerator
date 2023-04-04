import java.util.Date;
import java.util.List;

public class PublicationProperties {

    private final List<Integer> stationIdList;
    private final List<String> cityList;
    private final List<String> directionList;
    private final List<Date> dateList;
    private final List<String> operatorList;
    private final int tempMin;
    private final int tempMax;
    private final double rainMin;
    private final double rainMax;
    private final int windMin;
    private final int windMax;

    public List<String> getOperatorList() {
        return operatorList;
    }

    public List<Integer> getStationIdList() {
        return stationIdList;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public List<String> getDirectionList() {
        return directionList;
    }

    public List<Date> getDateList() {
        return dateList;
    }

    public int getTempMin() {
        return tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public double getRainMin() {
        return rainMin;
    }

    public double getRainMax() {
        return rainMax;
    }

    public int getWindMin() {
        return windMin;
    }

    public int getWindMax() {
        return windMax;
    }

    private PublicationProperties(PublicationPropertiesBuilder builder) {

        this.stationIdList = builder.stationIdList;
        this.cityList = builder.cityList;
        this.directionList = builder.directionList;
        this.dateList = builder.dateList;
        this.operatorList = builder.operatorList;
        this.tempMin = builder.tempMin;
        this.tempMax = builder.tempMax;
        this.rainMin = builder.rainMin;
        this.rainMax = builder.rainMax;
        this.windMin = builder.windMin;
        this.windMax = builder.windMax;
    }

    public static PublicationPropertiesBuilder getBuilder() {

        return new PublicationPropertiesBuilder();
    }

    public static class PublicationPropertiesBuilder {

        private List<Integer> stationIdList;
        private List<String> cityList;
        private List<String> directionList;
        private List<Date> dateList;
        private List<String> operatorList;
        private int tempMin;
        private int tempMax;
        private double rainMin;
        private double rainMax;
        private int windMin;
        private int windMax;

        private PublicationPropertiesBuilder () {
        }

        public PublicationProperties build() {

            return new PublicationProperties(this);
        }

        public PublicationPropertiesBuilder withStationIdList(List<Integer> stationIdList) {

            this.stationIdList = stationIdList;
            return this;
        }

        public PublicationPropertiesBuilder withCityList(List<String> cityList) {

            this.cityList = cityList;
            return this;
        }

        public PublicationPropertiesBuilder withDirectionList(List<String> directionList) {

            this.directionList = directionList;
            return this;
        }

        public PublicationPropertiesBuilder withOperatorsList(List<String> operatorsList) {

            this.operatorList = operatorsList;
            return this;
        }

        public PublicationPropertiesBuilder withDateList(List<Date> dateList) {

            this.dateList = dateList;
            return this;
        }

        public PublicationPropertiesBuilder withTemp(int tempMin, int tempMax) {

            this.tempMin = tempMin;
            this.tempMax = tempMax;
            return this;
        }

        public PublicationPropertiesBuilder withRain(double rainMin, double rainMax) {

            this.rainMin = rainMin;
            this.rainMax = rainMax;
            return this;
        }

        public PublicationPropertiesBuilder withWind(int windMin, int windMax) {

            this.windMin = windMin;
            this.windMax = windMax;
            return this;
        }
    }
}
