import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TransactionDao {

    private static final String URL = "jdbc:mysql://localhost:3306/zadanie_25?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";
    private Connection connection;

    public TransactionDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Błąd wczytywnia sterownika. " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z powodu \n " + e.getMessage());
        }
    }


    public void save(Transaction transaction) {
        final String sql = "INSERT INTO zadanie_25.transaction (type, description, amount, date) VALUES (?,?,?,?);";
        try {
            PreparedStatement prepStet = connection.prepareStatement(sql);
            prepStet.setString(1, String.valueOf(transaction.getType()));
            prepStet.setString(2, transaction.getDescription());
            prepStet.setDouble(3, transaction.getAmount());
            prepStet.setString(4, transaction.getDate());
            prepStet.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można zapisać transakcji");
            e.printStackTrace();
        }
    }

    public void update(Transaction transaction) {
        final String sql = "UPDATE zadanie_25.transaction SET type=?, description=?, amount=?, date = ? WHERE id =?;";
        try {
            PreparedStatement prepStet = connection.prepareStatement(sql);
            prepStet.setString(1, String.valueOf(transaction.getType()));
            prepStet.setString(2, transaction.getDescription());
            prepStet.setDouble(3, transaction.getAmount());
            prepStet.setString(4, transaction.getDate());
            prepStet.setLong(5, transaction.getId());
            prepStet.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można zapisać transakcji");
            e.printStackTrace();
        }
    }

    public void deleteTrans(long id) {
        final String sql = "DELETE FROM zadanie_25.transaction WHERE id = ?";
        try {
            PreparedStatement prepStat = connection.prepareStatement(sql);
            prepStat.setLong(1, id);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można usunąć transakcji");
            e.printStackTrace();
        }
    }

    public List<Transaction> showList(TransactionType type) {
        List<Transaction> showList = new ArrayList<Transaction>();
        final String sql = "SELECT * FROM zadanie_25.transaction WHERE type='"+ type+"'";
        try {
            PreparedStatement prepStat = connection.prepareStatement(sql);
            ResultSet result = prepStat.executeQuery();
            while (result.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(result.getLong("id"));
                transaction.setDescription(result.getString("description"));
                transaction.setAmount(result.getDouble("amount"));
                transaction.setDate(result.getString("date"));
                showList.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Nie można wyświetlić transakcji");
            e.printStackTrace();
        }
        return showList;

    }
    //kolejne metody


    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
