package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "food_tracking")
public class FoodTracking {

  @Id
  @SequenceGenerator(
      name = "food_tracking_gen",
      sequenceName = "food_tracking_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_tracking_gen")
  @Column(name = "food_tracking_id", nullable = false)
  private Long foodTrackingId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private FTUser ftUser;

  @Column(name = "food_tracking_date", nullable = false)
  private LocalDate foodTrackingDate;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "food_tracking_id", referencedColumnName = "food_tracking_id")
  private List<MealTracking> mealTrackingList;
}
