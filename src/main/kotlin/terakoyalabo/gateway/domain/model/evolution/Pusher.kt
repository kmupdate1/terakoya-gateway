package terakoyalabo.gateway.domain.model.evolution

import terakoyalabo.gateway.domain.model.validate

@JvmInline
value class Pusher private constructor(val name: String) {
    companion object {
        fun of(pusher: Pusher): Pusher = Pusher(pusher.name).validate(
            condition = { !it.name.isEmpty() },
            lazyMessage = { "Pusher name must not be empty." }
        )
    }
}
