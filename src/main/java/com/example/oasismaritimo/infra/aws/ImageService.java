package com.example.oasismaritimo.infra.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Classe de serviço para lidar com operações de imagem com Amazon S3.
 */


@Service
public class ImageService {
    private AmazonS3 s3;

    @Value("${s3.region.name}")
    private String regionName;

    @Value("${access.key.id}")
    private String accessKeyId;

    @Value("${access.key.secret}")
    private String accessKeySecret;

    /**
     * Inicializa o cliente Amazon S3 com as credenciais AWS fornecidas.
     */
    @PostConstruct
    public void init() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
        this.s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(regionName)
                .build();
    }

    /**
     * Faz o upload de um arquivo de imagem para o bucket S3 e retorna a URL da imagem.
     *
     * @param image o arquivo de imagem a ser carregado.
     * @param bucketName o nome do bucket S3 onde a imagem será carregada.
     * @return A URL da imagem carregada.
     */
    public String uploadImage(File image, String bucketName) {
        String key = image.getName();
        s3.putObject(new PutObjectRequest(bucketName, key, image));
        return s3.getUrl(bucketName, key).toString();
    }
}