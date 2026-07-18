package com.cauandev.persistence;

import com.cauandev.persistence.entity.EmployeeEntity;
import com.mysql.cj.jdbc.StatementImpl;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {
    public void insert(final EmployeeEntity employeeEntity) {
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {
            var sql = "INSERT INTO employees (name, salary, birthday) values ('" +
                    employeeEntity.getName() + "', " +
                    employeeEntity.getSalary() + ", '" +
                    formatOffsetDateTime(employeeEntity.getBirthday()) + "' )";
            statement.executeUpdate(sql);

            if (statement instanceof StatementImpl impl)
                employeeEntity.setId(impl.getLastInsertID());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(final EmployeeEntity employeeEntity) {
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {
            var sql = "UPDATE employees set " +
                    "name     /= '" + employeeEntity.getName() + "', " +
                    "salary   =  " + employeeEntity.getSalary() + ", " +
                    "birthday = '" + formatOffsetDateTime(employeeEntity.getBirthday()) + "'" +
                    "WHERE id = " + employeeEntity.getId();
            statement.executeUpdate(sql);

            if (statement instanceof StatementImpl impl)
                employeeEntity.setId(impl.getLastInsertID());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(long id) {

    }

    public List<EmployeeEntity> findAll(){
        List<EmployeeEntity> entities = new ArrayList<>();

        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {
            statement.executeQuery("SELECT * FROM employees ORDER BY name");
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                var entity = new EmployeeEntity();

                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));

                entities.add(entity);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return entities;
    }

    public EmployeeEntity findById(final long id) {
        var entity = new EmployeeEntity();

        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {
            statement.executeQuery("SELECT * FROM employees WHERE id = " + id);
            var resultSet = statement.getResultSet();
            if (resultSet.next()) {
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return entity;
    }

    private String formatOffsetDateTime(final OffsetDateTime dateTime) {
        var utcDateTime = dateTime.withOffsetSameInstant(UTC);
        return utcDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
