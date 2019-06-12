package io.archilab.projektboerse.proposalservice.repository;

import io.archilab.projektboerse.proposalservice.entities.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
