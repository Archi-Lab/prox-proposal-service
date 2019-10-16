package io.archilab.prox.proposalservice.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArchivedProposal extends AbstractProposal {

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false)
  private java.util.Date created;

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date modified;

  public ArchivedProposal(String content, UUID projectId, UUID supervisorId, UUID studentId) {
    this.content = content;

    this.projectId = projectId;

    this.supervisorId = supervisorId;
    this.studentId = studentId;
  }

  public ArchivedProposal(Proposal proposal) {
    // this.latestProposal = proposal;
    this.content = proposal.getContent();
    this.projectId = proposal.getProjectId();
    this.supervisorId = proposal.getSupervisorId();
    this.studentId = proposal.getStudentId();
    this.version = proposal.getVersion();
    this.created = proposal.getCreated();
    this.modified = proposal.getModified();
    this.lastUpdateBy = proposal.getLastUpdateBy();
  }
}
