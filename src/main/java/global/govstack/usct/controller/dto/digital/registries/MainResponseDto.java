package global.govstack.usct.controller.dto.digital.registries;

import java.util.List;

public class MainResponseDto {

  private Integer count;
  private Integer next;
  private Integer previous;
  private List<PackageDto> results;

  public MainResponseDto() {}

  public MainResponseDto(Integer count, Integer next, Integer previous, List<PackageDto> results) {
    this.count = count;
    this.next = next;
    this.previous = previous;
    this.results = results;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getNext() {
    return next;
  }

  public void setNext(Integer next) {
    this.next = next;
  }

  public Integer getPrevious() {
    return previous;
  }

  public void setPrevious(Integer previous) {
    this.previous = previous;
  }

  public List<PackageDto> getResults() {
    return results;
  }

  public void setResults(List<PackageDto> results) {
    this.results = results;
  }
}
