package com.srwl.scaai.utils;


import com.srwl.scaai.common.Constant;
import org.springframework.util.DigestUtils;

import java.util.*;

/**
 * @author : [xieHang]
 * @version : [v1.0.0]
 * @date : [2022/3/23 16:40]
 * Description   : [签名工具类]
 */
public class SignatureUtil {
    /**
     * @return 获取时间戳
     */
    public static String getTimesTamp() {
        long times = System.currentTimeMillis();
        return Long.toString(times);
    }

    /**
     * @return 获取随机数
     */
    public static String getRandom() {
        Random random = new Random();
        int rdNum = random.nextInt(Integer.MAX_VALUE);
        return Integer.toString(rdNum);
    }

    /**
     * Description: 对参数进行进行拼接并返回
     *
     * @param params 参数map集合
     * @return String 拼接后的字符串
     * @author [xieHang]
     * @date [2022/3/24 11:33]
     */
    public static String sortMap(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        String key;
        String value;
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            key = entry.getKey();
            value = entry.getValue();
            sb.append("&" + key + "=" + value);
        }
        //删除第一个的&
        sb.deleteCharAt(0);
        return sb.toString();
    }

    /**
     * Description:将所有请求参数根据key值字母升序排列以后拼接,在最后加上token 并使用md5加密,并转换为大写。
     *
     * @return String 加密大写后的字符串
     * @author [xieHang]
     * @date [2022/3/23 17:33]
     */
    public static String getSignature(String params, String token) {
        String result = params + "&token=" + token;
        //md5加密后转换为大写
        return DigestUtils.md5DigestAsHex(result.getBytes()).toUpperCase();
    }

    /**
     * Description:公共参数生成,返回map集合，请求接口时使用
     *
     * @param token -token  map-参数集合
     * @return String 拼接字符串
     * @author [xieHang]
     * @date [2022/3/24 11:38]
     */
    public static Map<String, String> getParams(String token) {
        Map<String,String> map = new HashMap<>();
        String times = getTimesTamp();
        String random = getRandom();
        map.put("timestamp", times);
        map.put("nonce", random);
        String sign = sortMap(map);
        //获取签名
        String signature = SignatureUtil.getSignature(sign, token);
        map.put("signature", signature);
        map.put("appid",Constant.CCA_APPID);
        //获取参数字符串
        String params = sortMap(map);
        map.put("params",params);
        return map;
    }
    /**
     *Description:获取餐餐安token
     *@author [xieHang]
     *@date [2022/3/24 22:15]
     * @return Token
     */
    public static String getCCAToken(){
        String params = "appid="+Constant.CCA_APPID+"&secret="+Constant.CCA_SECRET;
        String url = "http://job.gxsca.cn/open_api/auth/token";
        String result =  HttpClientUtil.sendGet(url,params);
        int start, end = -1;
        start = result.indexOf("access_token");
        if (start != -1) {
            start = result.indexOf(":",start)+1;
            end = result.indexOf(",", start);
        }
        return result.substring(start,end);
    }
}
