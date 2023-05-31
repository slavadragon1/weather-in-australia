import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File input_file = new File("data_weather_australia.txt");
        File output_file = new File("output.txt");
        Scanner inp = new Scanner(input_file);
        Scanner scan = new Scanner(System.in);
        FileWriter out = new FileWriter(output_file);

        HashMap<String, Station> stationJournal = new HashMap<>();
        ArrayList<Weather> allNotes = new ArrayList<>();


        while(inp.hasNextLine())
        {
            String[] info = {"", "", "", "", "", "", "", "", "", ""};
            for(int i=0; i<10; i++)
                info[i] = inp.nextLine();

            Weather weather = new Weather(info);
            String stationName = weather.getStationName();
            if(stationJournal.containsKey(weather.getStationName()))
            {
                stationJournal.get(stationName).addWeatherNote(weather);
            } else
            {
                stationJournal.put(stationName, new Station(stationName));
                stationJournal.get(stationName).addWeatherNote(weather);
            }
            allNotes.add(weather);
        }

        System.out.println("Введите число");
        Double difference = Double.valueOf(scan.nextLine());
        String[] stations = stationJournal.keySet().toArray(new String[0]);

        stationJournal.get(stations[0])
                .getJournal()
                .keySet()
                .stream()
                .filter(date ->
                {
                    Double t1 = stationJournal.get(stations[0]).getWeatherNoteByDate(date).getAvgTemp();
                    Double t2 = stationJournal.get(stations[1]).getWeatherNoteByDate(date).getAvgTemp();
                    return Math.abs(t1 - t2) > difference;
                })
                .forEach(date ->
                {
                    Weather s1 = stationJournal.get(stations[0]).getWeatherNoteByDate(date);
                    Weather s2 = stationJournal.get(stations[1]).getWeatherNoteByDate(date);
                    System.out.println(s1.getDate());
                    System.out.printf("%-15s%10s\n",s1.getStationName(), s1.getAvgTemp());
                    System.out.printf("%-15s%10s\n",s2.getStationName(),s2.getAvgTemp());
                    System.out.println("-----------------------------------------------------------");
                });


        if(stationJournal.get(stations[0]).getPrecipitationSum() > stationJournal.get(stations[1]).getPrecipitationSum())
            System.out.println(stations[0]);
        else
            System.out.println(stations[1]);
        System.out.print("- станция с наибольшим суммарным количеством осадков.\n");

        String date = allNotes.stream().max(Comparator.comparing(Weather::getPrecipitation)).map(Weather::getDate).get();

        for(Station station: stationJournal.values())
        {
            Weather note = station.getWeatherNoteByDate(date);
            System.out.printf("%10s\n%-20s%-10s\n",note.getDate(), note.getStationName(), note.getPrecipitation());
        }




//        for(Station station: stationJournal.values())
//        {
//            for(Weather note: station.getJournal().values())
//            {
//                System.out.println(note.getStationName() + "\t" + note.getDate());
//                System.out.print("\t\t\t"+station.getWeatherNoteByDate(note.getDate()).getDate()+ "\n");
//            }
//            System.out.println("======================================");
//        }

            out.close();
            scan.close();
            inp.close();
    }
}