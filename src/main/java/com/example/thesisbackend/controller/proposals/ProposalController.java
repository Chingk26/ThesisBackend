package com.example.thesisbackend.controller.proposals;

import com.example.thesisbackend.pojo.Proposal;
import com.example.thesisbackend.service.proposals.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Proposal>> getProposalsByStudentId(@PathVariable Integer studentId) {
        List<Proposal> proposals = proposalService.getProposalsByStudentId(studentId);
        return new ResponseEntity<>(proposals, HttpStatus.OK);
    }

    @DeleteMapping("/{proposalId}")
    public ResponseEntity<Map<String, String>> deleteProposal(@PathVariable Integer proposalId) {
        proposalService.deleteProposal(proposalId);
        return new ResponseEntity<>(Map.of("error_message", "success"), HttpStatus.OK);
    }

    @PutMapping("/{proposalId}")
    public ResponseEntity<Proposal> updateProposal(@PathVariable Integer proposalId,
                                                   @RequestBody Proposal proposal) {
        Proposal updatedProposal = proposalService.updateProposal(proposalId, proposal);
        return new ResponseEntity<>(updatedProposal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Proposal> saveProposal(@RequestBody Proposal proposal) {
        Proposal savedProposal = proposalService.saveProposal(proposal);
        return new ResponseEntity<>(savedProposal, HttpStatus.CREATED);
    }

    @PostMapping("/upload/{proposalId}")
    public ResponseEntity<Map<String, String>> uploadPdf(@PathVariable Integer proposalId,
                                                         @RequestParam("file") MultipartFile file,
                                                         HttpServletRequest request) {
        Map<String, String> response = proposalService.uploadPdf(proposalId, file, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/read/{proposalId}")
    public void readPdf(@PathVariable Integer proposalId, HttpServletResponse response) throws IOException {
        proposalService.read(proposalId, response);
    }

    @PutMapping("/pass/{proposalId}")
    public ResponseEntity<Map<String, String>> passProposalByTeacher(@PathVariable Integer proposalId) {
        Map<String, String> response = proposalService.passProposalByTeacher(proposalId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/refuse/{proposalId}")
    public ResponseEntity<Map<String, String>> refuseProposalByTeacher(@PathVariable Integer proposalId) {
        Map<String, String> response = proposalService.refuseProposalByTeacher(proposalId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
