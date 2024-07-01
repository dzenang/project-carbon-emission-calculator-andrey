package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import org.example.config.ConnectionFactory;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        if ( connection != null ) {
            System.out.println("DB connection established");
        }
    }
}
