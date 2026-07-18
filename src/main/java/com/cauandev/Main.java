package com.cauandev;

import com.cauandev.persistence.ConnectionUtil;
import com.cauandev.persistence.EmployeeDAO;
import com.cauandev.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Properties;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private final static EmployeeDAO employeeDAO = new EmployeeDAO();

    static void main() {
        var props = ConnectionUtil.getProps();

        var flyway = Flyway.configure()
                .dataSource(
                        props.getProperty("db.url"),
                        props.getProperty("db.user"),
                        props.getProperty("db.password")
                )
                .load();

        flyway.migrate();

        var employee = new EmployeeEntity();
        employee.setName("Lucas");
        employee.setSalary(new BigDecimal(3500));
        employee.setBirthday(OffsetDateTime.now().minusYears(20));
        System.out.println(employee);
        employeeDAO.insert(employee);
        System.out.println(employee);
    }
}
