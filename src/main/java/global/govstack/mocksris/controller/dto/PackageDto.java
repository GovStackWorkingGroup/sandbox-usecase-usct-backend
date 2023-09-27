package global.govstack.mocksris.controller.dto;

import global.govstack.mocksris.model.Package;

public class PackageDto {
  private int id;
  private String name;
  private String description;
  private float amount;
  private String currency;

  public PackageDto() {}

  public PackageDto(Package packageEntity) {
    this.id = packageEntity.getId();
    this.name = packageEntity.getName();
    this.description = packageEntity.getDescription();
    this.amount = packageEntity.getAmount();
    this.currency = packageEntity.getCurrency();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
