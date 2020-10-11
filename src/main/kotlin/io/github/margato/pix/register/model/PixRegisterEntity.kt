package io.github.margato.pix.register.model

import io.github.margato.pix.register.enums.KeyType
import io.github.margato.pix.register.enums.Status
import io.micronaut.core.annotation.Introspected
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "pix_keys")
@Introspected
data class PixRegisterEntity(
        @Id
        @GeneratedValue
        val id: UUID?,

        @Column(name = "user_id", length = 36)
        val userId: String,

        @Column(name = "type")
        @Enumerated(EnumType.STRING)
        var keyType: KeyType,

        @Column(name = "value", length = 100)
        var keyValue: String,

        @Column(name = "expires_at", length = 100)
        val expiresAt: String,

        @Column(name = "status")
        @Enumerated(EnumType.STRING)
        var status: Status?
)