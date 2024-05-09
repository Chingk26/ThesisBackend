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
    private Integer application_id;
    private Integer student_id;
    private Integer teacher_id;
    private Integer teacher_pass;
    private String content;
}
