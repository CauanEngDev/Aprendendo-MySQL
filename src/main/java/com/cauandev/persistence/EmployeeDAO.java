package com.cauandev.persistence;

import com.cauandev.persistence.entity.EmployeeEntity;
import com.mysql.cj.jdbc.StatementImpl;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

            // System.out.printf("Foram afetados %s registros na base de dados.", statement.getUpdateCount());

            if (statement instanceof StatementImpl impl)
                employeeEntity.setId(impl.getLastInsertID());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(final EmployeeEntity employeeEntity) {

    }

    public void delete(long id) {

    }

    public List<EmployeeEntity> findAll(){
        return null;
    }

    public EmployeeEntity findById() {
        return null;
    }

    private String formatOffsetDateTime(final OffsetDateTime dateTime) {
        var utcDateTime = dateTime.withOffsetSameInstant(ZoneOffset.UTC);
        return utcDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
