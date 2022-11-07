package com.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String errorMessage;
    private String errorCode;
    private Date timeStamp;
}
