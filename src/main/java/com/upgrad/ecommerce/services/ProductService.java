package com.upgrad.ecommerce.services;

import com.upgrad.ecommerce.dto.ProductDTO;
import com.upgrad.ecommerce.models.Product;
import com.upgrad.ecommerce.repositories.ProductRepository;
import com.upgrad.ecommerce.utils.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        final List<Product> products = productRepository.findAll(Sort.by("id"));
        return products.stream()
                .map((product) -> mapToDTO(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    public ProductDTO get(final String id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final String id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final String id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setManufacturer(product.getManufacturer());
        productDTO.setAvailableItems(product.getAvailableItems());
        productDTO.setImageUrl(product.getImageUrl());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setManufacturer(productDTO.getManufacturer());
        product.setAvailableItems(productDTO.getAvailableItems());
        product.setImageUrl(productDTO.getImageUrl());
        return product;
    }

}
