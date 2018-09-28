package io.renren.modules.oss.cloud;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import io.renren.common.exception.RRException;

import java.io.*;


/**
 * 百度云存储
 *
 * @author Admin
 * @date 2018-09-26 17:54
 */
public class BaiduCloudStorageService extends CloudStorageService {
    private BosClient client;

    public BaiduCloudStorageService(CloudStorageConfig config) {
        this.config = config;

        // 初始化
        init();
    }

    private void init() {
        // 初始化客户端配置
        BosClientConfiguration bosConfig = new BosClientConfiguration();
        bosConfig.setCredentials(new DefaultBceCredentials(config.getBaiduAccessKeyID(), config.getBaiduSecretAccessKey()));
        String baiduEndPoint = config.getBaiduEndPoint();
        if (baiduEndPoint != null && baiduEndPoint.length() > 0) {
            bosConfig.setEndpoint(baiduEndPoint);
        }
        client = new BosClient(bosConfig);
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        System.out.println(path);
        // 获取数据流
        try {
            inputStream = new FileInputStream("C:\\Users\\Admin\\Desktop\\下载测试页面\\BaiDuTest.png");
        } catch (Exception e) {
            throw new RRException("系统找不到指定的路径", e);
        }

        // String objectKey = new String();
        String objectKey = "123456";
        String bucketName = config.getBaiduBucketName();
        // 以数据流形式上传Object
        // try {
        //     client.putObject(bucketName, objectKey, inputStream);
        // } catch (Exception e) {
        //     throw new RRException("上传文件失败，请检查配置信息", e);
        // }
        client.putObject(bucketName, objectKey, inputStream);

        return config.getQcloudDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getBaiduPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getBaiduPrefix(), suffix));
    }
}
