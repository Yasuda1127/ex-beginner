package com.example.exbeginner.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UserForm {
    @NotBlank(message="名前を入力してください")
    private String name;

   @Min(value = 18, message = "年齢は18以上で入力してください")
    private int age;

    @NotBlank(message = "コメントを入力してください")
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
