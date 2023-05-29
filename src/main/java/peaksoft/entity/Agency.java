package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Entity
@Table(name = "agency")
@Getter
@Setter
@NoArgsConstructor
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "agency_gen")
    @SequenceGenerator(name = "agency_gen",sequenceName = "agency_gen",allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    @Value("phone_number")
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Column(length = 1000)
    private String image;
    @ManyToMany(mappedBy = "agencies",cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Customer> customers;
    @OneToMany (mappedBy = "agency",cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<House> houses;

}
