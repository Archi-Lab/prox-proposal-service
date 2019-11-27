package io.archilab.prox.proposalservice.repository;

import io.archilab.prox.proposalservice.entities.ArchivedProposal;
import io.archilab.prox.proposalservice.entities.Proposal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivedProposalRepository extends JpaRepository<ArchivedProposal, Long> {

  Optional<ArchivedProposal> findByVersionAndLatestProposal(long version, Proposal proposal);
}
