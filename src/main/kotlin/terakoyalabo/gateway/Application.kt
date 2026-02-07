package terakoyalabo.gateway

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import io.ktor.server.request.path
import io.ktor.server.request.receiveText
import io.ktor.server.response.respondText
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.sampleModule() {
    routing {
        // 司令官専用：CloudHook 実弾受信エンドポイント
        post("/terakoyalabo/cloudhook/github") {
            println(call.request.path())
            // Rawテキストとして受信
            val rawJson = call.receiveText()

            val printStr = buildString {
                appendLine("\n" + "=".repeat(60))
                appendLine("📡 [CloudHook] 信号を検知しました")
                appendLine("Time: ${java.time.LocalDateTime.now()}")
                appendLine("-".repeat(60))
                appendLine(rawJson) // ここに生JSONがドバッと出ます
                appendLine("=".repeat(60) + "\n")
            }
            println(printStr)

            // GitHubへの応答
            call.respondText("Roger that! Terakoya M4 is on duty.")
        }
    }
}

fun main(args: Array<String>) = EngineMain.main(args)
