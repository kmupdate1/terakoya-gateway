package terakoyalabo.gateway.domain.model

inline fun <T> T.validate(condition: (T) -> Boolean, lazyMessage: (T) -> String): T {
    if (!condition(this)) throw InvalidValidationException(lazyMessage.invoke(this))
    return this
}
