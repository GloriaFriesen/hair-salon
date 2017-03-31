import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import java.util.Date;

public class Stylist {
  private String name;
  private Date hire_date;
  private String favorite_service;
  private int id;

  public Stylist(String name, Date hire_date, String favorite_service) {
    this.name = name;
    this.hire_date = hire_date;
    this.favorite_service = favorite_service;
  }

  public String getName() {
    return name;
  }

  public Date getHireDate() {
    return hire_date;
  }

  public String getFavoriteService() {
    return favorite_service;
  }
}
