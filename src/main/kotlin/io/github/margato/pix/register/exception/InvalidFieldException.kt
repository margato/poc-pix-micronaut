package io.github.margato.pix.register.exception

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

open class InvalidFieldException(message: String)
    : HttpStatusException(HttpStatus.BAD_REQUEST, message)