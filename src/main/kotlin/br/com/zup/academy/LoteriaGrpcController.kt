package br.com.zup.academy

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Validated
@Controller("/api/loteria")
class LoteriaGrpcController(@Inject val gRpcClient: LoteriaServiceGrpc.LoteriaServiceBlockingStub) {

    @Post
    fun pegarNumeros(@Body @Valid loteriaForm: LoteriaForm): LoteriaFormResponse {
        val request = loteriaForm.toLoteriaRequest()
        val response = gRpcClient.sortear(request)

        return LoteriaFormResponse(nome = response.nome, email = loteriaForm.email, numeros = response.numeros)
    }

}

@Introspected
data class LoteriaForm(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String
) {
    fun toLoteriaRequest(): LoteriaRequest {
        return LoteriaRequest.newBuilder().setNome(nome).setEmail(email).build()
    }
}

data class LoteriaFormResponse(val nome: String, val email: String, val numeros: String)