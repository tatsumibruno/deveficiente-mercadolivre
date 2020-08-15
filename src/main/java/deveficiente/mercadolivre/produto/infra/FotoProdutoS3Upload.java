package deveficiente.mercadolivre.produto.infra;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import deveficiente.mercadolivre.produto.dominio.FotoProdutoUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class FotoProdutoS3Upload implements FotoProdutoUpload {

    @Value("${aws.access-key}")
    private String accessKey;
    @Value("${aws.secret-key}")
    private String secretKey;

    @Override
    public String gerarLinkNovaFoto(String titulo) {
        Regions clientRegion = Regions.US_EAST_1;
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(clientRegion)
                .build();

        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1_000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        String nomeArquivo = String.format("%s-%s", titulo, System.currentTimeMillis());
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest("deveficiente", nomeArquivo)
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration);

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
