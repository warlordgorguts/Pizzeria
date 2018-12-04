package pizzabot;

import java.sql.*;

public class JDBCReader {

    public void Select(){
        try (
                // Step 1: Allocate a database 'Connection' object

                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/pizzeria_db?allowMultiQueries=true", "root", "1234");
                // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
        ) {
            // Step 3: Execute a SQL SELECT query, the query result
            //  is returned in a 'ResultSet' object.
            String multiQuerySqlString = "select name, price from pizzas;select name, price from drinks;";
            //String drinkSelect = "select name, price from drinks;";
            //System.out.println("The SQL query is: " + strSelect); // Echo For debugging
            //System.out.println();

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

            //ResultSet pset = stmt.executeQuery(pizzaSelect);
            //ResultSet dset = stmt.executeQuery(drinkSelect);

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            /*
            System.out.println("The records selected are:");
            int prowCount = 0;
            while(pset.next()) {   // Move the cursor to the next row, return false if no more row
                String name = pset.getString("name");
                double price = pset.getDouble("price");
                System.out.println(name + ", " + price);
                ++prowCount;
            }
            System.out.println("Total number of records = " + prowCount);
            */
           // int drowCount = 0;
           // while(dset.next()) {   // Move the cursor to the next row, return false if no more row
            //    String dname = dset.getString("name");
            //    double dprice = dset.getDouble("price");
            //    System.out.println(dname + ", " + dprice);
           //     ++drowCount;
           // }
           // System.out.println("Total number of records = " + drowCount);

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        // Step 5: Close the resources - Done automatically by try-with-resources
    }
}

