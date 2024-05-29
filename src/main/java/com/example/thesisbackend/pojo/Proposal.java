package com.example.thesisbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

//@Entity
//public class Proposal {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String title;
//    private String description;
//    private Date submissionDate;
//    private Long studentId;
//
//    // Getters and Setters
//}
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {
    @TableId(type = IdType.AUTO)
    private Integer Id;
    private Integer studentId;
    private Integer teacherId;
    private Date submissionDate;
    private Integer teacherPass;
    private Integer deanPass;
    private byte[] content;
    private String title;
}