package com.example.thesisbackend.service.impl.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.thesisbackend.mapper.ApplicationMapper;
import com.example.thesisbackend.mapper.UserMapper;
import com.example.thesisbackend.pojo.Application;
import com.example.thesisbackend.service.application.ApplicationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.*;



@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> addApplication(Integer studentId, Integer teacherId) {
        Map<String,String> map = new HashMap<>();
        if(studentId == null) {
            map.put("error_message","学生不能为空");
            return map;
        }
        if(userMapper.selectById(studentId)==null) {
            map.put("error_message","没有这个学生");
            return map;
        }
        if(userMapper.selectById(studentId).getAuthority()!=0) {
            map.put("error_message","这个用户不是学生");
            return map;
        }
        if(teacherId == null) {
            map.put("error_message","导师不能为空");
            return map;
        }
        if(userMapper.selectById(teacherId)==null) {
            map.put("error_message","没有这个导师");
            return map;
        }
        if(userMapper.selectById(teacherId).getAuthority()!=1) {
            map.put("error_message","这个用户不是导师");
            return map;
        }
        if(applicationMapper.selectBySP(studentId,0).size()!=0||applicationMapper.selectBySP(studentId,1).size()!=0){
            map.put("error_message","您已提交过申请,请勿重复提交");
            return map;
        }
        Application application=new Application();
        application.setStudent_id(studentId);
        application.setTeacher_id(teacherId);
        applicationMapper.insert(application);
        map.put("error_message", "success");
        map.put("application_id", String.valueOf(application.getApplication_id()));
        return map;
    }

    @Override
    public Map<String, String> uploadPdf(Integer application_id,MultipartFile file) {
        Map<String,String> map = new HashMap<>();
        String filename = file.getOriginalFilename();
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
        //限制上传文件大小最大为16M
        if (file.getSize() > 16777215) { //16M
            map.put("error_message", "上传的PDF文件大于16M");
            return map;
        }

        try {
            byte[] bytes = file.getBytes();
            Application application=applicationMapper.selectById(application_id);
            application.setContent(bytes);
            //使用mybatisPlus的BaseMapper，修改数据库的data，把pdf已bytes的形式存入数据库
            applicationMapper.updateById(application);
        }catch (IOException e) {
            e.printStackTrace();
        }
        map.put("error_message", "success");
        return map;
    }

    @Override
    public void read(Integer applicationId, HttpServletResponse response) throws IOException {
        String filename = URLEncoder.encode("PDF" ,"UTF-8");
        response.setContentType("application/pdf");
        response.setHeader("filename",""+ filename + ".pdf");
        response.addHeader("Access-Control-Expose-Headers", "filename");

        // 获取数据库中的二进制数据
        QueryWrapper<Application> queryWrapper = new QueryWrapper<Application>() //利用mybatisPlus按id查询
                .eq("applicationId",applicationId);
        Application application = applicationMapper.selectOne(queryWrapper);

        ByteArrayInputStream in = new ByteArrayInputStream(application.getContent());
        OutputStream out = response.getOutputStream();
        byte[] bytes = new byte[1024];
        while((in.read(bytes)) != -1) {
            out.write(bytes);
        }
        out.flush();
        in.close();
        out.close();
    }
}
