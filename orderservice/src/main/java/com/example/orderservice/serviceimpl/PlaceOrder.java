package com.example.orderservice.serviceimpl;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import com.example.product.grpc.*;

@Service
public class PlaceOrder {
    // The "label" here MUST match the name in application.yml
    @GrpcClient("product-service")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public void placeOrder (Long productId){
        GetProductRequest getProductRequest = GetProductRequest.newBuilder()
                .setProductId(productId)
                .build();

        // 2. Call it — looks exactly like a local method call!
        //    This BLOCKS until Product Service responds
        ProductResponse product = productServiceBlockingStub.getProduct(getProductRequest);

        // 3. Use the response normally
        System.out.println("Product name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Stock available: " + product.getStock());

        if (product.getStock()<1){
            throw new RuntimeException("Out Of Stock !!!!");
        }

        // Place the Order reduce the stock from the Product Table


    }
}
