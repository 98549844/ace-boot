package com.ace.boot.entity.dao.hibernate;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class LogEntity implements Serializable {
    Long logId;

    String operator;
    String description;
    Date accessTime;
}
