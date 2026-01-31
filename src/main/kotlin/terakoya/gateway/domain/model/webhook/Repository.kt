package terakoya.gateway.domain.model.webhook

import terakoya.gateway.domain.model.validate

@JvmInline
value class Repository private constructor(val name: String) {
    companion object {
        fun of(name: String): Repository = Repository(name).validate(
            condition = { !it.name.isEmpty() },
            lazyMessage = { "Repository name must not be empty." },
        )
    }
}
