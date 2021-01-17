package com.aceboot.entity.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity implements Serializable {
    private Integer id;
    private String password;


    //personal info
    private String userName;
    private String status;
    private String email;
    private String mobile;

    private String domain;
    private String ip;
    private String hostName;

    private String createUser;
    //注册日子
    private LocalDateTime createDate;
    private String amendUser;
    private LocalDateTime amendDate;
    private String remark;


}
