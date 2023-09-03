package com.example.dbservice.procedure;

import com.example.dbservice.dto.LoginRequest;
import com.example.dbservice.model.Employee;
import com.example.dbservice.model.ServicesMenu;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoginProcedure {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> validateLogin(LoginRequest loginRequest, String lang) {
        String statement = "{ ? = call XXMOB.XXX_MOBILE_LOGIN.VALIDATELOGIN(?, ? , ?, ?) }";
        Session session = entityManager.unwrap(Session.class);
        CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
            @Override
            public CallableStatement execute(Connection connection) throws SQLException {
                CallableStatement function = connection.prepareCall(statement);
                function.setString(2, loginRequest.getUserName());
                function.setString(3, loginRequest.getPassword());
                function.setString(4, lang);
                function.registerOutParameter(5, Types.NUMERIC);
                function.registerOutParameter(1, Types.VARCHAR);
                function.execute();
                return function;
            }
        });

        Map<String, Object> result = new HashMap<>();
        try {
            result.put("p_user_id", callableStatement.getInt(5));
            result.put("result", callableStatement.getString(1));
            int pUserId = (int) result.get("p_user_id");
            if (pUserId != 0) {
                Employee info = getInfo(lang, pUserId);
                result.put("employeeData", info);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void alterSession(String lang, Integer pUserId) {
        Session session = entityManager.unwrap(Session.class);
        CallableStatement callableStatement2 = session.doReturningWork(new ReturningWork<CallableStatement>() {
            @Override
            public CallableStatement execute(Connection connection) throws SQLException {
                CallableStatement function2 = connection.prepareCall("{  call XXMOB.XXX_MOBILE_LOGIN.alter_session(?, ?) }");
                function2.setString(1, lang);
                function2.setInt(2, pUserId);
                function2.execute();
                return function2;
            }
        });

    }

    public Employee getInfo(String lang, int id) {
        alterSession(lang, id);
        final Employee[] employee = {null};

        String sql = "SELECT PERSON_ID,DISP_NAME, D_TITLE, JOB_NAME, D_SEX, IMAGE FROM xxmob.xxx_mob_hr_emp_main_v WHERE person_id = APPS.FND_GLOBAL.EMPLOYEE_ID";
        Session session = entityManager.unwrap(Session.class);
        session.doReturningWork(new ReturningWork<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(sql);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        employee[0] = new Employee();
                        employee[0].setId(resultSet.getInt("PERSON_ID"));
                        employee[0].setDISP_NAME(resultSet.getString("DISP_NAME"));
                        employee[0].setD_TITLE(resultSet.getString("D_TITLE"));
                        employee[0].setJOB_NAME(resultSet.getString("JOB_NAME"));
                        employee[0].setD_SEX(resultSet.getString("D_SEX"));
                        employee[0].setIMAGE(resultSet.getBytes("IMAGE"));
                    }
                }
                return null;
            }
        });

        return employee[0];
    }


    public List<ServicesMenu> getServices(String lang, int id, String pParentId) {
        alterSession(lang, id);
        List<ServicesMenu> servicesMenuList = new ArrayList<>();

        String sql = "SELECT * FROM xxmob.XXX_MOB_MAIN_SERVICES_MENU";
        if (pParentId != null) {
            sql += " WHERE value = ?";
        }

        Session session = entityManager.unwrap(Session.class);
        String finalSql = sql;
        session.doReturningWork(new ReturningWork<Void>() {
            @Override
            public Void execute(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(finalSql);
                if (pParentId != null) {
                    statement.setString(1, pParentId);
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        ServicesMenu servicesMenu = new ServicesMenu();
                        servicesMenu.setSERV_ID(resultSet.getString("SERV_ID"));
                        servicesMenu.setICON(resultSet.getString("ICON"));
                        servicesMenu.setLABEL(resultSet.getString("LABEL"));
                        servicesMenu.setENABLED(resultSet.getString("ENABLED"));
                        servicesMenu.setTYPE(resultSet.getString("TYPE"));
                        servicesMenu.setSECURITY_FORMULA(resultSet.getString("SECURITY_FORMULA"));
                        servicesMenu.setSERV_COUNT(resultSet.getInt("SERV_COUNT"));
                        servicesMenuList.add(servicesMenu);
                    }
                }
                return null;
            }
        });

        return servicesMenuList;
    }
}


