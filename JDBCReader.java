package pizzabot;

import java.sql.*;

public class JDBCReader {

    public void Select(){
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/pizzeria_db?allowMultiQueries=true", "root", "1234");
                Statement stmt = conn.createStatement();
        ) {
            String multiQuerySqlString = "select name, price from pizzas;select name, price from drinks;";
            boolean hasMoreResultSets = stmt.execute( multiQuerySqlString);

            int queryNumber = 0;

            while ( hasMoreResultSets || stmt.getUpdateCount() != -1 ) {
                queryNumber ++;
                if ( hasMoreResultSets ) {
                    ResultSet rs = stmt.getResultSet();
                    int prowCount = 0;

                    while(rs.next()) {   // Move the cursor to the next row, return false if no more row
                        if (queryNumber == 1) {
                            String name = rs.getString("name");
                            double price = rs.getFloat("price");
                            System.out.println(name + ", " + price);
                            ++prowCount;
                        }else{
                            String name = rs.getString("name");
                            double price = rs.getFloat("price");
                            System.out.println(name + ", " + price);
                            ++prowCount;
                        }
                    }
                    System.out.println("Total number of records = " + prowCount);
                    // handle your rs here
                } // if has rs
                else { // if ddl/dml/...
                    int queryResult = stmt.getUpdateCount();
                    if ( queryResult == -1 ) { // no more queries processed
                        break;
                    } // no more queries processed
                    // handle success, failure, generated keys, etc here
                } // if ddl/dml/...

                // check to continue in the loop
                hasMoreResultSets = stmt.getMoreResults();
            } // while results

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        // Step 5: Close the resources - Done automatically by try-with-resources
    }

    public int SelectDiscount(String cardNumber){
        try (

                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/pizzeria_db", "root", "1234");
                Statement stmt = conn.createStatement();
        ) {
            String QuerySqlString = "select cardDiscount from discountcards where cardNumber = " + cardNumber + " ;";

            ResultSet cardResultSet = stmt.executeQuery(QuerySqlString);

            while(cardResultSet.next()) {
               return cardResultSet.getInt("cardDiscount");
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return 0;

    }
}

