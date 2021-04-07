package repository.client;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import java.sql.SQLException;
import java.util.List;

public class ClientRepositoryMySQLTest {

    private static ClientRepository repository;
    private static AccountRepository accountRepository;

    @BeforeClass
    public static void setupClass(){
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
        repository = new ClientRepositoryMySQL(connectionWrapper.getConnection(),accountRepository);
    }

    @Before
    public void setup() {
        repository.removeAll();
    }

    @Test
    public void findAll() {
        Client client = new ClientBuilder().setName("Jean de la Craiova").setIdNumber("101238457").setCardNumber("14825819556").setAddress("Aleea Barosanilor").setPhone("0721393057").build();
        Client client2 = new ClientBuilder().setName("Florin Salam").setIdNumber("101258457").setCardNumber("14821819556").setAddress("Strada Bombardierilor").setPhone("0721393051").build();

        repository.saveClient(client);
        repository.saveClient(client2);

        List<Client> clients = repository.findAll();
        Assert.assertTrue(clients.size()==2);
    }

    @Test
    public void findById() throws EntityNotFoundException {
        Client client = new ClientBuilder().setName("Jean de la Craiova").setIdNumber("101238457").setCardNumber("14825819556").setAddress("Aleea Barosanilor").setPhone("0721393057").build();
        Client client2 = new ClientBuilder().setName("Florin Salam").setIdNumber("101258457").setCardNumber("14821819556").setAddress("Strada Bombardierilor").setPhone("0721393051").build();

        repository.saveClient(client);
        repository.saveClient(client2);

        Assert.assertTrue(repository.findById(2L).getName().equals("Florin Salam"));
    }

    @Test
    public void findByName() throws SQLException {
        Client client = new ClientBuilder().setName("Jean de la Craiova").setIdNumber("101238457").setCardNumber("14825819556").setAddress("Aleea Barosanilor").setPhone("0721393057").build();
        Client client2 = new ClientBuilder().setName("Florin Salam").setIdNumber("101258457").setCardNumber("14821819556").setAddress("Strada Bombardierilor").setPhone("0721393051").build();

        repository.saveClient(client);
        repository.saveClient(client2);

        Assert.assertTrue(repository.findByName("Jean de la Craiova").getAddress().equals("Aleea Barosanilor"));
    }

    @Test
    public void saveClient() {
        Client client = new ClientBuilder().setName("Sandu Ciorba").setIdNumber("101258454").setCardNumber("14821819554").setAddress("Drumul Taberei").setPhone("0721393058").build();
        Assert.assertTrue(repository.saveClient(client));
    }

    @Test
    public void removeAll(){
        repository.removeAll();
        List<Client> clients = repository.findAll();
        Assert.assertTrue(clients.isEmpty());
    }

    @Test
    public void update() {
    }
}