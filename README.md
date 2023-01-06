# Итоговый проект 
___
### Студент: Крылов Никита Андреевич РИ-210945 (AT-01)
### Вариант: 5
___

### Создание структуры проекта и основных методов
После прочтения задания мы можем создать структуру проекта:

![image](https://user-images.githubusercontent.com/114663524/210892187-6446c488-da09-4980-9f5e-53c50778ce00.png)

Файл `Main` является основным, в нем мы будем писать основную логику программы и будем взаимодействовать с другими файлами в нашем проекте

Файл `DataBase` будет содержать в себе необходимые методы для взаимодействия с базой данных

Файл `Graph` будет содержать в себе необходимые методы для построения графика

<br />

Перейдем в файл `Main` и создадим нужные нам методы:
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

Метод `purificationAndSendToDatabase` будет получать данные из метода `parsingCsvFile` и отправлять их в файл `DataBase` для добавления в базу данных 

Метод `gettingAverageMagnitude` будет получать данные из базы данных и выполнять 2 задачу (Выведите в консоль среднюю магнитуду для штата "West Virginia")

Метод `gettingTheDeepestEarthquake` будет брать данные из базы данных и выполнять 3 задачу (Выведите в консоль название штата, в котором произошло самое глубокое землетрясение в 2013 году)

Метод `sortingDataFromDataBase` будет брать данные из базы данных и сортировать их для последующей обработи в методе `sendToMakeGraph`

Метод `sendToMakeGraph` обрабатывает данные из метода `sortingDataFromDataBase` и отправляет данные в файл `Graph` для выполнения 1 задачи (Постройте график по среднему количеству землетрясений для каждого года)
____

### Парсинг данных 

Добавим в наш проект файл `Землетрясения.cvs` из задания

![image](https://user-images.githubusercontent.com/114663524/210904699-a0b066f1-af4a-4c15-9081-80469fb5a42c.png)

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













