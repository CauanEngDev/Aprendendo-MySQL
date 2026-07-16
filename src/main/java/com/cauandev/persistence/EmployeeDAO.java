package com.cauandev.persistence;

import com.cauandev.persistence.entity.EmployeeEntity;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDAO {
    public void insert(final EmployeeEntity employeeEntity) {
        try() {

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
}
