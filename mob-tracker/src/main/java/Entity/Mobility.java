package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.Year;

@Entity
public class Mobility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String studentName;
    private Year prom;
    private String city;
    private String destinationCountry;
    private Date beginDate;
    private Date endDate;
}
