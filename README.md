# Итоговый проект 
___
### Студент: Крылов Никита Андреевич РИ-210945 (AT-01)
### Вариант: 5
___

### Создание структуры проекта и основных методов
После прочтения задания мы можем создать структуру проекта:

![image](https://user-images.githubusercontent.com/114663524/210892187-6446c488-da09-4980-9f5e-53c50778ce00.png)

Файл ***Main*** является основным, в нем мы будем писать основную логику программы и будем взаимодействовать с другими файлами в нашем проекте

Файл ***DataBase*** будет содержать в себе необходимые методы для взаимодействия с базой данных

Файл ***Graph*** будет содержать в себе необходимые методы для построения графика

<br />

>Файл ***Main***

создадим нужные нам методы:
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
Метод `main` будет вызывать все остальные методы 

Метод `parsingCsvFile` будет парсить файл, который находится в задании

Метод `purificationAndSendToDatabase` будет получать данные из метода `parsingCsvFile` и отправлять их в файл ***DataBase*** для добавления в базу данных 

Метод `gettingAverageMagnitude` будет получать данные из базы данных и выполнять 2 задачу (Выведите в консоль среднюю магнитуду для штата "West Virginia")

Метод `gettingTheDeepestEarthquake` будет брать данные из базы данных и выполнять 3 задачу (Выведите в консоль название штата, в котором произошло самое глубокое землетрясение в 2013 году)

Метод `sortingDataFromDataBase` будет брать данные из базы данных и сортировать их для последующей обработи в методе `sendToMakeGraph`

Метод `sendToMakeGraph` обрабатывает данные из метода `sortingDataFromDataBase` и отправляет данные в файл ***Graph*** для выполнения 1 задачи (Постройте график по среднему количеству землетрясений для каждого года)
____

### Парсинг данных 

Добавим в наш проект файл ***Землетрясения.csv*** из задания

![image](https://user-images.githubusercontent.com/114663524/210904699-a0b066f1-af4a-4c15-9081-80469fb5a42c.png)

> Файл ***Main***

Пропишем метод `parsingCsvFile`:

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

### Создание базы данных (SQL Lite) и подключение к проекту

> Файл ***DataBase***

создадим класс `ConnectionDataBase` который будет присоединяться и работать с базой данных (SQL Lite):

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

    public static void closeDB() throws SQLException {
        conn.close();
        statement.close();
    }
}
```

Метод `connection` будет подключаться к базе данных 

Метод `createDataBase` будет создавать таблицу 

Метод `writeDataBase` будет заполнять таблицу нужными данными 

Метод `closeDB` будет закрывать соединение с базой данных

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

    public static void closeDB() throws SQLException {
        conn.close();
        statement.close();
    }
}

```

> Файл ***DataBase***

пропишем вызовы ранее написанных методов:

```Java
public class DataBase {

    public static void main() throws ClassNotFoundException, SQLException {
        ConnectionDataBase.connection();
        ConnectionDataBase.createDataBase();
        ConnectionDataBase.writeDataBase();
        ConnectionDataBase.closeDB();
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

    public static void closeDB() throws SQLException {
        conn.close();
        statement.close();
    }
}
```
<br /> 

> Файл ***Main*** 

вызовем метод `main` класса `DataBase`:

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

Теперь нам нужно передать данные из csv в базу данных

Сначала определимся с тем, какие данные нам нужны по заданию (Глубина в метрах, Магнитуда, Штат, Время)

> Файл ***DataBase***

Создадим 4 колонки 

```Java
public static void createDataBase() throws SQLException {
        statement = conn.createStatement();
        statement.execute("CREATE TABLE if not exists 'data' (Depth STRING, Magnitude STRING, State STRING, Time String, UNIQUE (Depth, Magnitude, State, Time));");
    }
```

Теперь наша задача передать распарсенные данные в файл ***DataBase*** 




















