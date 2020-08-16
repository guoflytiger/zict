package com.springboot.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
public class User implements Serializable{

    private static final long serialVersionUID = -1943961352036134112L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String passWord;

    private Integer age;
}
