package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import peaksoft.enums.HouseType;

@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor(force = true)
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "house_gen")
    @SequenceGenerator(name = "house_gen",sequenceName = "house_seq",allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Value("house_type")
    private HouseType houseType;
    private String address;
    private int price;
    private int room;
    private String country;
    private String description;
    private Boolean isBooked;
    @Column(length = 1000)
    private String image;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.MERGE})
    private Agency agency;
    @OneToOne(mappedBy = "houseId",cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private Booking booking;
}
