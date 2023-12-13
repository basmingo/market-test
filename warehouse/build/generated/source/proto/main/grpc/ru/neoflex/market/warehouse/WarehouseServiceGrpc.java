package ru.neoflex.market.warehouse;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: WarehouseService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class WarehouseServiceGrpc {

  private WarehouseServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "warehouse.WarehouseService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest,
      ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse> getUpdateProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateProduct",
      requestType = ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest.class,
      responseType = ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest,
      ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse> getUpdateProductMethod() {
    io.grpc.MethodDescriptor<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest, ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse> getUpdateProductMethod;
    if ((getUpdateProductMethod = WarehouseServiceGrpc.getUpdateProductMethod) == null) {
      synchronized (WarehouseServiceGrpc.class) {
        if ((getUpdateProductMethod = WarehouseServiceGrpc.getUpdateProductMethod) == null) {
          WarehouseServiceGrpc.getUpdateProductMethod = getUpdateProductMethod =
              io.grpc.MethodDescriptor.<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest, ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WarehouseServiceMethodDescriptorSupplier("updateProduct"))
              .build();
        }
      }
    }
    return getUpdateProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest,
      ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse> getCreateProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createProduct",
      requestType = ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest.class,
      responseType = ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest,
      ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse> getCreateProductMethod() {
    io.grpc.MethodDescriptor<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest, ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse> getCreateProductMethod;
    if ((getCreateProductMethod = WarehouseServiceGrpc.getCreateProductMethod) == null) {
      synchronized (WarehouseServiceGrpc.class) {
        if ((getCreateProductMethod = WarehouseServiceGrpc.getCreateProductMethod) == null) {
          WarehouseServiceGrpc.getCreateProductMethod = getCreateProductMethod =
              io.grpc.MethodDescriptor.<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest, ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WarehouseServiceMethodDescriptorSupplier("createProduct"))
              .build();
        }
      }
    }
    return getCreateProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest,
      ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse> getDeleteProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteProduct",
      requestType = ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest.class,
      responseType = ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest,
      ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse> getDeleteProductMethod() {
    io.grpc.MethodDescriptor<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest, ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse> getDeleteProductMethod;
    if ((getDeleteProductMethod = WarehouseServiceGrpc.getDeleteProductMethod) == null) {
      synchronized (WarehouseServiceGrpc.class) {
        if ((getDeleteProductMethod = WarehouseServiceGrpc.getDeleteProductMethod) == null) {
          WarehouseServiceGrpc.getDeleteProductMethod = getDeleteProductMethod =
              io.grpc.MethodDescriptor.<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest, ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WarehouseServiceMethodDescriptorSupplier("deleteProduct"))
              .build();
        }
      }
    }
    return getDeleteProductMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WarehouseServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WarehouseServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WarehouseServiceStub>() {
        @java.lang.Override
        public WarehouseServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WarehouseServiceStub(channel, callOptions);
        }
      };
    return WarehouseServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WarehouseServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WarehouseServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WarehouseServiceBlockingStub>() {
        @java.lang.Override
        public WarehouseServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WarehouseServiceBlockingStub(channel, callOptions);
        }
      };
    return WarehouseServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WarehouseServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WarehouseServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WarehouseServiceFutureStub>() {
        @java.lang.Override
        public WarehouseServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WarehouseServiceFutureStub(channel, callOptions);
        }
      };
    return WarehouseServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void updateProduct(ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest request,
        io.grpc.stub.StreamObserver<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateProductMethod(), responseObserver);
    }

    /**
     */
    default void createProduct(ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest request,
        io.grpc.stub.StreamObserver<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateProductMethod(), responseObserver);
    }

    /**
     */
    default void deleteProduct(ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest request,
        io.grpc.stub.StreamObserver<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteProductMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service WarehouseService.
   */
  public static abstract class WarehouseServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return WarehouseServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service WarehouseService.
   */
  public static final class WarehouseServiceStub
      extends io.grpc.stub.AbstractAsyncStub<WarehouseServiceStub> {
    private WarehouseServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WarehouseServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WarehouseServiceStub(channel, callOptions);
    }

    /**
     */
    public void updateProduct(ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest request,
        io.grpc.stub.StreamObserver<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createProduct(ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest request,
        io.grpc.stub.StreamObserver<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteProduct(ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest request,
        io.grpc.stub.StreamObserver<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteProductMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service WarehouseService.
   */
  public static final class WarehouseServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<WarehouseServiceBlockingStub> {
    private WarehouseServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WarehouseServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WarehouseServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse updateProduct(ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse createProduct(ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse deleteProduct(ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteProductMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service WarehouseService.
   */
  public static final class WarehouseServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<WarehouseServiceFutureStub> {
    private WarehouseServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WarehouseServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WarehouseServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse> updateProduct(
        ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse> createProduct(
        ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse> deleteProduct(
        ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteProductMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPDATE_PRODUCT = 0;
  private static final int METHODID_CREATE_PRODUCT = 1;
  private static final int METHODID_DELETE_PRODUCT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPDATE_PRODUCT:
          serviceImpl.updateProduct((ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest) request,
              (io.grpc.stub.StreamObserver<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse>) responseObserver);
          break;
        case METHODID_CREATE_PRODUCT:
          serviceImpl.createProduct((ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest) request,
              (io.grpc.stub.StreamObserver<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse>) responseObserver);
          break;
        case METHODID_DELETE_PRODUCT:
          serviceImpl.deleteProduct((ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest) request,
              (io.grpc.stub.StreamObserver<ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getUpdateProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateRequest,
              ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductStatusUpdateResponse>(
                service, METHODID_UPDATE_PRODUCT)))
        .addMethod(
          getCreateProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest,
              ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateResponse>(
                service, METHODID_CREATE_PRODUCT)))
        .addMethod(
          getDeleteProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteRequest,
              ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductDeleteResponse>(
                service, METHODID_DELETE_PRODUCT)))
        .build();
  }

  private static abstract class WarehouseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WarehouseServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.neoflex.market.warehouse.WarehouseServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WarehouseService");
    }
  }

  private static final class WarehouseServiceFileDescriptorSupplier
      extends WarehouseServiceBaseDescriptorSupplier {
    WarehouseServiceFileDescriptorSupplier() {}
  }

  private static final class WarehouseServiceMethodDescriptorSupplier
      extends WarehouseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    WarehouseServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (WarehouseServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WarehouseServiceFileDescriptorSupplier())
              .addMethod(getUpdateProductMethod())
              .addMethod(getCreateProductMethod())
              .addMethod(getDeleteProductMethod())
              .build();
        }
      }
    }
    return result;
  }
}
