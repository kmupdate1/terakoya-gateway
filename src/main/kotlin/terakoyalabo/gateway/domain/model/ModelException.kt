package terakoyalabo.gateway.domain.model

import terakoyalabo.gateway.domain.DomainException

abstract class ModelException(
    override val message: String,
    override val cause: Throwable? = null,
) : DomainException(message = message, cause = cause)
