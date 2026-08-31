package com.zeynep.eTicaretSitesi.service.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FileServiceClient {

    private final RestClient restClient;

    public FileServiceClient(
            @Value("${file-service.url}") String fileServiceUrl
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(fileServiceUrl)
                .build();
    }

//    public FileServiceResponse upload(MultipartFile file, String authorizationHeader) {
//
//        try {
//
//            ByteArrayResource resource =
//                    new ByteArrayResource(file.getBytes()) {
//
//                        @Override
//                        public String getFilename() {
//                            return file.getOriginalFilename();
//                        }
//                    };
//
//            MultiValueMap<String, Object> body =
//                    new LinkedMultiValueMap<>();
//
//            body.add("file", resource);
//
//            return restClient.post()
//                    .uri("/api/v1/upload")
//                    .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
//                    .contentType(MediaType.MULTIPART_FORM_DATA)
//                    .body(body)
//                    .retrieve()
//                    .body(FileServiceResponse.class);
//
//
//
//        } catch (IOException e) {
//
//            throw new RuntimeException(
//                    "Dosya File Service'e gönderilemedi.",
//                    e
//            );
//        }
//    }
//    public ResponseEntity<byte[]> download(String objectName) {
//
//        return restClient.get()
//                .uri("/api/v1/download/{objectName}", objectName)
//                .retrieve()
//                .toEntity(byte[].class);
//    }
}