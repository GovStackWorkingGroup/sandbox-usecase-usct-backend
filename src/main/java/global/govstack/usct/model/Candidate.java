package global.govstack.usct.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Candidate")
public class Candidate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @Column(name = "openimis_package_id")
  private Set<Integer> openImisPackageIds = new HashSet<Integer>();

  @Column(name = "emulator_package_id")
  private Set<Integer> emulatorPackageIds = new HashSet<Integer>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "consent_id", referencedColumnName = "id")
  private Consent consent;

  @Column(name = "igrant_id")
  private String igrantId;

  @Column(name = "is_beneficiary", nullable = false)
  private boolean isBeneficiary;

  public Set<Integer> getOpenImisPackageIds() {
    return openImisPackageIds;
  }

  public void setOpenImisPackageIds(Set<Integer> packages) {
    this.openImisPackageIds = packages;
  }

  public Set<Integer> getEmulatorPackageIds() {
    return emulatorPackageIds;
  }

  public void setEmulatorPackageIds(Set<Integer> emulatorPackageIds) {
    this.emulatorPackageIds = emulatorPackageIds;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Consent getConsent() {
    return consent;
  }

  public void setConsent(Consent consent) {
    this.consent = consent;
  }

  public String getIgrantId() {
    return igrantId;
  }

  public void setIgrantId(String igrantId) {
    this.igrantId = igrantId;
  }

  public boolean getIsBeneficiary() {
      return this.isBeneficiary;
  }
  public void setIsBeneficiary(boolean isBeneficiary) {
    this.isBeneficiary = isBeneficiary;
  }
}
