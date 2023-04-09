package com.chhin.fitnesstracker.model;

public class SelectOptionsDTO {

  private Integer id;
  private String description;

  public SelectOptionsDTO(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
