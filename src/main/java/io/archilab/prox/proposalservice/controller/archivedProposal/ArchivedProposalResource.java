package io.archilab.prox.proposalservice.controller.archivedProposal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import io.archilab.prox.proposalservice.controller.proposal.ProposalController;
import io.archilab.prox.proposalservice.entities.ArchivedProposal;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class ArchivedProposalResource extends ResourceSupport {

  private final ArchivedProposal archivedProposal;

  public ArchivedProposalResource(final ArchivedProposal archivedProposal) {
    this.archivedProposal = archivedProposal;
    final long version = archivedProposal.getVersion();
    final long proposalId = archivedProposal.getLatestProposal().getId();

    this.add(linkTo(methodOn(ArchivedProposalController.class).all(proposalId)).withRel("archive"));
    this.add(linkTo(methodOn(ProposalController.class).get(proposalId)).withRel("latest-proposal"));
    this.add(
        linkTo(methodOn(ArchivedProposalController.class).get(proposalId, version)).withSelfRel());
  }
}
