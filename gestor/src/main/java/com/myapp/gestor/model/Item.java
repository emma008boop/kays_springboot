package com.myapp.gestor.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The product must have a name")
    private String name;

    private String description;

    @Positive
    @NotNull(message = "The item must have a price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ItemState state = ItemState.ABANDONED;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @URL(message = "Please provide an url")
    @Column(length = 1024)
    private String productUrl;

    @Column(columnDefinition = "TINYINT")
    private Integer needLevel;

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

}
