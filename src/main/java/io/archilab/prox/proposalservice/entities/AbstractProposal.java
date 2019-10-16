package io.archilab.prox.proposalservice.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
@Setter(AccessLevel.NONE)
public class AbstractProposal {
  @Id
  private UUID id;

  @Basic
  @Setter
  protected String content;

  @Basic
  @Setter
  protected int version;

  @Basic
  @Setter
  protected UUID projectId;

  @Basic
  @Setter
  protected UUID supervisorId;

  @Basic
  @Setter
  protected UUID studentId;

  @Basic
  @Setter
  @Getter
  protected String lastUpdateBy;

  protected AbstractProposal() {
    this.id = UUID.randomUUID();
  }
}
