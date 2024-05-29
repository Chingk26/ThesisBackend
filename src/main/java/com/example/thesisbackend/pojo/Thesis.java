package com.example.thesisbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thesis {
    @TableId(type = IdType.AUTO)
    private Integer thesisId;
    private Integer studentId;
    private Integer teacherId;
    private Date start;
    private Date end;
    private Integer teacherPass;
    private Integer deanPass;
    private byte[] content;
    private String title;
    private String result;
    private String progress;
    private String quality;
    private String opinion;
}
