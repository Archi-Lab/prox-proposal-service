package io.archilab.projektboerse.proposalservice.controller.proposal;

import io.archilab.projektboerse.proposalservice.entities.Proposal;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class ProposalResource extends ResourceSupport {
    private final Proposal proposal;

    public ProposalResource(Proposal proposal){
        this.proposal = proposal;
        final Long id = proposal.getId();

        //TODO: Links hinzufügen
        add(linkTo(ProposalController.class).withRel("proposal"));
        //add(linkTo(methodOn(ArchivedProposalController.class).all(id)).withRel("archive"));
        add(linkTo(methodOn(ProposalController.class).get(id)).withSelfRel());

    }
}
