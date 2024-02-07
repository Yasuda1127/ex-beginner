package com.example.exbeginner.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserForm {
    @NotBlank(message = "名前を入力してください")
    private String name;

    @NotNull(message = "年齢を入力してください")
    // @Min(value = 18, message = "年齢は18以上で入力してください")
    private Integer age;

    @NotBlank(message = "コメントを入力してください")
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
