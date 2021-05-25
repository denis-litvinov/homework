package epam.practical8.db;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import epam.practical8.db.entity.Team;
import epam.practical8.db.entity.User;


public class DBManager {

    private static DBManager dbManager;
    private static String path = "src\\main\\resources\\practical8\\app.properties";
    private static String url = property();


    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public static String property() {
        String s = "";
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(path))) {
            props.load(in);
            s = props.getProperty("connection.url");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return s;
    }


    public Connection getConnection(String connectionUrl) throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }


    public void insertUser(User user) {
        try (Connection conn = getConnection(url)) {
            String insert = "INSERT INTO users (login) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);) {
                stmt.setString(1, user.getLogin());
                stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys();) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                        System.out.println("users " + "  ==> " + "[" + user + "]");
                        System.out.println("id " + user.getId());
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection con = getConnection(url)) {
            try (Statement statement = con.createStatement()) {
                String insert = "SELECT * FROM users;";
                try (ResultSet resultSet = statement.executeQuery(insert)) {
                    while (resultSet.next()) {
                        User user = new User(resultSet.getString(2));
                        user.setId(resultSet.getInt("id"));
                        list.add(user);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void insertTeam(Team team) {
        try (Connection conn = getConnection(url)) {
            String insert = "INSERT INTO teams (name) VALUES (?)";
            try (PreparedStatement statement = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);) {
                statement.setString(1, team.getName());
                statement.executeUpdate();
                try (ResultSet resultSets = statement.getGeneratedKeys();) {
                    if (resultSets.next()) {
                        team.setId(resultSets.getInt(1));
                        System.out.println("teams " + "  ==> " + "[" + team + "]");
                        System.out.println("id " + team.getId());
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Team> findAllTeams() {
        List<Team> list = new ArrayList<>();
        try (Connection connection = getConnection(url)) {
            try (Statement statement = connection.createStatement()) {
                String insertion = "SELECT * FROM teams;";
                try (ResultSet rs = statement.executeQuery(insertion)) {
                    while (rs.next()) {
                        Team team = new Team(rs.getString("name"));
                        team.setId(rs.getInt("id"));
                        list.add(team);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public User getUser(String login) {
        User user = new User();
        try (Connection connection = getConnection(url)) {
            try (Statement statement = connection.createStatement()) {
                String insertion = "SELECT * FROM users WHERE" + " " + "login" + "=" + "'" + login + "'" + ";";
                try (ResultSet resultSet = statement.executeQuery(insertion)) {
                    if (resultSet.next()) {
                        user.setId(resultSet.getInt("id"));
                        user.setLogin(resultSet.getString("login"));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public Team getTeam(String name) {
        Team team = new Team();
        try (Connection con = getConnection(url)) {
            try (Statement statement = con.createStatement()) {
                String insertion = "SELECT * FROM teams WHERE" + " " + "name" + "=" + "'" + name + "'" + ";";
                try (ResultSet rs = statement.executeQuery(insertion)) {
                    if (rs.next()) {
                        team.setId(rs.getInt("id"));
                        team.setName(rs.getString("name"));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return team;
    }

    public void setTeamsForUser(User user, Team... teams) {
        Connection connection = null;
        try {
            connection = getConnection(url);
            String insertion = "INSERT INTO users_teams (user_id, team_id) VALUES (?, ?)";
            connection.setAutoCommit(false);
            try(PreparedStatement statement = connection.prepareStatement(insertion)) {
                for (Team team : teams) {
                    statement.setInt(1, user.getId());
                    statement.setInt(2, team.getId());
                    statement.addBatch();
                }
                statement.executeBatch();
                connection.commit();
            }
        } catch (SQLException throwables) {
            try {
                if (connection == null){
                    throw new NullPointerException();
                }
                connection.rollback();
                System.out.println("РОЛБЕКНУЛОСЬ УСПЕШНО");
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }

    }

    public List<Team> getUserTeams(User user) {
        List<Team> teams = new ArrayList<>();
        try (Connection connection = getConnection(url)) {
            try (Statement statement = connection.createStatement()) {
                String insert = "SELECT id,name FROM users_teams LEFT JOIN teams" +
                        " ON team_id = id" + " WHERE users_teams.user_id = " + user.getId() + ";";
                try (ResultSet resultSet = statement.executeQuery(insert)) {
                    while (resultSet.next()) {
                        Team team = Team.createTeam(resultSet.getString("name"));
                        team.setId(resultSet.getInt("id"));
                        teams.add(team);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teams;
    }


    public void deleteTeam(Team team) {
        try (Connection connection = getConnection(url)) {
            String insert = "DELETE FROM teams WHERE id = (?)";
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setInt(1, team.getId());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTeam(Team team) {
        try (Connection connection = getConnection(url)) {
            String insert = "UPDATE teams SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, team.getName());
                statement.setInt(2, team.getId());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
