package terakoya.gateway.domain.model.webhook

import terakoya.gateway.domain.model.validate

@JvmInline
value class Branch private constructor(val name: String) {
    companion object {
        fun of(name: String): Branch = Branch(name).validate(
            condition = { !it.name.isEmpty() },
            lazyMessage = { "Branch name must not be empty." },
        )
    }

    val isMain: Boolean get() = name == "main" || name == "refs/heads/main"
}
