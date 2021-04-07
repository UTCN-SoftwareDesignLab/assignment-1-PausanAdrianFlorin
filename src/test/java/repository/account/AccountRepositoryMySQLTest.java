package repository.account;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountRepositoryMySQLTest {
    private static AccountRepository repository;
    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass(){
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
        repository = new AccountRepositoryMySQL(connectionWrapper.getConnection());
        clientRepository = new ClientRepositoryMySQL(connectionWrapper.getConnection(),repository);
    }

    @Before
    public void setup() {
        clientRepository.removeAll();
        repository.removeAll();

        Client client = new ClientBuilder().setName("Jean de la Craiova").setIdNumber("101238457").setCardNumber("14825819556").setAddress("Aleea Barosanilor").setPhone("0721393057").build();
        Client client2 = new ClientBuilder().setName("Florin Salam").setIdNumber("101258457").setCardNumber("14821819556").setAddress("Strada Bombardierilor").setPhone("0721393051").build();

        clientRepository.saveClient(client);
        clientRepository.saveClient(client2);

        Account account = new AccountBuilder().setIdClient(1L).setIban("RO00RZBR1234123412341234").setType("debit").setMoney(9000.0).setCreationDate(Date.valueOf(LocalDate.now())).build();
        Account account1 = new AccountBuilder().setIdClient(1L).setIban("RO00RZBR1234123412345678").setType("credit").setMoney(1000.0).setCreationDate(Date.valueOf(LocalDate.now())).build();

        repository.addAccount(account);
        repository.addAccount(account1);
    }

    @Test
    public void addAccount() {
        //Assert.assertEquals(0,repository.findAccountsByClientId(1L).size());
        Assert.assertEquals(2,repository.findAccountsByClientId(1L).size());
    }

    @Test
    public void findAccountsByClientId() {
        Assert.assertEquals(2,repository.findAccountsByClientId(1L).size());
        Assert.assertEquals(0, repository.findAccountsByClientId(2L).size());
    }

    @Test
    public void findAccountByIban() throws SQLException {
        Assert.assertEquals("RO00RZBR1234123412341234",repository.findAccountByIban("RO00RZBR1234123412341234").getIban());
    }

    @Test
    public void findAccountById() throws SQLException {
        Assert.assertEquals("RO00RZBR1234123412341234",repository.findAccountById(1L).getIban());
    }

    @Test
    public void update() throws SQLException {
        Account account = repository.findAccountById(1L);
        account.setMoney(500.0);
        repository.update(account);
        Assert.assertEquals(Double.valueOf(500.0),repository.findAccountById(1L).getMoney());
    }

    @Test
    public void deleteAccountByIban() {
        repository.deleteAccountByIban("RO00RZBR1234123412345678");
        Assert.assertEquals(1,repository.findAccountsByClientId(1L).size());
    }

    @Test
    public void deleteAccountById() {
        repository.deleteAccountById(1L);
        Assert.assertEquals(1,repository.findAccountsByClientId(1L).size());
    }
}