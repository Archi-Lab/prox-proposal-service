package io.archilab.projektboerse.proposalservice.repository.exception;

import lombok.Getter;

public class ArchivedProposalNotFoundException extends RuntimeException {
    private final long proposalId;
    private final long version;

    public ArchivedProposalNotFoundException(final long proposalId, final long version){
        super("Version " + version + " of proposal with id " + proposalId + " does not exist");
        this.proposalId = proposalId;
        this.version = version;
    }
}
