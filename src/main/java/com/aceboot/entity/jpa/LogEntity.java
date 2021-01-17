package com.aceboot.entity.jpa;

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
