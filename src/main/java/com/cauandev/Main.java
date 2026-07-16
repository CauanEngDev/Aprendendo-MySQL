package com.cauandev;

import com.cauandev.persistence.ConnectionUtil;
import com.cauandev.persistence.EmployeeDAO;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    static void main() {
        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost/TesteMySQL", "dio_user", "dio1234")
                .load();

        flyway.migrate();
    }
}
