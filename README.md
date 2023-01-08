# Итоговый проект 
___
### Студент: Крылов Никита Андреевич РИ-210945 (AT-01)
### Вариант: 5
___

### Оглавление:

* [Создание структуры проекта и основных методов](#1)

* [Парсинг данных](#2)

* [Создание базы данных (SQL Lite) и подключение к проекту](#3)

* [Выполнение 2 задачи](#4)

* [Выполнение 3 задачи](#5)

* [Выполнение 1 задачи](#6)

<a name="1"></a>

### Создание структуры проекта и основных методов

После прочтения задания мы можем создать структуру проекта:

![image](https://user-images.githubusercontent.com/114663524/210892187-6446c488-da09-4980-9f5e-53c50778ce00.png)

Файл ***Main.java*** является основным, в нем мы будем писать основную логику программы и будем взаимодействовать с другими файлами в нашем проекте

Файл ***DataBase.java*** будет содержать в себе необходимые классы и методы для взаимодействия с базой данных

Файл ***Graph.java*** будет содержать в себе необходимые методы для построения графика

<br />

>Файл ***Main.java***

Создадим нужные нам методы:
```Java
public class Main {
    public static void main(String[] args) {
        
    }

    private static void parsingCsvFile() {
        
    }

    private static void purificationAndSendToDatabase() {
        
    }

    private static void gettingAverageMagnitude() {
        
    }

    private static void gettingTheDeepestEarthquake() {
        
    }

    private static void sortingDataFromDataBase() {
        
    }

    private static void sendToMakeGraph() {
        
    }
}
```
Метод `main` будет вызывать все остальные методы и перенаправлять данные 

Метод `parsingCsvFile` будет парсить файл, который находится в задании

Метод `purificationAndSendToDatabase` будет получать данные из метода `parsingCsvFile` и отправлять их в файл ***DataBase.java*** для добавления в базу данных 

Метод `gettingAverageMagnitude` будет получать данные из базы данных и выполнять 2 задачу (Выведите в консоль среднюю магнитуду для штата "West Virginia")

Метод `gettingTheDeepestEarthquake` будет брать данные из базы данных и выполнять 3 задачу (Выведите в консоль название штата, в котором произошло самое глубокое землетрясение в 2013 году)

Метод `sortingDataFromDataBase` будет брать данные из базы данных и сортировать их для последующей обработи в методе `sendToMakeGraph`

Метод `sendToMakeGraph` обрабатывает данные из метода `sortingDataFromDataBase` и отправляет данные в файл ***Graph.java*** для выполнения 1 задачи (Постройте график по среднему количеству землетрясений для каждого года)
____

<a name="2"></a>

### Парсинг данных 

Добавим в наш проект файл ***Землетрясения.csv*** из задания

![image](https://user-images.githubusercontent.com/114663524/210904699-a0b066f1-af4a-4c15-9081-80469fb5a42c.png)

<br />

> Файл ***Main.java***

Пропишем метод `parsingCsvFile` и выведем результат на экран:

```Java
public class Main {
    public static void main(String[] args) {
        var elements = parsingCsvFile();
        System.out.println(elements);
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

    private static void purificationAndSendToDatabase() {

    }

    private static void gettingAverageMagnitude() {

    }

    private static void gettingTheDeepestEarthquake() {

    }

    private static void sortingDataFromDataBase() {

    }

    private static void sendToMakeGraph() {

    }
}
```

Получаем вывод: 

![image](https://user-images.githubusercontent.com/114663524/210906191-627516d5-1cad-4b74-b1a5-ba80b327054c.png)

___

<a name="3"></a>

### Создание базы данных (SQL Lite) и подключение к проекту

<br />

> Файл ***DataBase.java***

Создадим класс `ConnectionDataBase` который будет присоединяться и работать с базой данных (SQL Lite):

```Java
public class DataBase {

    public static void main() {
        
    }
}

class ConnectionDataBase {
    
}
```

По задумке метод `main` в классе `DataBase` будет последовательно проходиться по методам класса `ConnectionDataBase` 

Пропишем методы класса `ConnectionDataBase`:

```Java
public class DataBase {

    public static void main() {
        
    }
}

class ConnectionDataBase {

    public static java.sql.Connection conn;
    
    public static Statement statement;
    

    public static void connection() {

    }

    public static void createDataBase() {

    }

    public static void writeDataBase() {

    }

    public static void closeDataBase() throws SQLException {
        conn.close();
        statement.close();
    }
}
```

Метод `connection` будет подключаться к базе данных 

Метод `createDataBase` будет создавать таблицу 

Метод `writeDataBase` будет заполнять таблицу нужными данными 

Метод `closeDataBase` будет закрывать соединение с базой данных

<br />

Попробуем создать тестовую базу данных с 6 колонками и 6 значениями:

> Для корректной работы метода `connection` необходимо установить драйвер SQLite JDBC 

```Java
class ConnectionDataBase {

    public static java.sql.Connection conn;
    
    public static Statement statement;
    

    public static void connection() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:src\\data.db");
    }

    public static void createDataBase() throws SQLException {
        statement = conn.createStatement();
        statement.execute("CREATE TABLE if not exists 'data' (column1 STRING, column2 STRING, column3 STRING, column4 STRING, column5 STRING, column6 STRING, UNIQUE (column1, column2, column3, column4, column5, column6));");
    }

    public static void writeDataBase() throws SQLException {
        statement.execute(String.format("INSERT OR REPLACE INTO 'data'('column1', 'column2', 'column3', 'column4', 'column5', '6') " +
                "VALUES ('test1', 'test2', 'test3', 'test4', 'test5', 'test6');"));
    }

    public static void closeDataBase() throws SQLException {
        conn.close();
        statement.close();
    }
}

```

<br />

Пропишем вызовы ранее написанных методов:

```Java
public class DataBase {

    public static void main() throws ClassNotFoundException, SQLException {
        ConnectionDataBase.connection();
        ConnectionDataBase.createDataBase();
        ConnectionDataBase.writeDataBase();
        ConnectionDataBase.closeDataBase();
    }
}

class ConnectionDataBase {

    public static java.sql.Connection conn;
    
    public static Statement statement;

    public static void connection() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:src\\data.db");
    }

    public static void createDataBase() throws SQLException {
        statement = conn.createStatement();
        statement.execute("CREATE TABLE if not exists 'data' (column1 STRING, column2 STRING, column3 STRING, column4 STRING, column5 STRING, column6 STRING, UNIQUE (column1, column2, column3, column4, column5, column6));");
    }

    public static void writeDataBase() throws SQLException {
        statement.execute(String.format("INSERT OR REPLACE INTO 'data'('column1', 'column2', 'column3', 'column4', 'column5', '6') " +
                "VALUES ('test1', 'test2', 'test3', 'test4', 'test5', 'test6');"));
    }

    public static void closeDataBase() throws SQLException {
        conn.close();
        statement.close();
    }
}
```

<br /> 

> Файл ***Main.java*** 

Вызовем метод `main` класса `DataBase`:

```Java
public static void main(String[] args) throws SQLException, ClassNotFoundException {
        var elements = parsingCsvFile();
        DataBase.main();
    }
```

Теперь запустим и увидим что у нас создался файл ***data.db***:

![image](https://user-images.githubusercontent.com/114663524/211056722-392deeed-3405-4d94-a944-86812c8c6e4e.png)

Откроем этот файл через программу DB Browser (SQLite):

![image](https://user-images.githubusercontent.com/114663524/211057069-803abaf8-e17a-4d9d-b8a3-074e16ac9233.png)

Теперь нам нужно передать данные из нашего csv файла в базу данных

Сначала определимся с тем, какие данные нам нужны по заданию (Глубина в метрах, Магнитуда, Штат, Время)

<br />

> Файл ***DataBase.java***

Создадим 4 колонки:

```Java
public static void createDataBase() throws SQLException {
        statement = conn.createStatement();
        statement.execute("CREATE TABLE if not exists 'data' (Depth STRING, Magnitude STRING, State STRING, Time String, UNIQUE (Depth, Magnitude, State, Time));");
    }
``` 

<br />

> Файл ***Main.java***

Теперь наша задача передать распарсенные данные в файл ***DataBase.java***

Прописываем метод `purificationAndSendToDatabase`, в нем мы удаляем ненужные нам данные и отправляем обработанные данные для добавления их в базу данных:

```Java
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
```

<br />

> Файл ***DataBase.java***

Помещаем все данные в метод `writeDataBase`:

```Java
public class DataBase {

    public static void main(List<String> elements) throws ClassNotFoundException, SQLException {
        ConnectionDataBase.connection();
        ConnectionDataBase.createDataBase();
        ConnectionDataBase.writeDataBase(elements.get(0), elements.get(1), elements.get(2), elements.get(3));
        ConnectionDataBase.closeDataBase();
    }
}

class ConnectionDataBase {
    public static java.sql.Connection conn;
    public static Statement statement;
    public static ResultSet resSet;

    public static void connection() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:src\\data.db");
    }

    public static void createDataBase() throws SQLException {
        statement = conn.createStatement();
        statement.execute("CREATE TABLE if not exists 'data' (Depth STRING, Magnitude STRING, State STRING, Time String, UNIQUE (Depth, Magnitude, State, Time));");
    }

    public static void writeDataBase(String depth, String magnitude, String state, String time) throws SQLException {
        statement.execute(String.format("INSERT OR REPLACE INTO 'data'('Depth', 'Magnitude', 'State', 'Time') " +
                "VALUES ('%s', '%2s', '%3s', '%4s');", depth, magnitude, state, time));
    }

    public static void closeDataBase() throws SQLException {
        conn.close();
        statement.close();
    }
}
```

<br />

Теперь в таблице мы имеем все нужные нам данные из csv файла:

![image](https://user-images.githubusercontent.com/114663524/211085833-a31b4b74-64de-4d02-b7c4-9efa826f8946.png)

<br />

> Файл ***DataBase.java***

Чтобы работать с этими данными нужно их забрать из базы данных, для этого создаем метод `readDataBase`:

```Java
public static List<String> readDataBase() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:src\\data.db");
        statement = conn.createStatement();
        resSet = statement.executeQuery("SELECT * FROM data");

        List<String> dataFromDataBase = new ArrayList<>();

        while (resSet.next()) {
            dataFromDataBase.add(resSet.getString("Depth"));
            dataFromDataBase.add(resSet.getString("Magnitude"));
            dataFromDataBase.add(resSet.getString("State"));
            dataFromDataBase.add(resSet.getString("Time"));
        }

        conn.close();
        resSet.close();

        return dataFromDataBase;
    }
```

<br /> 

> Файл ***Main.java***

Принимаем эти данные:

```Java
public static void main(String[] args) throws SQLException, ClassNotFoundException {
        var elements = parsingCsvFile();
        purificationAndSendToDatabase(elements);
        var dataFromDataBase = ConnectionDataBase.readDataBase();
        System.out.println(dataFromDataBase);
    }
```

Выводим на экран:

![image](https://user-images.githubusercontent.com/114663524/211089027-58869f2c-128e-46f9-b47b-4f041e8be2dc.png)
___

<a name="4"></a>

### Выполнение 2 задачи

У нас есть необходимая информация, теперь мы можем выполнить задачу (Выведите в консоль среднюю магнитуду для штата "West Virginia")

<br /> 

> Файл ***Main.java***

Для этого мы заранее создали метод `gettingAverageMagnitude`, пропишем его:

```Java
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
```

Выводим данные на экран:

```Java
public static void main(String[] args) throws SQLException, ClassNotFoundException {
        var elements = parsingCsvFile();
        purificationAndSendToDatabase(elements);
        var dataFromDataBase = ConnectionDataBase.readDataBase();
        var averageMagnitude = gettingAverageMagnitude(dataFromDataBase, "West Virginia");
        System.out.println("Средняя магнитуда для штата West Virginia: " + averageMagnitude);
    }
```

Получаем:

![image](https://user-images.githubusercontent.com/114663524/211092703-dc45f5ae-0ee8-41c5-ba13-2722d2cc882c.png)
___

<a name="5"></a>

### Выполнение 3 задачи

У нас есть необходимая информация, теперь мы можем выполнить задачу (Выведите в консоль название штата, в котором произошло самое глубокое землетрясение в 2013 году)

<br /> 

> Файл ***Main.java***

Для этого мы заранее создали метод `gettingTheDeepestEarthquake`, пропишем его:

```Java
private static String gettingTheDeepestEarthquake(List<String> dataFromDataBase) {
        List<Integer> depth = new ArrayList<>();
        for (int i = 0; i < dataFromDataBase.size(); i += 4) {
            if (Objects.equals(dataFromDataBase.get(i + 3).substring(0, 4), "2013")) {
                depth.add(Integer.parseInt(dataFromDataBase.get(i)));
            }
        }
        return dataFromDataBase.get(dataFromDataBase.indexOf(Integer.toString(Collections.max(depth))) + 2);
    }
```

Выводим данные на экран:

```Java
public static void main(String[] args) throws SQLException, ClassNotFoundException {
        var elements = parsingCsvFile();
        purificationAndSendToDatabase(elements);
        var dataFromDataBase = ConnectionDataBase.readDataBase();
        var averageMagnitude = gettingAverageMagnitude(dataFromDataBase, "West Virginia");
        var stateTheDeepestEarthquake = gettingTheDeepestEarthquake(dataFromDataBase);
        System.out.println("Средняя магнитуда для штата West Virginia: " + averageMagnitude);
        System.out.println("Название штата, в котором произошло самое глубокое землятресение: " + stateTheDeepestEarthquake);
    }
```

Получаем:

![image](https://user-images.githubusercontent.com/114663524/211097474-006f2c18-b769-4cfc-a843-a67566cf440c.png)
___

<a name="6"></a>

### Выполнение 1 задачи

Для выполнения задачи (Постройте график по среднему количеству землетрясений для каждого года) нужно построить график, для этого мы заранее создали файл `Graph` в котором и будем его строить

> для построение графика нужно скачать и установить библиотеку Jfreechart

<br /> 

> Файл ***Graph.java***

Создаём тестовый график с 4 столбцами и тестовыми данными:

```Java
public class Graph extends JFrame {

    public Graph() {
        initUI();
    }

    private void initUI() {

        CategoryDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Тестовый график");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset() {

        var dataset = new DefaultCategoryDataset();

        dataset.setValue(1, "Test1", "1");
        dataset.setValue(2, "Test2", "2");
        dataset.setValue(3, "Test3", "3");
        dataset.setValue(4, "Test4", "4");

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        return ChartFactory.createBarChart(
                "",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    public static void main() {

        EventQueue.invokeLater(() -> {

            var ex = new Graph();
            ex.setVisible(true);
        });
    }
}
```

Вызываем этот класс:

```Java
public static void main(String[] args) throws SQLException, ClassNotFoundException {
        var elements = parsingCsvFile();
        purificationAndSendToDatabase(elements);
        var dataFromDataBase = ConnectionDataBase.readDataBase();
        var averageMagnitude = gettingAverageMagnitude(dataFromDataBase, "West Virginia");
        var stateTheDeepestEarthquake = gettingTheDeepestEarthquake(dataFromDataBase);
        System.out.println("Средняя магнитуда для штата West Virginia: " + averageMagnitude);
        System.out.println("Название штата, в котором произошло самое глубокое землятресение: " + stateTheDeepestEarthquake);
        Graph.main();
    }
```

Получаем:

![image](https://user-images.githubusercontent.com/114663524/211103083-a1c150c1-df51-400e-98c8-96ef4aef66c2.png)

<br /> 

> Файл ***Main.java***

Для того, чтобы высчитать среднее количество землятресений для каждого года нам нужно: год, список штатов (где были землетрясения), время каждого землетрясения

Нужно высчитать сколько раз были землетрясения в каждом штате, сложить количество землетрясений и разделить на количество штатов

Для удобства сначала нужно отсортировать данные из базы данных в методе `sortingDataFromDataBase`

```Java
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
```

Выведем на экран:

```Java
public static void main(String[] args) throws SQLException, ClassNotFoundException {
        var elements = parsingCsvFile();
        purificationAndSendToDatabase(elements);
        var dataFromDataBase = ConnectionDataBase.readDataBase();
        var averageMagnitude = gettingAverageMagnitude(dataFromDataBase, "West Virginia");
        var stateTheDeepestEarthquake = gettingTheDeepestEarthquake(dataFromDataBase);
        var sortedDataFromDataBase = sortingDataFromDataBase(dataFromDataBase);
        System.out.println(sortedDataFromDataBase);
        System.out.println("Средняя магнитуда для штата West Virginia: " + averageMagnitude);
        System.out.println("Название штата, в котором произошло самое глубокое землятресение: " + stateTheDeepestEarthquake);
    }
```

Получим:

![image](https://user-images.githubusercontent.com/114663524/211107176-98240613-4cdd-4cd2-b435-8f4dadcd1936.png)

Теперь необходимо расположить все данные по возрастнию годов и отправить в файл ***Graph.java*** для построение графика, всё это мы делаем в методе `sendToMakeGraph`:

```Java
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
```

<br /> 

> Файл ***Graph.java***

Обрабатываем полученные данные и создаём график:

```Java
public class Graph extends JFrame {

    public Graph(List<String> infoFromDB) {
        initUI(infoFromDB);
    }

    private void initUI(List<String> infoFromDB) {

        CategoryDataset dataset = createDataset(infoFromDB);

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Среднее количество землятресений");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset(List<String> informationForGraph) {

        var dataset = new DefaultCategoryDataset();

        for (String s : informationForGraph) {
            var year = s.split("&")[1];
            var averageValue = s.split("&")[0];
            dataset.setValue(Double.parseDouble(averageValue), year, "Годы");
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        return ChartFactory.createBarChart(
                "",
                "",
                "Среднее количество землятресений",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    public static void main(List<String> informationForGraph) {

        EventQueue.invokeLater(() -> {

            var ex = new Graph(informationForGraph);
            ex.setVisible(true);
        });
    }
}
```

<br /> 

> Файл ***Main.java***

Выводим:

```Java
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
```

Получаем график:

![image](https://user-images.githubusercontent.com/114663524/211108451-d37f7170-f777-4ef8-839a-cef5de555184.png)
___
