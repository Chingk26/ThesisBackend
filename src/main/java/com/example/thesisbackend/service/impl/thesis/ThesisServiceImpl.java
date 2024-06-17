package com.example.thesisbackend.service.impl.thesis;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.thesisbackend.mapper.ThesisMapper;
import com.example.thesisbackend.mapper.UserMapper;
import com.example.thesisbackend.pojo.Thesis;
import com.example.thesisbackend.service.thesis.ThesisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ThesisServiceImpl implements ThesisService {
    @Autowired
    private ThesisMapper thesisMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> addThesis(Integer studentId, Float result, Integer version) {
        Map<String,String> map = new HashMap<>();
        LocalDateTime localDateTime=LocalDateTime.now();
        Thesis thesis=new Thesis();
        thesis.setStudentId(studentId);
        LocalDateTime start=userMapper.selectByAuthority(2).get(0).getStart();
        LocalDateTime end=userMapper.selectByAuthority(2).get(0).getEnd();
        if(localDateTime.isBefore(start)||localDateTime.isAfter(end)){
            map.put("error_message","不在论文提交时间，无法上传");
            return map;
        }
        if(userMapper.selectById(studentId).getTeacherId()==null){
            map.put("error_message", "您还没有导师，请先进行导师申请");
            return map;
        }
        thesis.setTeacherId(userMapper.selectById(studentId).getTeacherId());
        thesis.setResult(result);
        thesis.setStart(start);
        thesis.setEnd(end);
        thesis.setVersion(version);
        thesisMapper.insert(thesis);
        map.put("error_message", "success");
        map.put("thesis_id", String.valueOf(thesis.getThesisId()));
        return map;
    }

    @Override
    public Map<String, String> uploadPdf(Integer thesisId, MultipartFile file, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Map<String,String> map = new HashMap<>();
        String filename = file.getOriginalFilename();
        String prename=filename.substring(0,filename.lastIndexOf("."));
        // 文件是否为空
        if (StringUtils.isEmpty(filename)) {
            map.put("error_message", "文件名为空");
            return map;
        }
        //获取文件后缀名
        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
        if (!suffixName.equals("pdf")) {
            map.put("error_message", "上传的文件非PDF格式");
            return map;
        }
        //限制上传文件大小最大为10M
        if (file.getSize() > 10485760) { //10M
            map.put("error_message", "上传的PDF文件大于10M");
            return map;
        }

        try {
            byte[] bytes = file.getBytes();
            // 数据库已有数据进行修改
            Thesis thesis=thesisMapper.selectById(thesisId);
            thesis.setContent(bytes);
            thesis.setTitle(prename);
            //使用mybatisPlus的BaseMapper，修改数据库的data，把pdf已bytes的形式存入数据库
            thesisMapper.updateById(thesis);
        }catch (IOException e) {
            e.printStackTrace();
        }
        map.put("error_message", "success");
        return map;
    }

    @Override
    public void read(Integer thesisId, HttpServletResponse response) throws IOException {
        Thesis thesis = thesisMapper.selectById(thesisId);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + thesis.getTitle() + ".pdf" + "\"");
        ByteArrayInputStream in = new ByteArrayInputStream(thesis.getContent());
        OutputStream out = response.getOutputStream();
        byte[] bytes = new byte[1024];
        while((in.read(bytes)) != -1) {
            out.write(bytes);
        }
        out.flush();
        in.close();
        out.close();
    }

    @Override
    public Map<String, String> updateThesis(Integer thesisId, Float result) {
        Map<String,String> map = new HashMap<>();
        Thesis thesis=thesisMapper.selectById(thesisId);
        LocalDateTime localDateTime=LocalDateTime.now();
        if(thesis.getVersion()==2){
            map.put("error_message","论文终稿不得修改");
            return map;
        }
        if(localDateTime.isBefore(thesis.getStart())||localDateTime.isAfter(thesis.getEnd())){
            map.put("error_message","不在论文提交时间，无法上传");
            return map;
        }
        if(localDateTime.isAfter(thesis.getEnd().minusDays(7))||localDateTime.isAfter(thesis.getEnd())){

        }
        if(userMapper.selectById(thesis.getStudentId()).getTeacherId()==null){
            map.put("error_message", "您还没有导师，请先进行导师申请");
            return map;
        }
        thesis.setResult(result);
        thesis.setTeacherPass(0);
        thesis.setDeanPass(0);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        map.put("thesis_id", String.valueOf(thesis.getThesisId()));
        return map;
    }

}
