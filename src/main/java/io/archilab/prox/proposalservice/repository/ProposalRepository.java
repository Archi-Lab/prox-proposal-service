package io.archilab.prox.proposalservice.repository;

import io.archilab.prox.proposalservice.entities.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {}
