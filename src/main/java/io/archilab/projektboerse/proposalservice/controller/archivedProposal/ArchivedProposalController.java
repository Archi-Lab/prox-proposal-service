package io.archilab.projektboerse.proposalservice.controller.archivedProposal;

import io.archilab.projektboerse.proposalservice.entities.Proposal;
import io.archilab.projektboerse.proposalservice.repository.ArchivedProposalRepository;
import io.archilab.projektboerse.proposalservice.repository.ProposalRepository;
import io.archilab.projektboerse.proposalservice.repository.exception.ArchivedProposalNotFoundException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/proposal/{id}/archive", produces = "application/hal+json")
public class ArchivedProposalController {

    private ProposalRepository proposalRepository;
    private ArchivedProposalRepository archivedProposalRepository;


    public ArchivedProposalController(
            final ProposalRepository proposalRepository,
            final ArchivedProposalRepository archivedProposalRepository) {
        this.proposalRepository = proposalRepository;
        this.archivedProposalRepository = archivedProposalRepository;
    }

    @GetMapping
    public ResponseEntity<Resources<ArchivedProposalResource>> all(@PathVariable final long id){
        final List<ArchivedProposalResource> collection = getArchivedProposalsFromProposal(id);
        final Resources<ArchivedProposalResource> resources = new Resources<>(collection);

        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }


    private List<ArchivedProposalResource> getArchivedProposalsFromProposal(final long proposalId){
        return proposalRepository
                .findById(proposalId)
                .map(proposal ->
                        proposal.getOldVersions()
                                .stream()
                                .map(ArchivedProposalResource::new)
                                .collect(Collectors.toList())
                ).orElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/{version}")
    public ResponseEntity<ArchivedProposalResource> get (
            @PathVariable final long id, @PathVariable final long version) {
        final Optional<Proposal> optionalProposal = proposalRepository.findById(id);
        //TODO Sicher machen
        //final ArchivedProposal archivedProposal = archivedProposalRepository.findByVersionAndProposal(version, optionalProposal.get());
        return archivedProposalRepository
                .findByVersionAndLatestProposal(version, optionalProposal.get())
                .map(p -> ResponseEntity.ok(new ArchivedProposalResource(p)) )
                .orElseThrow(() -> new ArchivedProposalNotFoundException(id, version));


    }
}
