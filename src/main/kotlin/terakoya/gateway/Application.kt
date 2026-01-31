package terakoya.gateway

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import io.ktor.server.request.receiveText
import io.ktor.server.response.respondText
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.module() {
    routing {
        // 司令官専用：CloudHook 実弾受信エンドポイント
        post("/terakoyalabo/cloudhook/github") {
            // Rawテキストとして受信
            val rawJson = call.receiveText()

            println("\n" + "=".repeat(60))
            println("📡 [CloudHook] 信号を検知しました")
            println("Time: ${java.time.LocalDateTime.now()}")
            println("-".repeat(60))
            println(rawJson) // ここに生JSONがドバッと出ます
            println("=".repeat(60) + "\n")

            // GitHubへの応答
            call.respondText("Roger that! Terakoya M4 is on duty.")
        }
    }
}

fun main(args: Array<String>) = EngineMain.main(args)
