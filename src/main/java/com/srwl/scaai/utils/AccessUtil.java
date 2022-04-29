package com.srwl.scaai.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srwl.scaai.entity.Breach;
import com.srwl.scaai.entity.CallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author : [xieHang]
 * @version : [v1.0.0]
 * @date : [2022/3/24 22:14]
 * Description   : [访问工具类]
 */
public class AccessUtil {

    /**
     * Description: 获取千里眼违规信息对象
     *
     * @param url    ai违规接口链接
     * @param planId 计划id
     * @return List<Breach>
     * @author [xieHang]
     * @date [2022/3/25 15:47]
     */
    public static List<Breach> getAIData(String url, String planId, String token, String startTime, String endTime) {
        Map<String, String> params = new HashMap<>();
        params.put("token",token);
        params.put("PATROL_PLAN_ID",planId);
        params.put("START_DATE",startTime);
        params.put("ENT_DATE",endTime);
        url = url+"?"+SignatureUtil.sortMap(params);
        String result = HttpClientUtil.sendPostByJson(url, "", 5);
        //处理响应结果、对应成多个Breach对象;
        JSONObject rs = JSONObject.parseObject(result);
        String data = rs.getString("data");
        return JSONArray.parseArray(data, Breach.class);
    }

    /**
     * Description: 回传餐餐安违规对象信息
     *
     * @param url      餐餐安ai上传地址
     * @param callBack 餐餐安ai违规对象
     * @return boolean
     * @author [xieHang]
     * @date [2022/3/25 15:49]
     */
    public static boolean callBack(String url, CallBack callBack) {
        String params = JSONObject.toJSONString(callBack);
        String result = HttpClientUtil.sendPostByJson(url, params, 5);
        //对返回结果进行判断,返回是否成功。
        return result.contains("success");
    }

}
