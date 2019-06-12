package io.archilab.projektboerse.proposalservice.controller.archivedProposal;

import io.archilab.projektboerse.proposalservice.controller.proposal.ProposalController;
import io.archilab.projektboerse.proposalservice.entities.ArchivedProposal;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class ArchivedProposalResource extends ResourceSupport {
    private final ArchivedProposal archivedProposal;

    public ArchivedProposalResource(final ArchivedProposal archivedProposal) {
        this.archivedProposal = archivedProposal;
        final long version = archivedProposal.getVersion();
        final long proposalId = archivedProposal.getLatestProposal().getId();

        add(linkTo(methodOn(ArchivedProposalController.class).all(proposalId)).withRel("archive"));
        add(linkTo(methodOn(ProposalController.class).get(proposalId)).withRel("latest-proposal"));
        add(linkTo(methodOn(ArchivedProposalController.class).get(proposalId,version)).withSelfRel());

    }
}
