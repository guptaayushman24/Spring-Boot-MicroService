package com.example.productservice.serviceImpl;

import com.example.product.grpc.GetProductRequest;
import com.example.product.grpc.ProductResponse;
import com.example.product.grpc.ProductServiceGrpc;
import com.example.productservice.model.ProductModel;
import com.example.productservice.repository.ProductRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class ProductGrpcService extends ProductServiceGrpc.ProductServiceImplBase{
    private final ProductRepository productRepository;

    @Override
    public void getProduct (GetProductRequest getProductRequest, StreamObserver<ProductResponse> responseStreamObserver){
        // Extract the productId from the incomming request
        ProductModel productDetail = productRepository.findById(getProductRequest.getProductId()).orElse(null);

        if (productDetail==null){
            // If not found, send a gRPC error back
            responseStreamObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Product with id " + getProductRequest.getProductId() + " not found")
                            .asRuntimeException()
            );
            return;
        }

        // Build the response using the generated Builder pattern
        ProductResponse response = ProductResponse.newBuilder()
                .setId(productDetail.getId())
                .setName(productDetail.getName())
                .setPrice(productDetail.getPrice())
                .setStock(productDetail.getStock())
                .build();

        // Send the response back
        responseStreamObserver.onNext(response);

        // Tell gRPC "I'm done, no more messages coming"
        responseStreamObserver.onCompleted();


    }
}
