package global.govstack.usct.model;

import global.govstack.usct.types.ConsentStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Consent")
public class Consent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @OneToOne(mappedBy = "consent")
  private Candidate candidate;

  @Column
  @Enumerated(EnumType.STRING)
  private ConsentStatus status;

  @Column private LocalDateTime date;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }

  public ConsentStatus getStatus() {
    return status;
  }

  public void setStatus(ConsentStatus status) {
    this.status = status;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}
