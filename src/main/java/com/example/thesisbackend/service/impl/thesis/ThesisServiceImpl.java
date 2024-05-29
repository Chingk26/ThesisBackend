package com.example.thesisbackend.service.impl.thesis;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.thesisbackend.mapper.ApplicationMapper;
import com.example.thesisbackend.mapper.TeacherMapper;
import com.example.thesisbackend.mapper.ThesisMapper;
import com.example.thesisbackend.pojo.Application;
import com.example.thesisbackend.pojo.Thesis;
import com.example.thesisbackend.pojo.User;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThesisServiceImpl implements ThesisService {
    @Autowired
    private ThesisMapper thesisMapper;
    @Override
    public Map<String, String> addThesis(Integer studentId, Integer teacherId, String result) {
        Map<String,String> map = new HashMap<>();
        Thesis thesis=new Thesis();
        thesis.setStudentId(studentId);
        thesis.setTeacherId(teacherId);
        thesis.setResult(result);
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
    public Map<String, String> passThesisByTeacher(Integer thesisId) {
        Map<String,String> map = new HashMap<>();
        if(thesisMapper.selectById(thesisId)==null) {
            map.put("error_message","没有这个申请");
            return map;
        }
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setTeacherPass(1);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> refuseThesisByTeacher(Integer thesisId) {
        Map<String,String> map = new HashMap<>();
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setTeacherPass(2);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }
}
