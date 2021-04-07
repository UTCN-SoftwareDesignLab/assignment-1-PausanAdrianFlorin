package repository.activity;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.Activity;
import model.User;
import model.builder.ActivityBuilder;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import static database.Constants.Roles.EMPLOYEE;

public class ActivityRepositoryMySQLTest {

    private static ActivityRepository repository;
    private static UserRepository userRepository;
    private static RightsRolesRepository rightsRolesRepository;

    @BeforeClass
    public static void setupClass(){
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
        rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());
        userRepository = new UserRepositoryMySQL(connectionWrapper.getConnection(),rightsRolesRepository);
        repository = new ActivityRepositoryMySQL(connectionWrapper.getConnection(),userRepository);
    }

    @Before
    public void setup() {
        userRepository.removeAll();
        repository.removeAll();

        User user = new UserBuilder().setUsername("dorel").setPassword("parola_dorel").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(EMPLOYEE))).build();
        User user2 = new UserBuilder().setUsername("maricica").setPassword("parola_maricica").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(EMPLOYEE))).build();

        userRepository.save(user);
        userRepository.save(user2);

        Activity activity = new ActivityBuilder().setIdEmployee(1L).setDescription("test_activity_1").setDate(Date.valueOf(LocalDate.now())).build();
        Activity activity2 = new ActivityBuilder().setIdEmployee(1L).setDescription("test_activity_2").setDate(Date.valueOf(LocalDate.now())).build();

        repository.addActivity(activity);
        repository.addActivity(activity2);
    }

    @Test
    public void findActivityOfEmployee() throws SQLException {
        Assert.assertEquals(2,repository.findActivityOfEmployee("dorel").size());
    }

    @Test
    public void findActivityOfEmployeeBetween() throws SQLException {
        LocalDate date = LocalDate.of(2021,3,19);
        LocalDate date1 = LocalDate.of(2021,3,25);

        Assert.assertEquals(2,repository.findActivityOfEmployeeBetween("dorel",date,date1).size());

        LocalDate date2 = LocalDate.of(2020,3,19);
        LocalDate date3 = LocalDate.of(2020,3,25);

        Assert.assertEquals(0,repository.findActivityOfEmployeeBetween("dorel",date2,date).size());
    }

    @Test
    public void addActivity() throws SQLException {
        Activity activity = new ActivityBuilder().setIdEmployee(2L).setDescription("test_activity_3")
                .setDate(Date.valueOf(LocalDate.now())).build();

        repository.addActivity(activity);

        Assert.assertEquals(1,repository.findActivityOfEmployee("maricica").size());
    }

}