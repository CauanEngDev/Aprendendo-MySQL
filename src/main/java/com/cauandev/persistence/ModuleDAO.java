package com.cauandev.persistence;

import com.cauandev.persistence.entity.ContactEntity;
import com.cauandev.persistence.entity.EmployeeEntity;
import com.cauandev.persistence.entity.ModuleEntity;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class ModuleDAO {
    public List<ModuleEntity> findAll(){
        List<ModuleEntity> entities = new ArrayList<>();
        var sql = "SELECT m.id module_id, " +
                    "m.name module_name, " +
                    "e.id employee_id, " +
                    "e.name employee_name, " +
                    "e.salary employee_salary, " +
                    "e.birthday employee_birthday " +
                  "FROM modules m " +
                  "INNER JOIN accesses a " +
                    "on a.module_id = m.id " +
                  "INNER JOIN employees e " +
                    "on e.id = a.employee_id " +
                  "ORDER BY m.id";

        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            var hasNext = resultSet.next();

            while (hasNext) {
                ModuleEntity module = new ModuleEntity();
                module.setId(resultSet.getLong("module_id"));
                module.setName(resultSet.getString("module_name"));
                module.setEmployees(new ArrayList<>());

                do {
                    var employee = new EmployeeEntity();
                    employee.setId(resultSet.getLong("employee_id"));
                    employee.setName(resultSet.getString("employee_name"));
                    employee.setSalary(resultSet.getBigDecimal("employee_salary"));
                    var birthdayInstant = resultSet.getTimestamp("employee_birthday").toInstant();
                    employee.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                    module.getEmployees().add(employee);
                    hasNext = resultSet.next();
                } while ((hasNext) && (module.getId() == resultSet.getLong("module_id")));

                entities.add(module);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return entities;
    }
}
