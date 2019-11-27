package io.archilab.prox.proposalservice.repository.exception;

public class ArchivedProposalNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 6776775041050638109L;
  private final long proposalId;
  private final long version;

  public ArchivedProposalNotFoundException(final long proposalId, final long version) {
    super("Version " + version + " of proposal with id " + proposalId + " does not exist");
    this.proposalId = proposalId;
    this.version = version;
  }
}
