package com.team13.todolist.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("column")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ColumnParameter {

    private String name;

    public ColumnParameter() {
    }

    public ColumnParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
