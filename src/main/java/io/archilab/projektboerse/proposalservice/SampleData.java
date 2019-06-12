package io.archilab.projektboerse.proposalservice;


import io.archilab.projektboerse.proposalservice.entities.Proposal;
import io.archilab.projektboerse.proposalservice.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;



@Component
public class SampleData implements ApplicationListener<ContextRefreshedEvent> {

    //@Autowired
    //private KundeRepository kundeRepository;

    @Autowired
    private ProposalRepository proposalRepository;


    public SampleData() {

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
//        Proposal p1 = new Proposal();
//        p1.setVersion(1L);
//        p1.setContent("Hallo");
//        p1.setProjectId("pro123");
//        p1.setStudentId("stud123");
//        p1.setSupervisorId("sup123");
//        p1.setId(1L);
//
//        proposalRepository.save(p1);

        //proposalRepository.save(new Proposal("project123", "supervisor123", "student123", "Dies ist ein Test", 1));
        //proposalRepository.save(new Proposal("project456", "supervisor456", "student456", "Dies ist ein Test2", 1));



        //proposalRepository.save(new llllllllProposal(123L, 567, 789, "Hello"));
        //proposalRepository.save(new llllllllProposal(123L, 567, 789, "Hello2"));

    }

}
