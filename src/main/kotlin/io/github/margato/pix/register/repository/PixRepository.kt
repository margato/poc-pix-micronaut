package io.github.margato.pix.register.repository

import io.github.margato.pix.register.enums.KeyType
import io.github.margato.pix.register.model.PixRegisterEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface PixRepository : CrudRepository<PixRegisterEntity, UUID> {
    fun findByKeyTypeAndUserId(keyType: KeyType, userId: String): Optional<PixRegisterEntity>
}
