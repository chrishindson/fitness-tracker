package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "food_tracking")
public class FoodTracking {

  @Id
  @SequenceGenerator(name = "food_tracking_gen", sequenceName = "food_tracking_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_tracking_gen")
  @Column(name = "food_tracking_id", nullable = false)
  private Long foodTrackingId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private FTUser ftUser;

  @Column(name = "food_tracking_date", nullable = false)
  private LocalDate foodTrackingDate;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "food_tracking_id", referencedColumnName = "food_tracking_id")
  private List<MealTracking> mealTrackingList;

  public void setFoodTrackingId(Long foodTrackingId) {
    this.foodTrackingId = foodTrackingId;
  }

  public void setFtUser(FTUser ftUser) {
    this.ftUser = ftUser;
  }

  public void setFoodTrackingDate(LocalDate foodTrackingDate) {
    this.foodTrackingDate = foodTrackingDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodTracking that = (FoodTracking) o;
    return Objects.equals(foodTrackingId, that.foodTrackingId);
  }

  public void setMealTrackingList(List<MealTracking> mealTrackingList) {
    this.mealTrackingList = mealTrackingList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(foodTrackingId);
  }

  @Override
  public String toString() {
    return "FoodTracking{" +
        "foodTrackingId=" + foodTrackingId +
        ", foodTrackingDate=" + foodTrackingDate +
        ", mealTrackingList=" + mealTrackingList +
        '}';
  }
}