package com.zerobase.cms.order.controller;

import com.zerobase.cms.order.domain.product.AddProductForm;
import com.zerobase.cms.order.domain.product.AddProductItemForm;
import com.zerobase.cms.order.domain.product.ProductDto;
import com.zerobase.cms.order.domain.product.ProductItemDto;
import com.zerobase.cms.order.domain.product.UpdateProductForm;
import com.zerobase.cms.order.domain.product.UpdateProductItemForm;
import com.zerobase.cms.order.service.ProductItemService;
import com.zerobase.cms.order.service.ProductService;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller/product")
@RequiredArgsConstructor
public class SellerProductController {

    private final ProductService productService;
    private final ProductItemService productItemService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestHeader("X-AUTH-TOKEN") String token,
        @RequestBody AddProductForm form) {

        return ResponseEntity.ok(ProductDto.from(
            productService.addProduct(jwtAuthenticationProvider.getUserVo(token).getId(), form)));
    }

    @PostMapping("/item")
    public ResponseEntity<ProductDto> addProductItem(@RequestHeader("X-AUTH-TOKEN") String token,
        @RequestBody AddProductItemForm form) {

        return ResponseEntity.ok(ProductDto.from(
            productItemService.addProductItem(jwtAuthenticationProvider.getUserVo(token).getId(), form)));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestHeader("X-AUTH-TOKEN") String token,
        @RequestBody UpdateProductForm form) {

        return ResponseEntity.ok(ProductDto.from(
            productService.updateProduct(jwtAuthenticationProvider.getUserVo(token).getId(), form)));
    }

    @PutMapping("/item")
    public ResponseEntity<ProductItemDto> updateProductItem(@RequestHeader("X-AUTH-TOKEN") String token,
        @RequestBody UpdateProductItemForm form) {

        return ResponseEntity.ok(ProductItemDto.from(
            productItemService.updateProductItem(jwtAuthenticationProvider.getUserVo(token).getId(), form)));
    }


}
