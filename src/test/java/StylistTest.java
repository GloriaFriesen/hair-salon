import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Date;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_returnsInstanceOfStylist_true()  {
    Stylist newStylist = new Stylist("Abby", "08/16/2011", "color");
    assertEquals(true, newStylist instanceof Stylist);
  }

  @Test
  public void getName_instantiatesWithName_String() {
    Stylist newStylist = new Stylist("Abby", "08/16/2011", "color");
    assertEquals("Abby", newStylist.getName());
  }

  @Test
  public void getHireDate_instantiatesWithHireDate_Date() {
    Stylist newStylist = new Stylist("Abby", "08/16/2011", "color");
    assertEquals("08/16/2011", newStylist.getHireDate());
  }

  @Test
  public void getFavoriteService_instantiatesWithFavoriteService_String() {
    Stylist newStylist = new Stylist("Abby", "08/16/2011", "color");
    assertEquals("color", newStylist.getFavoriteService());
  }

  @Test
  public void getId_returnsInstanceWithId_true() {
    Stylist newStylist = new Stylist("Abby", "08/16/2011", "color");
    newStylist.save();
    assertTrue(newStylist.getId() > 0);
  }

  @Test
  public void all_getsAllStylists_true() {
    Stylist firstStylist = new Stylist("Abby", "08/16/2011", "color");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Pepper Jack", "10/08/2015", "beard trim");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame() {
    Stylist firstStylist = new Stylist("Abby", "08/16/2011", "color");
    Stylist secondStylist = new Stylist("Abby", "08/16/2011", "color");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist newStylist = new Stylist("Abby", "08/16/2011", "color");
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("Abby", "08/16/2011", "color");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Pepper Jack", "10/08/2015", "beard trim");
    secondStylist.save();
    assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  }

  @Test
  public void update_updatesStylist_true() {
    Stylist newStylist = new Stylist("Abby", "08/16/2011", "color");
    newStylist.save();
    newStylist.update("Pepper Jack", "10/08/2015", "beard trim");
    assertEquals("Pepper Jack", newStylist.find(newStylist.getId()).getName());
    assertEquals("10/08/2015", newStylist.find(newStylist.getId()).getHireDate());
    assertEquals("beard trim", newStylist.find(newStylist.getId()).getFavoriteService());
  }

  @Test
  public void delete_deletesStylist_true() {
    Stylist newStylist = new Stylist("Abby", "08/16/2011", "color");
    newStylist.save();
    newStylist.delete();
    assertEquals(null, newStylist.find(newStylist.getId()));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientList() {
    Stylist newStylist = new Stylist("Abby", "08/16/2011", "color");
    newStylist.save();
    Client firstClient = new Client("Tina", newStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Louise", newStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] {firstClient, secondClient};
    assertTrue(newStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
