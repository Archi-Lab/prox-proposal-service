package io.archilab.prox.proposalservice.controller.proposal;

import io.archilab.prox.proposalservice.entities.ArchivedProposal;
import io.archilab.prox.proposalservice.entities.Proposal;
import io.archilab.prox.proposalservice.repository.ArchivedProposalRepository;
import io.archilab.prox.proposalservice.repository.ProposalRepository;
import io.archilab.prox.proposalservice.repository.exception.ProposalNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/proposal", produces = "application/hal+json")
public class ProposalController {

  private final ProposalRepository proposalRepository;
  private final ArchivedProposalRepository archivedProposalRepository;

  public ProposalController(
      final ProposalRepository proposalRepository,
      final ArchivedProposalRepository archivedProposalRepository) {
    this.proposalRepository = proposalRepository;
    this.archivedProposalRepository = archivedProposalRepository;
  }

  @GetMapping
  public ResponseEntity<Resources<ProposalResource>> all() {
    final List<ProposalResource> collection =
        this.proposalRepository.findAll().stream()
            .map(ProposalResource::new)
            .collect(Collectors.toList());
    final Resources<ProposalResource> resources = new Resources<>(collection);
    final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    resources.add(new Link(uriString, "self"));
    return ResponseEntity.ok(resources);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProposalResource> get(@PathVariable final Long id) {
    return this.proposalRepository
        .findById(id)
        .map(p -> ResponseEntity.ok(new ProposalResource(p)))
        .orElseThrow(() -> new ProposalNotFoundException(id));
  }

  @PostMapping
  public ResponseEntity<ProposalResource> post(@RequestBody final Proposal input) {
    // TODO: Bei leerem Content das Template laden
    input.setVersion(1);
    final Proposal proposal = this.proposalRepository.save(new Proposal(input));

    // archive proposal
    this.archivedProposalRepository.save(new ArchivedProposal(proposal));

    final URI uri =
        MvcUriComponentsBuilder.fromController(this.getClass())
            .path("/{id}")
            .buildAndExpand(proposal.getId())
            .toUri();
    return ResponseEntity.created(uri).body(new ProposalResource(proposal));
  }

  // Patch behandelt nur Änderungen an dem Proposaltext
  @PatchMapping("/{id}")
  public ResponseEntity<?> put(
      @PathVariable("id") final long id, @RequestBody Proposal proposalFromRequest) {
    final Optional<Proposal> optional = this.proposalRepository.findById(id);

    if (optional.isPresent()) {
      final Proposal proposal = optional.get();
      proposal.updateContent(proposalFromRequest.getContent());
      this.proposalRepository.save(proposal);

      // archive proposal
      this.archivedProposalRepository.save(new ArchivedProposal(proposal));

      final ProposalResource resource = new ProposalResource(proposal);
      final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
      return ResponseEntity.created(uri).body(resource);

    } else {
      return ResponseEntity.notFound().build();
    }

    //        final ArchivedProposalResource resource = new ArchivedProposalResource(proposal);
    //        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
    //        return ResponseEntity.created(uri).body(resource);
  }
}
