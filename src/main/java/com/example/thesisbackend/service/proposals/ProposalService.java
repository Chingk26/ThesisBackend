package com.example.thesisbackend.service.proposals;

import com.example.thesisbackend.pojo.Proposal;

import java.util.*;

public interface ProposalService {

    List<Proposal> getProposalsByStudentId(Long studentId);

    void deleteProposal(Long id);

    Proposal updateProposal(Long id, Proposal proposal);

    Proposal saveProposal(Proposal proposal);
}
