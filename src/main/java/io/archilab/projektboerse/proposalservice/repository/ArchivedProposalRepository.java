package io.archilab.projektboerse.proposalservice.repository;

import io.archilab.projektboerse.proposalservice.entities.ArchivedProposal;
import io.archilab.projektboerse.proposalservice.entities.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArchivedProposalRepository extends JpaRepository<ArchivedProposal, Long> {
    Optional<ArchivedProposal> findByVersionAndLatestProposal(long version, Proposal proposal);
}
