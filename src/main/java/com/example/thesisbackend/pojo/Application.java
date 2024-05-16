package com.example.thesisbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @TableId(type = IdType.AUTO)
    private Integer applicationId;
    private Integer studentId;
    private Integer teacherId;
    private Integer teacherPass;
    private byte[] content;
    private String title;
}
