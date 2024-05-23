package com.example.thesisbackend.service.proposals;

public interface ProposalService {

    List<Proposal> getProposalsByStudentId(Long studentId);

    void deleteProposal(Long id);

    Proposal updateProposal(Long id, Proposal proposal);
}
