package global.govstack.usct.controller.dto.digital.registries;

import global.govstack.usct.controller.dto.OpenImisPackageDto;

public class PackageDto {
  private int id;
  private String name;
  private String description;
  private float amount;
  private final String currency = "EURO";

  public PackageDto() {}

  public PackageDto(OpenImisPackageDto openImisPackageDto) {
    this.id = openImisPackageDto.ID();
    this.name = openImisPackageDto.LastName();
    this.description = openImisPackageDto.FirstName();
    this.amount = openImisPackageDto.CurrentAddress();
  }

  public PackageDto(int id, String name, String description, float amount) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.amount = amount;
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
    return "EURO";
  }
}
