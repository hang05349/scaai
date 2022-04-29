package com.srwl.scaai.entity;

import lombok.Data;

/**
 * @author : [xieHang]
 * @version : [v1.0.0]
 * @date : [2022/3/28 15:35]
 * Description   : [餐餐安上传AI数据所需的违规坐标集实体类]
 */
@Data
public class Point {
    private String label;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Point(String label, int x1, int y1, int x2, int y2) {
        this.label = label;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}
