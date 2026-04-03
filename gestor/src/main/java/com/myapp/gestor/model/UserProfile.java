package com.myapp.gestor.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.hibernate.annotations.BatchSize;

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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE)
    private List<Item> items;

    public void updateStreak() {
        LocalDate today = LocalDate.now();

        if (lastActivityDate == null) {
            this.streaks = 1;
            streakStartedDate = today;
        } else {
            Long dayBetween = ChronoUnit.DAYS.between(lastActivityDate, today);

            if (dayBetween == 1) {
                this.streaks++;
            } else if (dayBetween == 0) {

                return;
            } else {
                this.streaks = 1;
                this.streakStartedDate = today;
            }

        }
        this.lastActivityDate = today;
    }
}
