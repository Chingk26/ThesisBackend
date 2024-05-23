package com.example.thesisbackend.controller.proposals;

import com.example.thesisbackend.service.proposals.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @PostMapping
    public ResponseEntity<Proposal> submitProposal(@RequestBody Proposal proposal) {
        Proposal savedProposal = proposalService.saveProposal(proposal);
        return new ResponseEntity<>(savedProposal, HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Proposal>> getProposalsByStudentId(@PathVariable Long studentId) {
        List<Proposal> proposals = proposalService.getProposalsByStudentId(studentId);
        return new ResponseEntity<>(proposals, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proposal> updateProposal(@PathVariable Long id, @RequestBody Proposal proposal) {
        Proposal updatedProposal = proposalService.updateProposal(id, proposal);
        return new ResponseEntity<>(updatedProposal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProposal(@PathVariable Long id) {
        proposalService.deleteProposal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


