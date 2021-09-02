package revolt

import me.maya.revolt.defaultClientBuilder
import me.maya.revolt.events.Event
import me.maya.revolt.events.EventHandler
import me.maya.revolt.registerEventHandler
import me.maya.revolt.setErrorCallback
import me.maya.revolt.token

fun main() {
    val client = defaultClientBuilder {
        token(System.getenv("TOKEN"))

        registerEventHandler(object: EventHandler() {
            override suspend fun onMessage(event: Event.Message) {
                println("Received ${event.message.content} from ${event.message.author.username}")
            }
        })

        setErrorCallback { event, throwable ->
            System.err.println("Error occured in event ${event::class.qualifiedName}")
            throwable.printStackTrace()
        }
    }.build()
    client.runForever()
}