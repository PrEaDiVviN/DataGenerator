import java.util.Map;

public class WorkBatch {

    private Map<String, Integer> mandatory;
    private int toDeleteStationIds;

    public WorkBatch(Map<String, Integer> mandatory, int toDeleteStationIds) {

        this.mandatory = mandatory;
        this.toDeleteStationIds = toDeleteStationIds;
    }

    public boolean decrementPropertyIfExists(String property) {

        synchronized (property) {

            int value = mandatory.get(property) - 1;
            if (value < 0) {
                return false;
            }

            mandatory.put(property, value);
            return true;
        }
    }

    public boolean decrementEqualStationIfExists() {

        synchronized (this) {

            if(toDeleteStationIds > 0) {
                toDeleteStationIds--;
                return true;
            }

            return false;
        }
    }

}
