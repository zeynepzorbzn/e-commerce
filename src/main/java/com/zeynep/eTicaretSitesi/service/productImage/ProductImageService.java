package com.zeynep.eTicaretSitesi.service.productImage;

import com.zeynep.eTicaretSitesi.logic.product.ProductLogic;
import com.zeynep.eTicaretSitesi.mapper.productImage.ProductImageMapper;
import com.zeynep.eTicaretSitesi.repo.productImage.ProductImageRepository;
import com.zeynep.eTicaretSitesi.service.file.FileServiceClient;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;
    private final ProductLogic productLogic;
    private final FileServiceClient fileServiceClient;

    public ProductImageService(
            ProductImageRepository productImageRepository,
            ProductImageMapper productImageMapper,
            ProductLogic productLogic,
            FileServiceClient fileServiceClient
    ) {
        this.productImageRepository = productImageRepository;
        this.productImageMapper = productImageMapper;
        this.productLogic = productLogic;
        this.fileServiceClient = fileServiceClient;
    }

//    public ProductImageResponse uploadImage(
//            Long productId,
//            MultipartFile file,
//            String authorizationHeader
//    )
//    {
//
//        // 1. Ürünü bul
//        Product product = productLogic.findById(productId)
//                .orElseThrow(() ->
//                        new RuntimeException("Ürün bulunamadı.")
//                );
//
//        // 2. Dosyanın boş olup olmadığını kontrol et
//        if (file == null || file.isEmpty()) {
//            throw new RuntimeException("Yüklenecek dosya bulunamadı.");
//        }
//
//        // 3. Sadece image kabul et
//        String contentType = file.getContentType();
//
//        if (contentType == null || !contentType.startsWith("image/")) {
//            throw new RuntimeException(
//                    "Sadece image türündeki dosyalar yüklenebilir."
//            );
//        }
//
//        // 4. File Service'e gönder
//        FileServiceResponse fileResponse =
//                fileServiceClient.upload(file, authorizationHeader);
//
//        // 5. File Service'in döndürdüğü token'ı al
//        String imageToken = fileResponse.getObjectName();
//
//        // 6. ProductImage oluştur
//        ProductImage productImage = new ProductImage();
//
//        productImage.setImageToken(imageToken);
//        productImage.setProduct(product);
//
//        // 7. Database'e kaydet
//        ProductImage savedImage =
//                productImageRepository.save(productImage);
//
//        // 8. Response'a çevir
//        return productImageMapper.toResponse(savedImage);
//    }

//    public ResponseEntity<byte[]> downloadImage(String imageToken) {
//
//        ResponseEntity<byte[]> response =
//                fileServiceClient.download(imageToken);
//
//        return ResponseEntity
//                .status(response.getStatusCode())
//                .headers(response.getHeaders())
//                .body(response.getBody());
//    }
}