import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import java.util.Date;

public class Stylist {
  private String name;
  private String hire_date;
  private String favorite_service;
  private int id;

  public Stylist(String name, String hire_date, String favorite_service) {
    this.name = name;
    this.hire_date = hire_date;
    this.favorite_service = favorite_service;
  }

  public String getName() {
    return name;
  }

  public String getHireDate() {
    return hire_date;
  }

  public String getFavoriteService() {
    return favorite_service;
  }

  public int getId() {
    return id;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name, hire_date, favorite_service FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
      this.getHireDate().equals(newStylist.getHireDate()) &&
      this.getFavoriteService().equals(newStylist.getFavoriteService()) &&
      this.getId() == newStylist.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, hire_date, favorite_service) VALUES (:name, :hire_date, :favorite_service);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("hire_date", this.hire_date)
        .addParameter("favorite_service", this.favorite_service)
        .executeUpdate()
        .getKey();
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id;";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public void update(String name, String hire_date, String favorite_service) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name=:name, hire_date=:hire_date, favorite_service=:favorite_service WHERE id=:id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("hire_date", hire_date)
        .addParameter("favorite_service", favorite_service)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sqlDeleteStylist = "DELETE FROM stylists WHERE id=:id";
      con.createQuery(sqlDeleteStylist)
        .addParameter("id", id)
        .executeUpdate();
      String sqlUpdateClient = "UPDATE clients SET stylist_id=:stylist_id WHERE stylist_id=:id";
      con.createQuery(sqlUpdateClient)
        .addParameter("id", id)
        .addParameter("stylist_id", 0)
        .executeUpdate();
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Client.class);
    }
  }
}
