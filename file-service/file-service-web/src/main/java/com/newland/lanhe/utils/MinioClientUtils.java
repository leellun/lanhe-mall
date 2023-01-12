package com.newland.lanhe.utils;

import com.newland.lanhe.properties.MinioProperties;
import io.minio.*;
import io.minio.http.Method;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.*;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Author: leell
 * Date: 2023/1/12 20:41:48
 */
@Component
public class MinioClientUtils implements InitializingBean {
    @Autowired
    private MinioProperties minioProperties;
    private MinioClient minioClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        minioClient = MinioClient.builder()
                .httpClient(getUnsafeOkHttpsClient())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(minioProperties.getUrl())
                .build();
    }

    /**
     * @param objectName 文件名称
     * @return url
     * @Description 获取文件外链
     */
    public String getObjectURL(String objectName) throws Exception {
        return getObjectURL(objectName, 7);
    }

    /**
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     * @Description 获取文件外链
     */
    public String getObjectURL(String objectName, Integer expires) throws Exception {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(minioProperties.getBucketName()).method(Method.GET).object(objectName).expiry(expires).build());
    }

    /**
     * @param objectName 文件名称
     * @return 二进制流
     * @Description 获取文件
     */

    public InputStream getObject(String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder().bucket(minioProperties.getBucketName()).object(objectName).build());
    }

    /**
     * @param objectName 文件名称
     * @return 二进制流
     * @Description 获取文件
     */

    public InputStream getObject(String objectName, long offset, Long length) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder().bucket(minioProperties.getBucketName()).object(objectName).offset(offset).length(length).build());
    }


    /**
     * @param objectName 文件名称
     * @return url
     * @Description 获取文件访问地址(有效期)
     */
    public String getPresignedObjectUrl(String objectName) throws Exception {
        return getPresignedObjectUrl(objectName, null);
    }

    /**
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     * @Description 获取文件访问地址(有效期)
     */
    public String getPresignedObjectUrl(String objectName, Integer expires) throws Exception {
        GetPresignedObjectUrlArgs.Builder builder = GetPresignedObjectUrlArgs.builder().bucket(minioProperties.getBucketName()).object(objectName).method(Method.GET);
        if (expires != null) {
            builder.expiry(expires);
        }
        return minioClient.getPresignedObjectUrl(builder.build());
    }

    /**
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     * @Description 删除文件
     */
    public void removeObject(String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(minioProperties.getBucketName()).object(objectName).build());
    }

    public String putObject(String objectName, MultipartFile file) throws Exception {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(objectName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        minioClient.putObject(args);
        return objectName;
    }

    public OkHttpClient getUnsafeOkHttpsClient() throws KeyManagementException {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };


            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            });


            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            return builder.build();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
