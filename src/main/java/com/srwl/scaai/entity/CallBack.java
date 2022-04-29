package com.srwl.scaai.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : [xieHang]
 * @version : [v1.0.0]
 * @date : [2022/3/25 11:10]
 * Description   : [Ai数据上传接口对象实体类]
 */
@Data
public class CallBack {
    //违规图片base64
    private String pic;
    //ai违规标注点坐标
    private List<Point> point;
    //设备编号
    private String pid;
    //视频通道号
    private Integer cid;

    public CallBack(String pic, List<Point> point, String pid, Integer cid) {
        this.pic = pic;
        this.point = point;
        this.pid = pid;
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "CallBack{" +
                "point=" + point +
                ", pid='" + pid + '\'' +
                ", cid=" + cid +
                '}';
    }
}
