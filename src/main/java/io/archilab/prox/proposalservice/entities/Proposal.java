package io.archilab.prox.proposalservice.entities;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Proposal extends AbstractProposal {

  @OneToMany(cascade = CascadeType.ALL)
  private List<ArchivedProposal> archivedProposal = new ArrayList<>();

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false)
  @CreationTimestamp
  private java.util.Date created;

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private java.util.Date modified;

  @Basic
  @Setter
  @Getter
  private boolean studentPermitsPublish = false;

  @Basic
  @Setter
  @Getter
  private boolean supervisorPermitsPublish = false;


  public Proposal(String content, UUID projectId, UUID supervisorId, UUID studentId) {
    this.content = content;
    this.projectId = projectId;
    this.supervisorId = supervisorId;
    this.studentId = studentId;
    this.lastUpdateBy = lastUpdateBy;
  }

  public void addArchivedProposal(ArchivedProposal archivedProposal) {
    this.archivedProposal.add(archivedProposal);
  }
}
