package com.project.kindergartenbe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
