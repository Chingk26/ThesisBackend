package com.example.thesisbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private Integer teacherId;
    private String username;
    private String password;
    private Integer authority;
    private Integer access;
    private LocalDateTime start;
    private LocalDateTime end;
}
