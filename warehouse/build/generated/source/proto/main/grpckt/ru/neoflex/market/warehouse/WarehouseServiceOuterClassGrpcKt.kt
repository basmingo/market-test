package ru.neoflex.market.warehouse

import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import ru.neoflex.market.warehouse.WarehouseServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for warehouse.WarehouseService.
 */
public object WarehouseServiceGrpcKt {
  public const val SERVICE_NAME: String = WarehouseServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val updateProductMethod:
      MethodDescriptor<WarehouseServiceOuterClass.ProductStatusUpdateRequest, WarehouseServiceOuterClass.ProductStatusUpdateResponse>
    @JvmStatic
    get() = WarehouseServiceGrpc.getUpdateProductMethod()

  public val createProductMethod:
      MethodDescriptor<WarehouseServiceOuterClass.ProductCreateRequest, WarehouseServiceOuterClass.ProductCreateResponse>
    @JvmStatic
    get() = WarehouseServiceGrpc.getCreateProductMethod()

  public val deleteProductMethod:
      MethodDescriptor<WarehouseServiceOuterClass.ProductDeleteRequest, WarehouseServiceOuterClass.ProductDeleteResponse>
    @JvmStatic
    get() = WarehouseServiceGrpc.getDeleteProductMethod()

  /**
   * A stub for issuing RPCs to a(n) warehouse.WarehouseService service as suspending coroutines.
   */
  @StubFor(WarehouseServiceGrpc::class)
  public class WarehouseServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<WarehouseServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): WarehouseServiceCoroutineStub =
        WarehouseServiceCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun updateProduct(request: WarehouseServiceOuterClass.ProductStatusUpdateRequest,
        headers: Metadata = Metadata()): WarehouseServiceOuterClass.ProductStatusUpdateResponse =
        unaryRpc(
      channel,
      WarehouseServiceGrpc.getUpdateProductMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun createProduct(request: WarehouseServiceOuterClass.ProductCreateRequest,
        headers: Metadata = Metadata()): WarehouseServiceOuterClass.ProductCreateResponse =
        unaryRpc(
      channel,
      WarehouseServiceGrpc.getCreateProductMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun deleteProduct(request: WarehouseServiceOuterClass.ProductDeleteRequest,
        headers: Metadata = Metadata()): WarehouseServiceOuterClass.ProductDeleteResponse =
        unaryRpc(
      channel,
      WarehouseServiceGrpc.getDeleteProductMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the warehouse.WarehouseService service based on Kotlin coroutines.
   */
  public abstract class WarehouseServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for warehouse.WarehouseService.updateProduct.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend
        fun updateProduct(request: WarehouseServiceOuterClass.ProductStatusUpdateRequest):
        WarehouseServiceOuterClass.ProductStatusUpdateResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method warehouse.WarehouseService.updateProduct is unimplemented"))

    /**
     * Returns the response to an RPC for warehouse.WarehouseService.createProduct.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun createProduct(request: WarehouseServiceOuterClass.ProductCreateRequest):
        WarehouseServiceOuterClass.ProductCreateResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method warehouse.WarehouseService.createProduct is unimplemented"))

    /**
     * Returns the response to an RPC for warehouse.WarehouseService.deleteProduct.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun deleteProduct(request: WarehouseServiceOuterClass.ProductDeleteRequest):
        WarehouseServiceOuterClass.ProductDeleteResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method warehouse.WarehouseService.deleteProduct is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = WarehouseServiceGrpc.getUpdateProductMethod(),
      implementation = ::updateProduct
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = WarehouseServiceGrpc.getCreateProductMethod(),
      implementation = ::createProduct
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = WarehouseServiceGrpc.getDeleteProductMethod(),
      implementation = ::deleteProduct
    )).build()
  }
}
