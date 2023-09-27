package com.example.salonbella.service;

import com.example.salonbella.entity.OrderEntity;
import com.example.salonbella.entity.ProductEntity;
import com.example.salonbella.repository.OrderRepository;
import com.example.salonbella.repository.ProductRepository;
import com.example.salonbella.service.order.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public void addProduct(String name, String category, String price, String description, MultipartFile multipartFile) throws IOException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);
        productEntity.setContent(multipartFile.getBytes());
        productEntity.setDescription(description);
        productEntity.setCategory(category);
        productEntity.setPrice(Double.parseDouble(price));
        productRepository.save(productEntity);
    }

    public List<ProductEntity> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        for (ProductEntity p : productEntities) {
            p.setBase64(Base64.getEncoder().encodeToString(p.getContent()));
        }
        return productEntities;
    }

    public byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    @Transactional
    public void removeProduct(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        product.ifPresent(productRepository::delete);
    }
}
