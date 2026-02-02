package terakoyalabo.gateway.domain

abstract class DomainException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
