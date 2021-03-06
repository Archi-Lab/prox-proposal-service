package io.archilab.projektboerse.proposalservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class ArchivedProposal extends AbstractProposal {

    @JsonIgnore
    @ManyToOne
    private Proposal latestProposal;

    private java.util.Date modified;

    public ArchivedProposal(Proposal proposal){
        super(proposal);
        this.latestProposal = proposal;
        this.modified = proposal.getModified();
    }
}
