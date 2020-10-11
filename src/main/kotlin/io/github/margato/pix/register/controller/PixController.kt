package io.github.margato.pix.register.controller

import io.github.margato.pix.register.facade.PixFacade
import io.github.margato.pix.register.model.PixRegisterDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.inject.Inject
import javax.validation.Valid

@Controller("/pix/keys")
open class PixController(@Inject val pixFacade: PixFacade) {

    @Post("/random")
    open fun registerRandomKey(@Valid @Body dto: PixRegisterDTO): HttpResponse<PixRegisterDTO> {
        val response = pixFacade.registerRandomKey(dto)
        return HttpResponse.created(response)
    }

    @Post
    open fun registerKey(@Valid @Body dto: PixRegisterDTO): HttpResponse<PixRegisterDTO> {
        val response = pixFacade.registerKey(dto)
        return HttpResponse.created(response)
    }

    @Get
    open fun listAllKeys(): HttpResponse<List<PixRegisterDTO>> {
        val response = pixFacade.listAllKeys()
        return HttpResponse.ok(response)
    }

}