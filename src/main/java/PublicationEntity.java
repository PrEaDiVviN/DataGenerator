import java.text.SimpleDateFormat;
import java.util.Date;

public class PublicationEntity {

    private int stationId;
    private String city;
    private int temperature;
    private double rain;
    private int wind;
    private String direction;
    private Date date;

    public PublicationEntity(int stationId, String city, int temperature, double rain, int wind, String direction, Date date) {
        this.stationId = stationId;
        this.city = city;
        this.temperature = temperature;
        this.rain = rain;
        this.wind = wind;
        this.direction = direction;
        this.date = date;
    }

    @Override
    public String toString() {
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(date);

        return "{(stationId," + stationId + ")"+
                ";(city," + city + ')' +
                ";(temp," + temperature + ')' +
                ";(rain," + rain + ')' +
                ";(wind," + wind + ')' +
                ";(direction," + direction + ')' +
                ";(date," + formattedDate + ")}";
    }
}
