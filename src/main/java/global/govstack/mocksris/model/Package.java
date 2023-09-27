package global.govstack.mocksris.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Package {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column private String description;

  @ManyToMany(mappedBy = "packages")
  private Set<Candidate> candidates = new HashSet<>();

  @Column private Float amount;

  @Column private String currency;

  public Float getAmount() {
    return amount;
  }

  public Package setAmount(Float amount) {
    this.amount = amount;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public Package setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public Set<Candidate> getCandidates() {
    return candidates;
  }

  public void setCandidates(Set<Candidate> candidates) {
    this.candidates = candidates;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
}
