package io.github.margato.pix.register.validator

import io.github.margato.pix.register.enums.KeyType
import io.github.margato.pix.register.exception.InvalidFieldException
import io.github.margato.pix.register.exception.KeyTypeNotAllowedException
import io.github.margato.pix.register.model.PixRegisterDTO
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
open class PixRegisterValidator {
    private val logger = LoggerFactory.getLogger(this::class.java)

    open fun validatePixInput(dto: PixRegisterDTO) {
        if (dto.keyType == null) {
            logger.error("Key ${KeyType.RANDOM} cannot be registered")
            throw InvalidFieldException("Null field: type")
        }
        if (dto.keyValue == null) {
            logger.error("Key ${KeyType.RANDOM} cannot be registered")
            throw InvalidFieldException("Null field: value")
        }
        if (dto.keyType == KeyType.RANDOM) {
            logger.error("Key ${KeyType.RANDOM} cannot be registered")
            throw KeyTypeNotAllowedException(KeyType.RANDOM)
        }
    }

}
