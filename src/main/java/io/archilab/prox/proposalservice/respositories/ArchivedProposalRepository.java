package io.archilab.prox.proposalservice.respositories;

import io.archilab.prox.proposalservice.entities.ArchivedProposal;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;
import java.util.UUID;

public interface ArchivedProposalRepository
    extends PagingAndSortingRepository<ArchivedProposal, UUID> {
  List<ArchivedProposal> findByProjectIdAndStudentIdOrderByVersionAsc(UUID projectId,
      UUID studentId);
}
