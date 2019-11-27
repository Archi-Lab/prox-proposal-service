package io.archilab.prox.proposalservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ArchivedProposal extends AbstractProposal {

  @JsonIgnore @ManyToOne private Proposal latestProposal;

  private java.util.Date modified;

  public ArchivedProposal(Proposal proposal) {
    super(proposal);
    this.latestProposal = proposal;
    this.modified = proposal.getModified();
  }
}
