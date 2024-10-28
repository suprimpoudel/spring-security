package com.np.suprimpoudel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.np.suprimpoudel.exception.SubError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Base<T> {
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SubError> errors;

    public Base(T data) {
        this.data = data;
    }
}

