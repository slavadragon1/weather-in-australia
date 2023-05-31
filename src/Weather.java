public class Weather
{
    private String date;
    private String stationName;
    private Double min_temp;
    private Double max_temp;
    private Double precipitation;
    private Double evaporation;
    private Double sunTime;
    private String maxWindDirection;
    private Double maxWindSpeed;
    private String windDirection_9am;

    public Weather(String[] info)
    {
        this.date = info[0];
        this.stationName = info[1];
        try {
            this.min_temp = Double.valueOf(info[2]);
        } catch (Exception e){
            this.min_temp = -100.0;
        }
        try {
            this.max_temp = Double.valueOf(info[3]);
        } catch (Exception e){
            this.min_temp = -100.0;
        }
        try {
            this.precipitation = Double.valueOf(info[4]);
        } catch (Exception e){
            this.precipitation = -1.0;
        }
        try {
            this.evaporation = Double.valueOf(info[5]);
        } catch (Exception e) {
            this.evaporation = -1.0;
        }
        try{
            this.sunTime = Double.valueOf(info[6]);
        } catch (Exception e){
            this.sunTime = -1.0;
        }
        this.maxWindDirection = info[7];
        try {
            this.maxWindSpeed = Double.valueOf(info[8]);
        } catch (Exception e){
            this.maxWindSpeed = -1.0;
        }
        this.windDirection_9am = info[9];
    }

    public Double getAvgTemp()
    {
        return Math.abs(this.max_temp+this.min_temp)/2;
    }

    public String getDate()
    {
        return this.date;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }
}
