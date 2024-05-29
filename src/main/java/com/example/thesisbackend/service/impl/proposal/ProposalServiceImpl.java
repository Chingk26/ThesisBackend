package com.example.thesisbackend.service.impl.proposals;

import com.example.thesisbackend.mapper.ProposalMapper;
import com.example.thesisbackend.pojo.Proposal;
import com.example.thesisbackend.service.proposals.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProposalServiceImpl implements ProposalService {
    @Autowired
    private ProposalMapper proposalMapper;

    @Override
    public List<Proposal> getProposalsByStudentId(Integer studentId) {
        return proposalMapper.findByStudentId(studentId);
    }

    @Override
    public void deleteProposal(Integer id) {
        proposalMapper.deleteById(id);
    }

    @Override
    public Proposal updateProposal(Integer id, Proposal proposal) {
        proposal.setId(id);
        proposalMapper.updateById(proposal);
        return proposal;
    }

    @Override
    public Proposal saveProposal(Proposal proposal) {
        proposalMapper.insert(proposal);
        return proposal;
    }

    @Override
    public Map<String, String> uploadPdf(Integer proposalId, MultipartFile file, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> map = new HashMap<>();
        String filename = file.getOriginalFilename();
        String prename = filename.substring(0, filename.lastIndexOf("."));
        // 文件是否为空
        if (filename == null || filename.isEmpty()) {
            map.put("error_message", "文件名为空");
            return map;
        }
        // 获取文件后缀名
        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
        if (!suffixName.equalsIgnoreCase("pdf")) {
            map.put("error_message", "上传的文件非PDF格式");
            return map;
        }
        // 限制上传文件大小最大为10M
        if (file.getSize() > 10485760) { // 10M
            map.put("error_message", "上传的PDF文件大于10M");
            return map;
        }

        try {
            byte[] bytes = file.getBytes();
            // 数据库已有数据进行修改
            Proposal proposal = proposalMapper.selectById(proposalId);
            proposal.setContent(bytes);
            proposal.setTitle(prename);
            // 使用mybatisPlus的BaseMapper，修改数据库的data，把pdf以bytes的形式存入数据库
            proposalMapper.updateById(proposal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("error_message", "success");
        return map;
    }

    @Override
    public void read(Integer proposalId, HttpServletResponse response) throws IOException {
        Proposal proposal = proposalMapper.selectById(proposalId);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + proposal.getTitle() + ".pdf" + "\"");
        ByteArrayInputStream in = new ByteArrayInputStream(proposal.getContent());
        OutputStream out = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.flush();
        in.close();
        out.close();
    }

    @Override
    public Map<String, String> passProposalByTeacher(Integer proposalId) {
        Map<String, String> map = new HashMap<>();
        Proposal proposal = proposalMapper.selectById(proposalId);
        if (proposal == null) {
            map.put("error_message", "没有这个开题报告");
            return map;
        }
        proposal.setTeacherPass(1);
        proposalMapper.updateById(proposal);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> refuseProposalByTeacher(Integer proposalId) {
        Map<String, String> map = new HashMap<>();
        Proposal proposal = proposalMapper.selectById(proposalId);
        if (proposal == null) {
            map.put("error_message", "没有这个开题报告");
            return map;
        }
        proposal.setTeacherPass(2);
        proposalMapper.updateById(proposal);
        map.put("error_message", "success");
        return map;
    }
}
