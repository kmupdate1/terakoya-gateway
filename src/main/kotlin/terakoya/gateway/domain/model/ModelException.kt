package terakoya.gateway.domain.model

import terakoya.gateway.domain.DomainException

abstract class ModelException(
    override val message: String,
    override val cause: Throwable? = null,
) : DomainException(message = message, cause = cause)
