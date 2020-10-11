package io.github.margato.pix.register.mapper

import io.github.margato.pix.register.model.PixRegisterDTO
import io.github.margato.pix.register.model.PixRegisterEntity
import org.mapstruct.Mapper

@Mapper
interface PixRegisterMapper {
    fun toDto(pixKeyEntity: PixRegisterEntity): PixRegisterDTO
    fun toEntity(dto: PixRegisterDTO): PixRegisterEntity
    fun toDto(keys: Iterable<PixRegisterEntity>): List<PixRegisterDTO>
}