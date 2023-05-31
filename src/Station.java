import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Station
{
    private String stationName;

    private HashMap<String, Weather> journal;


    public Station(String name)
    {
        this.stationName = name;
        this. journal = new HashMap<>();
    }

    public void addWeatherNote(Weather weather)
    {
        this.journal.put(weather.getDate(), weather);
    }

    public Weather getWeatherNoteByDate(String date)
    {
        return journal.get(date);
    }

    public Double getPrecipitationSum()
    {
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        journal.values().stream().map(Weather::getPrecipitation).forEach(c -> sum.updateAndGet(v -> v + c));
        return Double.valueOf(String.valueOf(sum));
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public HashMap<String, Weather> getJournal() {
        return journal;
    }

    public void setJournal(HashMap<String, Weather> journal) {
        this.journal = journal;
    }
}
