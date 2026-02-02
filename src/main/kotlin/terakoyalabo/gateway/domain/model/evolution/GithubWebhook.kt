package terakoyalabo.gateway.domain.model.evolution

import java.time.LocalDateTime

data class GithubWebhook(
    val repository: Repository,
    val branch: Branch,
    val pusher: Pusher,
    val receivedAt: LocalDateTime = LocalDateTime.now(),
) {
    val shouldTriggerBuild: Boolean get() = branch.isMain && repository.name == "terakoya-core"
}
