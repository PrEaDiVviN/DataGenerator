public class SubscriptionItem {

    private String key;
    private String operator;
    private Object value;

    public SubscriptionItem(String key, String operator, Object value) {

        this.key = key;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String toString() {

        return "(" + key + ',' + operator + ',' + value + ')';
    }
}
