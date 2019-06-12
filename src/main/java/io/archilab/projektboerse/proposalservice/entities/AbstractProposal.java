package io.archilab.projektboerse.proposalservice.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class AbstractProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long version;
    private String projectId;
    private String supervisorId;
    private String studentId;

    @Column(columnDefinition="TEXT")
    private String content;

    public AbstractProposal(AbstractProposal abstractProposal){
        this.version = abstractProposal.version;
        this.projectId = abstractProposal.projectId;
        this.supervisorId = abstractProposal.supervisorId;
        this.studentId = abstractProposal.studentId;
        this.content = abstractProposal.content;
    }
    public AbstractProposal(AbstractProposal abstractProposal, long id){
        this(abstractProposal);
        this.id = id;
    }


}
