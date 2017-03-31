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
}
