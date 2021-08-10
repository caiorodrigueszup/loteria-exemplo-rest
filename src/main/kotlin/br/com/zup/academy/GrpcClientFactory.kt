package br.com.zup.academy

import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClientFactory {

    @Singleton
    fun loteriaClientStub(@GrpcChannel("loteria") channel: ManagedChannel): LoteriaServiceGrpc.LoteriaServiceBlockingStub {
        return LoteriaServiceGrpc.newBlockingStub(channel)
    }
}