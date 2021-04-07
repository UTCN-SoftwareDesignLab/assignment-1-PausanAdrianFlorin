package repository.user;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.User;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import java.sql.SQLException;
import java.util.Collections;
import static database.Constants.Roles.EMPLOYEE;

public class UserRepositoryMySQLTest {

    private static UserRepository repository;
    private static RightsRolesRepository rightsRolesRepository;

    @BeforeClass
    public static void setupClass(){
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
        rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());
        repository = new UserRepositoryMySQL(connectionWrapper.getConnection(),rightsRolesRepository);
    }

    @Before
    public void setup() {
        repository.removeAll();

        User user = new UserBuilder().setUsername("test1@test1.com").setPassword("testtest11!").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(EMPLOYEE))).build();
        User user1 = new UserBuilder().setUsername("test2@test2.com").setPassword("testtest22!").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(EMPLOYEE))).build();

        repository.save(user);
        repository.save(user1);
    }

    @Test
    public void findByUsernameAndPassword() {
        Assert.assertEquals("test1@test1.com",repository.findByUsernameAndPassword("test1@test1.com","testtest11!").getResult().getUsername());
    }

    @Test
    public void save() {
        User user = new UserBuilder().setUsername("test3@test3.com").setPassword("testtest33!").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(EMPLOYEE))).build();
        Assert.assertTrue(repository.save(user));
        Assert.assertEquals(3,repository.findAll().size());
    }

    @Test
    public void remove() {
        User user = new UserBuilder().setUsername("test1@test1.com").build();
        repository.remove(user);
        Assert.assertEquals(1,repository.findAll().size());
    }

    @Test
    public void update() throws SQLException {
        User user = repository.findByUsername("test1@test1.com");
        user.setPassword("testtest1111!");
        repository.update(user);
        Assert.assertEquals("testtest1111!",repository.findByUsername("test1@test1.com").getPassword());
    }

    @Test
    public void findAll() {
        User user = new UserBuilder().setUsername("test4@test4.com").setPassword("testtest44!").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(EMPLOYEE))).build();
        repository.save(user);
        Assert.assertEquals(3,repository.findAll().size());
    }

    @Test
    public void findByUsername() throws SQLException {
        Assert.assertEquals("test1@test1.com", repository.findByUsername("test1@test1.com").getUsername());
    }

    @Test
    public void removeAll() {
        repository.removeAll();
        Assert.assertTrue(repository.findAll().isEmpty());
    }
}