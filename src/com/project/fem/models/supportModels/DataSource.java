package com.project.fem.models.supportModels;

import lombok.Getter;

@Getter
public enum DataSource {
    TEST_CASE1(System.getProperty("user.dir") + "\\src\\com\\project\\fem\\resources\\testCase1.txt"),
    TEST_CASE2(System.getProperty("user.dir") + "\\src\\com\\project\\fem\\resources\\testCase2.txt");

    private String filePath;

    DataSource(String filePath) {
        this.filePath = filePath;
    }
}
