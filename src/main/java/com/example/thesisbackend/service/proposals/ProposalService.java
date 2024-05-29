package com.example.thesisbackend.service.proposals;

import com.example.thesisbackend.pojo.Proposal;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProposalService {
    List<Proposal> getProposalsByStudentId(Integer studentId);

    void deleteProposal(Integer id);

    Proposal updateProposal(Integer id, Proposal proposal);

    Proposal saveProposal(Proposal proposal);

    Map<String, String> uploadPdf(Integer proposalId, MultipartFile file, HttpServletRequest request);

    void read(Integer proposalId, HttpServletResponse response) throws IOException;

    Map<String, String> passProposalByTeacher(Integer proposalId);

    Map<String, String> refuseProposalByTeacher(Integer proposalId);
}
