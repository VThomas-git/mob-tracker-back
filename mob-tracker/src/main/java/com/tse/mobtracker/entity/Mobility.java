package com.tse.mobtracker.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Mobility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String studentName;
    private Long prom;
    private String city;
    private String destinationCountry;
    private Date beginDate;
    private Date endDate;
}
