package com.chhin.fitnesstracker.model;

public class SelectOptionsDTO {

  private Long id;
  private String description;

  public SelectOptionsDTO(Long id, String description) {
    this.id = id;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "SelectOptionsDTO{" +
        "id=" + id +
        ", description='" + description + '\'' +
        '}';
  }
}
