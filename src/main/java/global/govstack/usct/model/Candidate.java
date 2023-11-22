package global.govstack.usct.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
}
