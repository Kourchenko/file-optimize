package com.kourchenko.fileoptimize;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import software.amazon.awssdk.core.SdkSystemSetting;

import org.apache.commons.lang3.StringUtils;

/**
 * The module containing all dependencies required by the {@link App}.
 */
public class DependencyFactory {

    private DependencyFactory() {}

    /**
     * @return an instance of S3Client
     */
    public static AmazonS3 s3Client(String defaultAccesskey, String defaultSecretKey,
            String defaultRegion) {
        String envAWSAccessKey = SdkSystemSetting.AWS_ACCESS_KEY_ID.environmentVariable();
        String accessKey =
                StringUtils.defaultIfBlank(defaultAccesskey, System.getenv(envAWSAccessKey));

        String envAWSSecretKey = SdkSystemSetting.AWS_SECRET_ACCESS_KEY.environmentVariable();
        String secretKey =
                StringUtils.defaultIfBlank(defaultSecretKey, System.getenv(envAWSSecretKey));

        String envRegion = SdkSystemSetting.AWS_REGION.environmentVariable();
        String region = StringUtils.defaultIfBlank(defaultRegion, System.getenv(envRegion));

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region)
                .build();
    }
}
