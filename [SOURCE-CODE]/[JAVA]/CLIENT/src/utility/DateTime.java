package utility;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateTime implements Serializable
{
  private LocalDateTime time;

  public DateTime()
  {
    this.time = LocalDateTime.now();
  }

  public DateTime(LocalDateTime time)
  {
    this.time = time;
  }

  public LocalDateTime getLocalDateTime() {
    return time;
  }

  public String getTimestamp()
  {
    DateTimeFormatter dtf;
    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    return time.format(dtf);
  }

  public String getSortableDate()
  {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return time.format(dtf);
  }

  @Override public boolean equals(Object o)
  {
    if (!(o instanceof DateTime)) {
      return false;
    }
    DateTime other = (DateTime)o;
    return (
        this.time.getDayOfMonth() == other.time.getDayOfMonth() &&
            this.time.getMonthValue() == other.time.getMonthValue() &&
            this.time.getYear() == other.time.getYear() &&
            this.time.getHour() == other.time.getHour() &&
            this.time.getMinute() == other.time.getMinute() &&
            this.time.getSecond() == other.time.getSecond() &&
            this.time.getNano() == other.time.getNano()
    );
  }

  @Override public int hashCode()
  {
    return Objects.hash(time);
  }

  @Override public String toString()
  {
    return getTimestamp();
  }
}