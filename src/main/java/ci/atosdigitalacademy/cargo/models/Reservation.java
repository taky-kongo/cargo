package ci.atosdigitalacademy.cargo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_reservation")
    private LocalDate dateReservation;

    @Column(name = "status")
    private String status;

    @Column(name = "depart")
    private  String depart;

    @Column(name = "destination")
    private String destination;

    private String slug;

    @ManyToOne
    private Client client;

    @ManyToOne
    @JoinColumn(name = "voyage_id")
    private Voyage voyage;

    @OneToOne
    private Payment payment;
}
