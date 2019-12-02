package io.archilab.prox.proposalservice.respositories;

import io.archilab.prox.proposalservice.entities.Proposal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "proposals", path = "proposals")
public interface ProposalRepository extends PagingAndSortingRepository<Proposal, UUID> {
  List<Proposal> findByProjectId(UUID projectId);
  List<Proposal> findByProjectIdAndStudentId(UUID projectId, UUID studentId);
  List<Proposal> findByProjectIdAndSupervisorPermitsPublishIsTrueAndStudentPermitsPublishIsTrue(UUID projectId);

}
