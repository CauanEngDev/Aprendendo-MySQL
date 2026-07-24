package com.cauandev;

import com.cauandev.persistence.*;
import com.cauandev.persistence.entity.ContactEntity;
import com.cauandev.persistence.entity.EmployeeEntity;
import net.datafaker.Faker;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Stream;

import static java.time.ZoneOffset.UTC;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
    private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();
    private static final ContactDAO contactDAO = new ContactDAO();
    private final static Faker faker = new Faker(Locale.of("pt", "BR"));

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

        /*var insert = new EmployeeEntity();
        insert.setName("Lucas");
        insert.setSalary(new BigDecimal(3500));
        insert.setBirthday(OffsetDateTime.now().minusYears(20));
        System.out.println(insert);
        employeeDAO.insertWIthProcedure(insert);
        System.out.println(insert);*/

        /*var employee = new EmployeeEntity();
        employee.setName("Taylon");
        employee.setSalary(new BigDecimal(4000));
        employee.setBirthday(OffsetDateTime.now().minusYears(22));

        var employee1 = new EmployeeEntity();
        employee1.setName("Yasmim");
        employee1.setSalary(new BigDecimal(1439));
        employee1.setBirthday(OffsetDateTime.now().minusYears(21));

        employeeDAO.insert(employee);
        employeeDAO.insert(employee1);

        employeeDAO.findAll().forEach(System.out::println);*/

        /*var employeeUpd = new EmployeeEntity();
        employeeUpd.setId(insert.getId());
        employeeUpd.setName("Lucario");
        employeeUpd.setSalary(new BigDecimal(2000));
        employeeUpd.setBirthday(OffsetDateTime.now().minusYears(18));

        employeeDAO.update(employeeUpd);

        employeeDAO.delete(insert.getId());

        employeeAuditDAO.findAll().forEach(System.out::println);*/

        /*var entities = Stream.generate(() -> {
                    var employee = new EmployeeEntity();
                    employee.setName(faker.name().fullName());
                    employee.setSalary(new BigDecimal(faker.number().digits(4)));
                    employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40, 20)), LocalTime.MIN ,UTC));
                    return employee;
            }
        ).limit(10000).toList();

        employeeDAO.insertBatch(entities);*/

        /*var employee = new EmployeeEntity();
        employee.setName("João Victor");
        employee.setSalary(new BigDecimal(3500));
        employee.setBirthday(OffsetDateTime.now().minusYears(20));
        employeeDAO.insert(employee);

        var contact = new ContactEntity();
        contact.setDescription("joao.victor2026@gmail.com");
        contact.setType("e-mail");
        contact.setEmployee(employee);

        contactDAO.insert(contact);*/

        /*System.out.println(employeeDAO.findById(30271));*/

        /*var employee = new EmployeeEntity();
        employee.setName("Kauan");
        employee.setSalary(new BigDecimal(1600));
        employee.setBirthday(OffsetDateTime.now().minusYears(21));
        employeeDAO.insert(employee);*/

        /*var contact = new ContactEntity();
        contact.setDescription("mikael.boxe2026@gmail.com");
        contact.setType("e-mail");
        contact.setEmployee(employee);

        contactDAO.insert(contact);

        var contact2 = new ContactEntity();
        contact2.setDescription("75982829191");
        contact2.setType("telefone");
        contact2.setEmployee(employee);

        contactDAO.insert(contact2);

        System.out.println(employeeDAO.findById(employee.getId()));*/

        employeeDAO.findAll().forEach(System.out::println);
    }
}
