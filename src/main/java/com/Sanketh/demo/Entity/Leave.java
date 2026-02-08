package com.Sanketh.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "leave")
public class Leave {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDate startdate;
    @Column(nullable = false)
    private LocalDate enddate;
    @Column(nullable = false)
    private String reason;

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", reason='" + reason + '\'' +
                ", stats='" + stats + '\'' +
                ", employee=" + employee +
                '}';
    }

    @Column(nullable = false)
    private String stats;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;
}
