package com.srwl.scaai.entity;

import lombok.Data;

/**
 * @author : [xieHang]
 * @version : [v1.0.0]
 * @date : [2022/3/28 8:59]
 * Description   : [摄像头实体类,对应摄像头在两个平台的具体id]
 */
@Data
public class Camera {
    /**
     *Description:摄像头对应餐餐安平台pid(设备编号)
    */
    private String CCAPid;
    /**
     *Description:摄像头对应餐餐安平台cid(设备通道号)
     */
    private Integer CCACid;
    /**
     *Description:摄像头对应千里眼平台cameraId
     */
    private String CameraId;

    public Camera(String CCAPid, Integer CCACid, String cameraId) {
        this.CCAPid = CCAPid;
        this.CCACid = CCACid;
        CameraId = cameraId;
    }
}
