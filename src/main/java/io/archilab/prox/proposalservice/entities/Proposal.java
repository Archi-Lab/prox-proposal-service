package io.archilab.prox.proposalservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.archilab.prox.proposalservice.template.TemplateReader;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@NoArgsConstructor
public class Proposal extends AbstractProposal {

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false)
  @CreationTimestamp
  private java.util.Date created;

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private java.util.Date modified;

  @JsonIgnore
  @OneToMany(mappedBy = "latestProposal", cascade = CascadeType.ALL)
  private List<ArchivedProposal> oldVersions;

  public Proposal(final Proposal proposal) {
    super(proposal);

    if (this.getContent() == null) {
      this.setContent(TemplateReader.getInstance().getTemplate());
    }
    // TODO: created und modified verwalten
  }

  public void updateContent(String newContent) {
    this.setVersion(this.getVersion() + 1);
    this.setContent(newContent);
  }
}
