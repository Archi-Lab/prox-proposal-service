package io.archilab.prox.proposalservice.controller.proposal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import io.archilab.prox.proposalservice.entities.Proposal;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class ProposalResource extends ResourceSupport {

  private final Proposal proposal;

  public ProposalResource(Proposal proposal) {
    this.proposal = proposal;
    final Long id = proposal.getId();

    // TODO: Links hinzufügen
    this.add(linkTo(ProposalController.class).withRel("proposal"));
    // add(linkTo(methodOn(ArchivedProposalController.class).all(id)).withRel("archive"));
    this.add(linkTo(methodOn(ProposalController.class).get(id)).withSelfRel());
  }
}
