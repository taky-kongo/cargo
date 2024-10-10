package ci.atosdigitalacademy.cargo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voyages")
public class Voyage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String start;

    private String destination;

    @Column(name = "date_voyage")
    private LocalDate dateVoyage;

    private String time;

    private String slug;

    @OneToMany
    private List<Reservation> reservations;

    @ManyToOne
    private Company company;

    @OneToMany
    private List<Seat> seats;
}
