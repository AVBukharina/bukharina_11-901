package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "select * from client_info";
    //language=SQL
    private static final String SQL_FIND_ALL_USERS_BY_AGE = "select * from client_info where age = ?";
    //language=SQL
    private static final String SQL_FIND_USERS = "select exists (select * from client where email = ? and password = ?)";

    private Connection connection;

    private SimpleJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcImpl(Connection connection){
        this.connection = connection;
        this.jdbcTemplate = new SimpleJdbcTemplate(connection);
    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("firstname"))
            .lastName(row.getString("lastname"))
            .age(row.getInt("age"))
            .build();


    @Override
    public List<User> findAllByAge(Integer age) {
        /*try {
           PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS_BY_AGE);
           preparedStatement.setInt(1, age);

           List<User> users = new ArrayList<>();

           ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()){
               users.add(userRowMapper.mapRow(resultSet));
           }
           return users;
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }*/
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS, userRowMapper);
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    public boolean pageWithCookie(String email, String password){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USERS);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("exists");
            } else return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
