import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> readDataBase() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:src\\data.db");
        statement = conn.createStatement();
        resSet = statement.executeQuery("SELECT * FROM data");

        List<String> dataFromDataBase = new ArrayList<>();

        while(resSet.next())
        {
            dataFromDataBase.add(resSet.getString("Depth"));
            dataFromDataBase.add( resSet.getString("Magnitude"));
            dataFromDataBase.add(resSet.getString("State"));
            dataFromDataBase.add(resSet.getString("Time"));
        }

        conn.close();
        resSet.close();

        return dataFromDataBase;
    }


    public static void closeDataBase() throws SQLException {
        conn.close();
        statement.close();
    }
}
