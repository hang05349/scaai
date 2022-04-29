package com.srwl.scaai.entity;

import lombok.Data;

/**
 * @author : [xieHang]
 * @version : [v1.0.0]
 * @date : [2022/3/25 10:27]
 * Description   : [违规信息实体类]
 */
@Data
public class Breach {
    //摄像头ID
    private String CAMERA_ID;
    // 企业ID
    private String ENT_ID ;
    // 企业名称(学校名称)
    private String ENT_NAME ;
    // 违规结果
    private String ILLEGAL_RESULT ;
    // 违规时间
    private String ILLEGAL_TIME ;
    // 巡查计划ID
    private String PATROL_PLAN_ID ;
    // 巡查计划周期ID
    private String PLAN_CYCLE_ID ;
    // 视频播放路径（当违规结果为活物时才会有值）
    private String ILLEGAL_VIDEO ;
    // 违规图片播放路径
    private String ILLEGAL_CAPTURE ;
    // 违规结果ID
    private String IDENTIFICATION_ID ;
    // 原图片播放路径
    private String ORIGINAL_CAPTURE ;
}
