package com.example.thesisbackend.service.impl.proposal;

import com.example.thesisbackend.pojo.Proposal;
import com.example.thesisbackend.service.proposals.ProposalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalServiceImpl implements ProposalService {
    @Override
    public List<Proposal> getProposalsByStudentId(Long studentId) {
        return null;
    }

    @Override
    public void deleteProposal(Long id) {

    }

    @Override
    public Proposal updateProposal(Long id, Proposal proposal) {
        return null;
    }

    @Override
    public Proposal saveProposal(Proposal proposal) {
        return null;
    }
}
