import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        var elements = parsingCsvFile();
        purificationAndSendToDatabase(elements);
        var dataFromDataBase = ConnectionDataBase.readDataBase();
        var sortedDataFromDataBase = sortingDataFromDataBase(dataFromDataBase);
        sendToMakeGraph(sortedDataFromDataBase);
        var averageMagnitude = gettingAverageMagnitude(dataFromDataBase, "West Virginia");
        var stateTheDeepestEarthquake = gettingTheDeepestEarthquake(dataFromDataBase);
        System.out.println("Средняя магнитуда для штата West Virginia: " + averageMagnitude);
        System.out.println("Название штата, в котором произошло самое глубокое землятресение: " + stateTheDeepestEarthquake);
    }

    private static List<String> parsingCsvFile() {
        var file = "src\\Землетрясения.csv";
        var line = "";
        String[] row;
        BufferedReader reader;
        List<String> elements = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                if ((line.length() - line.replaceAll(",", "").length()) > 5) {
                    line = line.replaceAll(", ", " ");
                }
                row = line.split(",");
                elements.addAll(Arrays.asList(row));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }

    private static void purificationAndSendToDatabase(List<String> elements) throws SQLException, ClassNotFoundException {
        List<String> sliceOfElements = new ArrayList<>();
        for (int index = 6; index < elements.size(); index++) {
            sliceOfElements.add(elements.get(index));
            if (sliceOfElements.size() >= 6) {
                sliceOfElements.remove(0);
                sliceOfElements.remove(1);
                DataBase.main(sliceOfElements);
                sliceOfElements.clear();
            }
        }
    }

    private static Double gettingAverageMagnitude(List<String> dataFromDataBase, String state) {
        var averageMagnitude = 0.0;
        var count = 0;
        for (int i = 2; i < dataFromDataBase.size(); i += 4) {
            if (Objects.equals(dataFromDataBase.get(i).toLowerCase(), state.toLowerCase())) {
                count++;
                averageMagnitude += Float.parseFloat(dataFromDataBase.get(i - 1));
            }
        }
        return averageMagnitude / count;
    }

    private static String gettingTheDeepestEarthquake(List<String> dataFromDataBase) {
        List<Integer> depth = new ArrayList<>();
        for (int i = 0; i < dataFromDataBase.size(); i += 4) {
            if (Objects.equals(dataFromDataBase.get(i + 3).substring(0, 4), "2013")) {
                depth.add(Integer.parseInt(dataFromDataBase.get(i)));
            }
        }
        return dataFromDataBase.get(dataFromDataBase.indexOf(Integer.toString(Collections.max(depth))) + 2);
    }

    private static List<String> sortingDataFromDataBase(List<String> dataFromDataBase) {
        List<String> stateAndData = new ArrayList<>();
        List<String> sortedDataFromDataBase = new ArrayList<>();
        Set<String> numberOfEarthquakes = new HashSet<>();

        //очистка даты
        for (int index = 3; index < dataFromDataBase.size(); index += 4) {
            Collections.replaceAll(dataFromDataBase, dataFromDataBase.get(index), dataFromDataBase.get(index).substring(0, 4));
        }

        //объединение штата и времени для простоты подсчета количества землятресений в каждом штате
        for (int index = 2; index < dataFromDataBase.size(); index += 4) {
            stateAndData.add(dataFromDataBase.get(index) + "&" + dataFromDataBase.get(index + 1));
        }

        //подсчет количества землятресений в каждом штате
        for (String s : stateAndData) {
            numberOfEarthquakes.add(s + "&" + Collections.frequency(stateAndData, s));
        }

        //упорядочивание
        for (int year = 1973; year <= 2015; year++) {
            for (String s : numberOfEarthquakes) {
                if (Objects.equals(s.split("&")[1], Integer.toString(year))) {
                    sortedDataFromDataBase.add(s);
                }
            }
        }

        return sortedDataFromDataBase;
    }

    private static void sendToMakeGraph(List<String> sortedDataFromDataBase) {
        List<String> infoForGraph = new ArrayList<>();
        for (int year = 1973; year <= 2015; year++) {
            double amountByState = 0.0;
            double countByYear = 0.0;
            for (String s : sortedDataFromDataBase) {
                if (Objects.equals(s.split("&")[1], Integer.toString(year))) {
                    countByYear++;
                    amountByState += Float.parseFloat(s.split("&")[2]);
                }
            }
            if (countByYear != 0.0) {
                infoForGraph.add(amountByState / countByYear + "&" + year);
            }
        }
        Graph.main(infoForGraph);
    }
}