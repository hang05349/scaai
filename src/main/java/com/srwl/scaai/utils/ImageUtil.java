package com.srwl.scaai.utils;

import com.srwl.scaai.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * @author : [xieHang]
 * @version : [v1.0.0]
 * @date : [2022/3/24 8:48]
 * Description   : []
 */
public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将网络图片进行Base64位编码
     *
     * @param imageUrl 图片的url路径，如http://.....xx.jpg
     * @return 解码结果
     */
    public static String encodeImgageToBase64(URL imageUrl) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(outputStream.toByteArray());
    }

    /**
     * Description: 将本地图片进行Base64解码
     *
     * @param imageFile 图片文件
     * @return String
     */
    public static String encodeImgageToBase64(File imageFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(outputStream.toByteArray());
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64 base64编码的图片信息
     */
    public static void decodeBase64ToImage(String base64, String path,
                                           String imgName) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            FileOutputStream write = new FileOutputStream(new File(path
                    + imgName));
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Description: 将图片url按照uuid命名后下载保存到指定目录,并检查路径下是否有指定日期前的文件夹,存在则删除。
     *
     * @param imageUrl 图片url
     * @param suffix 后缀名,如果指定就用指定,否则截取url最后一个"."后的内容作为后缀名
     * @author [xieHang]
     * @date [2022/3/25 17:09]
     */
    public static void downloadImage(String imageUrl,String ...suffix) throws IOException {
        DataInputStream dis = null;
        FileOutputStream fos = null;
        String suffixName = null;
        try {
            URL url = new URL(imageUrl);
            //后缀名
            if(suffix.length==0){
                 suffixName = imageUrl.substring(imageUrl.lastIndexOf("."));
            }else{
                suffixName = suffix[0];
            }
            //使用uuid+后缀作为文件名
            String fileName = UUID.randomUUID() + suffixName;
            //获取当天日期作为文件名存放图片
            LocalDate date = LocalDate.now();
            LocalDate toDay = date.plusDays(Constant.FILE_SAVE_DAY);
            String saveDir = Constant.FILE_DOWNLOAD_DIR + date + "/";
            // 获取设置保存时限的日期,如果存在命名为该日期的文件夹,则删除
            File deleteDir = new File(Constant.FILE_DOWNLOAD_DIR + toDay);
            if (FileSystemUtils.deleteRecursively(deleteDir)) {
                logger.info("已删除{}天前的图片文件,文件路径为{}", -Constant.FILE_SAVE_DAY, deleteDir);
        }
            //创建文件:1.获取文件存放目录,获取目标文件夹
            //2.判断目标文件夹是否存在,若不存在,则新建文件夹。
            File fileDirectory = new File(saveDir);
            File destFile = new File(saveDir + fileName);
            if (!fileDirectory.exists()) {
                //新建目标文件夹,如果新建后还是没有,抛出异常
                if (!fileDirectory.mkdirs()) {
                    logger.error("创建文件夹失败!");
                    throw new RuntimeException("文件夹创建失败");
                }
            }
            dis = new DataInputStream(url.openStream());
            fos = new FileOutputStream(destFile);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fos.write(output.toByteArray());
            logger.info("url为:{} 的图片成功保存到本地,文件名为{}", url, fileName);
        } finally {
            assert dis != null;
            assert fos != null;
            dis.close();
            fos.close();
        }


    }
}
