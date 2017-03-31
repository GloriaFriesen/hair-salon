import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_returnsInstanceOfStylist_true() throws ParseException {
    Stylist newStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    assertEquals(true, newStylist instanceof Stylist);
  }

  @Test
  public void getName_instantiatesWithName_String() throws ParseException {
    Stylist newStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    assertEquals("Abby", newStylist.getName());
  }

  @Test
  public void getHireDate_instantiatesWithHireDate_Date() throws ParseException {
    Stylist newStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    Date expectedOutput = new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011");
    assertEquals(expectedOutput, newStylist.getHireDate());
  }

  @Test
  public void getFavoriteService_instantiatesWithFavoriteService_String() throws ParseException {
    Stylist newStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    assertEquals("color", newStylist.getFavoriteService());
  }

  @Test
  public void getId_returnsInstanceWithId_true() throws ParseException {
    Stylist newStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    newStylist.save();
    assertTrue(newStylist.getId() > 0);
  }

  @Test
  public void all_getsAllStylists_true() throws ParseException {
    Stylist firstStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Pepper Jack", new SimpleDateFormat("MM/dd/yyyy").parse("10/08/2015"), "beard trim");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame() throws ParseException {
    Stylist firstStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    Stylist secondStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() throws ParseException {
    Stylist newStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void find_returnsStylistWithSameId_secondStylist() throws ParseException {
    Stylist firstStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Pepper Jack", new SimpleDateFormat("MM/dd/yyyy").parse("10/08/2015"), "beard trim");
    secondStylist.save();
    assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  }

  @Test
  public void update_updatesStylist_true() throws ParseException {
    Stylist newStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    newStylist.save();
    newStylist.update("Pepper Jack", new SimpleDateFormat("MM/dd/yyyy").parse("10/08/2015"), "beard trim");
    Date updatedDate = new SimpleDateFormat("MM/dd/yyyy").parse("10/08/2015");
    assertEquals("Pepper Jack", newStylist.find(newStylist.getId()).getName());
    assertEquals(updatedDate, newStylist.find(newStylist.getId()).getHireDate());
    assertEquals("beard trim", newStylist.find(newStylist.getId()).getFavoriteService());
  }

  @Test
  public void delete_deletesStylist_true() throws ParseException {
    Stylist newStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    newStylist.save();
    newStylist.delete();
    assertEquals(null, newStylist.find(newStylist.getId()));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientList() throws ParseException {
    Stylist newStylist = new Stylist("Abby", new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"), "color");
    newStylist.save();
    Client firstClient = new Client("Tina", newStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Louise", newStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] {firstClient, secondClient};
    assertTrue(newStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
