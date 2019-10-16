package io.archilab.prox.proposalservice.controller;

import io.archilab.prox.proposalservice.entities.ArchivedProposal;
import io.archilab.prox.proposalservice.entities.Proposal;
import io.archilab.prox.proposalservice.respositories.ArchivedProposalRepository;
import io.archilab.prox.proposalservice.respositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RepositoryRestController
public class ProposalController {
  private final ProposalRepository proposalRepository;
  private final ArchivedProposalRepository archivedProposalRepository;

  @Autowired
  public ProposalController(final ProposalRepository proposalRepository,
      final ArchivedProposalRepository archivedProposalRepository) {
    this.proposalRepository = proposalRepository;
    this.archivedProposalRepository = archivedProposalRepository;
  }

  @PatchMapping("proposals/{id}")
  public ResponseEntity<?> patch(@PathVariable("id") final UUID id,
      @RequestBody Proposal proposalFromRequest) {
    final Optional<Proposal> optional = proposalRepository.findById(id);

    if (optional.isPresent()) {
      final Proposal proposal = optional.get();

      // archive old proposal
      ArchivedProposal archivedProposal = new ArchivedProposal(proposal);
      archivedProposalRepository.save(archivedProposal);

      // update proposal
      proposal.addArchivedProposal(archivedProposal);
      proposal.setContent(proposalFromRequest.getContent());
      proposal.setVersion(proposal.getVersion() + 1);

      proposal.setStudentPermitsPublish(proposalFromRequest.isStudentPermitsPublish());
      proposal.setSupervisorPermitsPublish(proposalFromRequest.isSupervisorPermitsPublish());
      proposal.setLastUpdateBy(proposalFromRequest.getLastUpdateBy());
      proposalRepository.save(proposal);


      // Resource anlegen
      final ProposalResource resource = new ProposalResource(proposal);
      final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
      return ResponseEntity.created(uri).body(resource);

    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
