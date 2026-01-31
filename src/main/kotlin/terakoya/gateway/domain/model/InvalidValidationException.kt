package terakoya.gateway.domain.model

import terakoya.gateway.domain.DomainException

class InvalidValidationException(
    override val message: String,
    override val cause: Throwable? = null,
) : DomainException(message = message, cause = cause)
