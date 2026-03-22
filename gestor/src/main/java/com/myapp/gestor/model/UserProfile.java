package com.myapp.gestor.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private Integer streaks = 0;

    @NotBlank(message = "The currency must be selected")
    private String currency;

    private LocalDate lastActivityDate;
    private LocalDate streakStartedDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE)
    private List<Items> items;

    public void updateStrake() {
        LocalDate today = LocalDate.now();

        if (lastActivityDate == null) {
            this.streaks = 1;
            streakStartedDate = today;
        } else {
            Long dayBetween = ChronoUnit.DAYS.between(lastActivityDate, today);

            if (dayBetween == 1) {
                this.streaks++;
            } else if (dayBetween > 2) {
                this.streaks = 1;
                this.streakStartedDate = today;
            }
        }
        this.lastActivityDate = today;
    }
}
