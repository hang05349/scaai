package com.srwl.scaai.main;

import com.srwl.scaai.common.Constant;
import com.srwl.scaai.entity.Breach;
import com.srwl.scaai.entity.CallBack;
import com.srwl.scaai.entity.Camera;
import com.srwl.scaai.entity.Point;
import com.srwl.scaai.utils.AccessUtil;
import com.srwl.scaai.utils.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : [xieHang]
 * @version : [v1.0.0]
 * @date : [2022/3/25 14:39]
 * Description   : [程序入口,定时执行任务]
 */
@Configuration
@EnableScheduling   //开启定时任务
// @EnableAsync        //开启多线程
public class SchedulingConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // @Async
    @Scheduled(cron = "0 0 0,12 * * ? ") // 定时执行
    public void scheduler1() throws IOException {
        logger.info("========启动定时任务===========");
        //构建请求参数,获取AI违规对象信息
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.minusHours(12);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd%20HH:mm:ss");
        String startTime = formatter.format(from);
        String endTime = formatter.format(now);
        intermediator(startTime,endTime);
        logger.info("========定时任务结束===========");
    }

    /**
     *Description: 查询黑晶 ai违规数据、处理后上传到餐餐安
     *@author [xieHang]
     *@date [2022/4/19 17:56]
     * @param startTime 查询黑晶数据的开始时间
     * @param endTime 查询黑晶数据的结束时间
     */
    private void intermediator(String startTime,String endTime) throws IOException {
        for (String plan : Constant.QLY_PLAN) {
            List<Breach> breaches = AccessUtil.getAIData("www.baidu.com", plan, Constant.QLY_TOKEN, startTime, endTime);
            List<CallBack> callBackList = objectReplace(breaches);
            for (CallBack callBack : callBackList) {
                boolean state = AccessUtil.callBack("www.baidu.com", callBack);
                logger.info("AI数据上传接口上传结果为:{},上传信息为: {} ", state, callBack.toString());
            }
        }
    }

    /**
     * Description: QLY AI违规对象 转换为CCA ai违规上传对象
     *
     * @param breaches QLY AI违规对象
     * @return List<CallBack>
     * @author [xieHang]
     * @date [2022/4/18 16:01]
     */
    public static List<CallBack> objectReplace(List<Breach> breaches) throws IOException {
        List<CallBack> callBackList = new ArrayList<>();
        for (Breach breach : breaches) {
            //找到摄像头对应关系
            Map<String, Camera> qlyCca = Constant.QLY_CCA;
            Camera camera = qlyCca.get(breach.getCAMERA_ID());
            //保存图片到本地,使用指定后缀名
            ImageUtil.downloadImage(breach.getILLEGAL_CAPTURE(), ".jpg");
            //Base64解码图片
            String imgageToBase64 = ImageUtil.encodeImgageToBase64(new URL(breach.getILLEGAL_CAPTURE()));
            //违规项结果转换,活物检测关键词暂时不清楚,如果没有遇到转换词直接原样传过去。
            String illegal_result = breach.getILLEGAL_RESULT();
            if (illegal_result.contains("帽子")) {
                illegal_result = "nohat";
            } else if (illegal_result.contains("口罩")) {
                illegal_result = "nomask";
            } else if (illegal_result.contains("手套")) {
                illegal_result = "noglove";
            } else if (illegal_result.contains("烟")) {
                illegal_result = "smoke";
            } else if (illegal_result.contains("手机")) {
                illegal_result = "phone";
            }
            //  else if (illegal_result.contains("垃圾桶")) {
            //     illegal_result = "nohat";
            // } else if (illegal_result.contains("赤裸")) {
            //     illegal_result = "nohat";
            // }
            Point point = new Point(illegal_result, 0, 0, 0, 0);
            List<Point> list = new ArrayList<>();
            list.add(point);
            CallBack callBack = new CallBack(imgageToBase64, list, camera.getCCAPid(), camera.getCCACid());
            callBackList.add(callBack);
        }
        return callBackList;
    }
}
