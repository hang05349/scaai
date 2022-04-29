package com.srwl.scaai.common;

import com.srwl.scaai.entity.Camera;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : [xieHang]
 * @version : [v1.0.0]
 * @date : [2022/3/24 11:22]
 * Description   : [通用常量]
 */
@Component
public class Constant implements InitializingBean {
    /**
     * Description: 餐餐安appid
     */
    public static String CCA_APPID;
    /**
     * Description: 餐餐安secret
     */
    public static String CCA_SECRET;
    /**
     * Description: 千里眼固定token
     */
    public static String QLY_TOKEN;
    /**
     * Description: 图片保存到本地的路径
     */
    public static String FILE_DOWNLOAD_DIR;
    /**
     * Description:图片保存时间,单位:天
     */
    public static int FILE_SAVE_DAY;


    /**
     * Description: QLY_CCA设备号对应关系,key为千里眼中摄像头id,value摄像头信息对象
     */
    public static Map<String, Camera> QLY_CCA = new HashMap<>();
    /**
     * Description: 计划列表
     */
    public static List<String> QLY_PLAN = new ArrayList<>();

    //初始化信息
    static {
        //明厨计划-厨房
        QLY_PLAN.add("f8b8639b6e4c766275819d6d7ea261a4");
        //明厨计划-仓库,暂时只设置两个计划,后面根据需要创建新计划
        QLY_PLAN.add("eb3c21d37e074c9e6458552abae5e49b");
        //对应关系
        QLY_CCA.put("452d6c38d53446eca5b8b82fd8696041", new Camera("27b09378efed4a338c4189fc2055e99b", 1, "452d6c38d53446eca5b8b82fd8696041"));
        QLY_CCA.put("65770045408947e183e0f67d81102dec", new Camera("27b09378efed4a338c4189fc2055e99b", 2, "65770045408947e183e0f67d81102dec"));
        QLY_CCA.put("0caffd82ec81440f996d014d429adf8b", new Camera("27b09378efed4a338c4189fc2055e99b", 3, "0caffd82ec81440f996d014d429adf8b"));
        QLY_CCA.put("e67be03b61354425814546623776b97e", new Camera("27b09378efed4a338c4189fc2055e99b", 4, "e67be03b61354425814546623776b97e"));
        QLY_CCA.put("3abd45c43adf42388d15596abee8eb84", new Camera("27b09378efed4a338c4189fc2055e99b", 5, "3abd45c43adf42388d15596abee8eb84"));
        QLY_CCA.put("06e632b37a2a4a6f8bfcb965af94a913", new Camera("27b09378efed4a338c4189fc2055e99b", 6, "06e632b37a2a4a6f8bfcb965af94a913"));
        QLY_CCA.put("cfda9f0c4d1d4d329c2beeb4811bdd44", new Camera("27b09378efed4a338c4189fc2055e99b", 7, "cfda9f0c4d1d4d329c2beeb4811bdd44"));
        QLY_CCA.put("f888dc7883964e8f8b560c6ce543d6cc", new Camera("27b09378efed4a338c4189fc2055e99b", 8, "f888dc7883964e8f8b560c6ce543d6cc"));
        QLY_CCA.put("6797597a3ac34501bde5c986e9bdeca0", new Camera("27b09378efed4a338c4189fc2055e99b", 9, "6797597a3ac34501bde5c986e9bdeca0"));
        QLY_CCA.put("2f604f02370a45df82540a8cffcf4058", new Camera("27b09378efed4a338c4189fc2055e99b", 10, "2f604f02370a45df82540a8cffcf4058"));
        QLY_CCA.put("be2005230ed442a5ab0c1b693409c2a8", new Camera("27b09378efed4a338c4189fc2055e99b", 11, "be2005230ed442a5ab0c1b693409c2a8"));
        QLY_CCA.put("38a2af624b1b441589f994cb8cd54583", new Camera("5444dc0aee1e4ce69ad3d5c922131404", 9, "38a2af624b1b441589f994cb8cd54583"));
        QLY_CCA.put("d8d3833baaf24886a7fb1df6e6c0b514", new Camera("5444dc0aee1e4ce69ad3d5c922131404", 1, "d8d3833baaf24886a7fb1df6e6c0b514"));
        QLY_CCA.put("e6d3d47abbab4797835b24e99987faf2", new Camera("5444dc0aee1e4ce69ad3d5c922131404", 2, "e6d3d47abbab4797835b24e99987faf2"));
        QLY_CCA.put("009af75f9f9f45d195dfed0f48e6ea1f", new Camera("5444dc0aee1e4ce69ad3d5c922131404", 3, "009af75f9f9f45d195dfed0f48e6ea1f"));
        QLY_CCA.put("e268686c1c7343288de2bd4bd5409565", new Camera("5444dc0aee1e4ce69ad3d5c922131404", 4, "e268686c1c7343288de2bd4bd5409565"));
        QLY_CCA.put("90dae99a126640bca01f47a011047746", new Camera("5444dc0aee1e4ce69ad3d5c922131404", 5, "90dae99a126640bca01f47a011047746"));
        QLY_CCA.put("d0b8431b44844be5bc66bf12d00d0874", new Camera("5444dc0aee1e4ce69ad3d5c922131404", 6, "d0b8431b44844be5bc66bf12d00d0874"));
        QLY_CCA.put("cc18808f040843f08bfc24e6a6accad2", new Camera("5444dc0aee1e4ce69ad3d5c922131404", 7, "cc18808f040843f08bfc24e6a6accad2"));
        QLY_CCA.put("023fd8d3bb6f4d588a73701d4da8f470", new Camera("5444dc0aee1e4ce69ad3d5c922131404", 8, "023fd8d3bb6f4d588a73701d4da8f470"));
        QLY_CCA.put("78c58c4a5f52410ca841edcb28f21550", new Camera("ced1087c52224af093062814b1380768", 1, "78c58c4a5f52410ca841edcb28f21550"));
        QLY_CCA.put("1bbb917a63b3464f8b4e237eb334bc83", new Camera("ced1087c52224af093062814b1380768", 2, "1bbb917a63b3464f8b4e237eb334bc83"));
        QLY_CCA.put("321c65e697b5452daeef88da2deb2977", new Camera("ced1087c52224af093062814b1380768", 3, "321c65e697b5452daeef88da2deb2977"));
        QLY_CCA.put("7ee7682c3aac4d7fbc872ed77fa77fd9", new Camera("ced1087c52224af093062814b1380768", 4, "7ee7682c3aac4d7fbc872ed77fa77fd9"));
        QLY_CCA.put("17275cf3d5944f5e947b439ac04a0279", new Camera("ced1087c52224af093062814b1380768", 5, "17275cf3d5944f5e947b439ac04a0279"));
        QLY_CCA.put("797615dbfcfd4d5abaeee2028fe55862", new Camera("ced1087c52224af093062814b1380768", 6, "797615dbfcfd4d5abaeee2028fe55862"));
        QLY_CCA.put("becdc83b0b3d4f5489dbe50371ac97ce", new Camera("ced1087c52224af093062814b1380768", 7, "becdc83b0b3d4f5489dbe50371ac97ce"));
        QLY_CCA.put("5988ba9808be46acab201a5b4400d6aa", new Camera("ced1087c52224af093062814b1380768", 8, "5988ba9808be46acab201a5b4400d6aa"));
        QLY_CCA.put("05b6702327fc4ad3b2dd8b2af90ead34", new Camera("ced1087c52224af093062814b1380768", 9, "05b6702327fc4ad3b2dd8b2af90ead34"));
    }

    @Value("${CCA_APPID}")
    public void setCcaAppid(String ccaAppid) {
        CCA_APPID = ccaAppid;
    }

    @Value("${CCA_SECRET}")
    public void setCcaSecret(String ccaSecret) {
        CCA_SECRET = ccaSecret;
    }

    @Value("${QLY_TOKEN}")
    public void setQlyToken(String qlyToken) {
        QLY_TOKEN = qlyToken;
    }

    @Value("${file.download.dir}")
    public void setFileDownloadDir(String fileDownloadDir) {
        FILE_DOWNLOAD_DIR = fileDownloadDir;
    }

    @Value("${file.save.day}")
    public void setFileSaveDay(int day) {
        //取反
        FILE_SAVE_DAY = -day;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(QLY_TOKEN);
        System.out.println(CCA_APPID);
        System.out.println(CCA_SECRET);
        System.out.println(FILE_DOWNLOAD_DIR);
    }
}
