package io.archilab.prox.proposalservice.controller;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.archilab.prox.proposalservice.entities.Proposal;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import java.util.UUID;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Getter
public class ProposalResource extends ResourceSupport {
  @JsonUnwrapped
  private final Proposal proposal;

  public ProposalResource(Proposal proposal) {
    this.proposal = proposal;
    final UUID id = proposal.getId();

    // TODO: Links hinzufügen
    // add(linkTo(ProposalController.class).withRel("proposal"));
    // add(linkTo(methodOn(ArchivedProposalController.class).all(id)).withRel("archive"));
    add(linkTo(ProposalController.class).slash("proposals").slash(id).withSelfRel());
    add(linkTo(ProposalController.class).slash("proposals").slash(id).withRel("proposal"));
    add(linkTo(ProposalController.class).slash("proposals").slash(id).slash("archivedProposal")
        .withRel("archivedProposal"));

  }
}
