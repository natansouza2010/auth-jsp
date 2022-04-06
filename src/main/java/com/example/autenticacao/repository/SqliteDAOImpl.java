package com.example.autenticacao.repository;

import com.example.autenticacao.dao.GenericDAO;
import com.example.autenticacao.model.User;
import com.example.autenticacao.model.util.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqliteDAOImpl implements GenericDAO<User, String> {
    @Override
    public boolean insert(User user) {
        String sql = "INSERT INTO USERS (USERNAME, NAME, PASSWORD) values (?,?,?)";
        ConnectionFactory factory = new ConnectionFactory();
        try(PreparedStatement stmt = factory.createPreparedStatement(sql)){
            stmt.setString(1, String.valueOf(user.getUsername()));
            stmt.setString(2, String.valueOf(user.getNome()));
            stmt.setString(3, String.valueOf(user.getSenha()));
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public User findOne(String username) {
        User user = null;
        String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
        ConnectionFactory factory = new ConnectionFactory();
        try(PreparedStatement ps = factory.createPreparedStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }



    public User findOneId(Integer id) {
        User user = null;
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        ConnectionFactory factory = new ConnectionFactory();
        try(PreparedStatement ps = factory.createPreparedStatement(sql)) {
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    private User resultSetToEntity(ResultSet rs) throws SQLException {
        return new User(
                Integer.valueOf(rs.getString("ID")),
                rs.getString("USERNAME"),
                rs.getString("NAME"),
                rs.getString("PASSWORD") );
    }

    @Override
    public List<User> listAll() {
        return null;
    }

    @Override
    public boolean update(User obj) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }


}
