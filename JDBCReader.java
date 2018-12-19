package pizzabot;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class JDBCReader {

    public void Select() {

        try (

                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/pizzeria_db?allowMultiQueries=true", "root", "1234");
                Statement stmt = conn.createStatement();
        ) {
            String multiQuerySqlString = "select name, price from pizzas;select name, price from drinks;";
            boolean hasMoreResultSets = stmt.execute(multiQuerySqlString);

            int queryNumber = 0;

            while (hasMoreResultSets || stmt.getUpdateCount() != -1) {
                queryNumber++;
                if (hasMoreResultSets) {
                    ResultSet rs = stmt.getResultSet();
                    int prowCount = 0;

                    while (rs.next()) {
                        if (queryNumber == 1) {
                            String name = rs.getString("name");
                            double price = rs.getFloat("price");
                            System.out.println(name + ", " + price);
                            ++prowCount;
                        } else {
                            String name = rs.getString("name");
                            double price = rs.getFloat("price");
                            System.out.println(name + ", " + price);
                            ++prowCount;
                        }
                    }
                    System.out.println("Total number of records = " + prowCount);
                } else {
                    int queryResult = stmt.getUpdateCount();
                    if (queryResult == -1) {
                        break;
                    }
                }
                hasMoreResultSets = stmt.getMoreResults();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int SelectDiscount(int cardNumber) {

        String QuerySqlString = "select * from discountcards where cardNumber = ?";
        int cardDiscount = 0;

        try (

                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/pizzeria_db?", "root", "1234");
                PreparedStatement stmt = conn.prepareStatement(QuerySqlString);
        ) {

            stmt.setInt(1, cardNumber);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cardDiscount = rs.getInt("carddiscount");
                return cardDiscount;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cardDiscount;

    }
}

