package io.archilab.prox.proposalservice.repository.exception;

public class ProposalNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 5304808693410164486L;
  private final long id;

  public ProposalNotFoundException(final long id) {
    super("Proposal does not exist with id:" + id);
    this.id = id;
  }
}
