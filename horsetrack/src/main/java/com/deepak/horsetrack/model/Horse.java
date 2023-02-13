package com.deepak.horsetrack.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="HORSE")
//@Data
@Getter
@Setter
@ToString
//@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Horse(@NonNull int horseNumber, @NonNull String horseName, @NonNull int odds, @NonNull RaceStatus raceStatus) {
        this.horseNumber = horseNumber;
        this.horseName = horseName;
        this.odds = odds;
        this.raceStatus = raceStatus;
    }

    @NonNull
    @Column
    private int horseNumber;
    @NonNull
    @Column
    private String horseName;
    @NonNull
    @Column
    private int odds;
    @NonNull
    @Column
    private RaceStatus raceStatus = RaceStatus.LOST;
}
