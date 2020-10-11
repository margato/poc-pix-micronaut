package io.github.margato.pix.register.exception

import io.github.margato.pix.register.enums.KeyType
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

open class KeyTypeAlreadyRegisteredException(keyType: KeyType, userId: String)
    : HttpStatusException(HttpStatus.BAD_REQUEST, "Key type $keyType already registered to user $userId")