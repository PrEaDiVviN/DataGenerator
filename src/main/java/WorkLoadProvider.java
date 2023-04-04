import java.util.List;

public interface WorkLoadProvider<T> {

    List<T> getWork();
}
