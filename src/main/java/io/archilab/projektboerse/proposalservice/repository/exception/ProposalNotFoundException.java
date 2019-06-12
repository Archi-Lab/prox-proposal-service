package io.archilab.projektboerse.proposalservice.repository.exception;

import lombok.Getter;

public class ProposalNotFoundException extends RuntimeException {
    private final long id;

    public ProposalNotFoundException(final long id){
        super("Proposal does not exist with id:" + id);
        this.id = id;
    }
}
