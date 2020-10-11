package io.github.margato.pix.register.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.margato.pix.register.enums.KeyType
import io.github.margato.pix.register.enums.Status
import io.micronaut.core.annotation.Introspected
import java.time.ZonedDateTime
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class PixRegisterDTO(
        val id: UUID?,

        @field:NotBlank
        @field:Size(min = 36, max = 36)
        @JsonProperty("user_id")
        val userId: String,

        @JsonProperty("type")
        var keyType: KeyType?,

        @field:Size(max = 36)
        @JsonProperty("value")
        var keyValue: String?,

        var status: Status?,

        @JsonProperty("expires_at")
        var expiresAt: ZonedDateTime?,
)