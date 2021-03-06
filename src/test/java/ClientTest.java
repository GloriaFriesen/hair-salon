import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;


public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client newClient = new Client("Tina", 1);
    assertEquals(true, newClient instanceof Client);
  }

  @Test
  public void getName_getsNameValue_name() {
    Client newClient = new Client("Tina", 1);
    assertEquals("Tina", newClient.getName());
  }

  @Test
  public void getId_returnsInstanceOfId_1() {
    Client newClient = new Client("Tina", 1);
    newClient.save();
    assertTrue(newClient.getId() > 0);
  }

  @Test
  public void all_getsAllClients_true() {
    Client firstClient = new Client("Tina", 1);
    firstClient.save();
    Client secondClient = new Client("Louise", 2);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame() {
    Client firstClient = new Client("Tina", 1);
    Client secondClient = new Client("Tina", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client newClient = new Client("Tina", 1);
    newClient.save();
    assertTrue(Client.all().get(0).equals(newClient));
  }

  @Test
  public void save_assignsIdToClient() {
    Client newClient = new Client("Tina", 1);
    newClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(newClient.getId(), savedClient.getId());
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Tina", 1);
    firstClient.save();
    Client secondClient = new Client("Louise", 2);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void update_updatesName_true() {
    Client newClient = new Client("Tina", 1);
    newClient.save();
    newClient.update("Louise");
    assertEquals("Louise", newClient.find(newClient.getId()).getName());
  }

  @Test
  public void updateStylist_updatesStylistId_true() {
    Client newClient = new Client("Tina", 1);
    newClient.save();
    newClient.updateStylist(0);
    assertEquals(0, Client.find(newClient.getId()).getStylistId());
  }

  @Test
  public void delete_deletesClient_true() {
    Client newClient = new Client("Tina", 1);
    newClient.save();
    newClient.delete();
    assertEquals(null, Client.find(newClient.getId()));
  }

}
