package io.github.margato.pix.register.facade

import io.github.margato.pix.register.enums.KeyType
import io.github.margato.pix.register.exception.KeyTypeAlreadyRegisteredException
import io.github.margato.pix.register.enums.Status
import io.github.margato.pix.register.mapper.PixRegisterMapper
import io.github.margato.pix.register.model.PixRegisterDTO
import io.github.margato.pix.register.repository.PixRepository
import io.github.margato.pix.register.validator.PixRegisterValidator
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PixFacade(
        @Inject private val repository: PixRepository,
        @Inject private val validator: PixRegisterValidator
) {

    private val mapper = Mappers.getMapper(PixRegisterMapper::class.java)
    private val logger = LoggerFactory.getLogger(this::class.java)

    open fun registerKey(dto: PixRegisterDTO): PixRegisterDTO {
        validator.validatePixInput(dto)
        checkIfKeyIsAlreadyRegistered(dto)

        dto.expiresAt = ZonedDateTime.now().plusYears(1)

        val entity = mapper.toEntity(dto)
        entity.status = Status.WAITING_FOR_ACTIVATION

        val registeredKey = repository.save(entity)
        logger.info("Key ${registeredKey.keyType}:${registeredKey.keyValue} was saved")

        return mapper.toDto(registeredKey)
    }

    open fun listAllKeys(): List<PixRegisterDTO> {
        return mapper.toDto(repository.findAll())
    }

    open fun registerRandomKey(dto: PixRegisterDTO): PixRegisterDTO {
        val randomKey = UUID.randomUUID().toString().replace("-", "").substring(0, 14)

        if (dto.expiresAt == null) {
            dto.expiresAt = ZonedDateTime.now().plusMinutes(15)
        }

        dto.keyType = KeyType.RANDOM
        dto.keyValue = randomKey
        dto.status = Status.ACTIVE

        val entity = mapper.toEntity(dto)
        val registeredRandomKey = repository.save(entity)
        logger.info("Key ${registeredRandomKey.keyType}:${registeredRandomKey.keyValue} was saved")

        return mapper.toDto(registeredRandomKey)
    }

    private fun checkIfKeyIsAlreadyRegistered(dto: PixRegisterDTO) {
        val type = dto.keyType!!
        val userId = dto.userId

        val keyAlreadyRegistered = repository.findByKeyTypeAndUserId(type, userId)

        if (keyAlreadyRegistered.isPresent) {
            throw KeyTypeAlreadyRegisteredException(type, userId)
        }
    }

}