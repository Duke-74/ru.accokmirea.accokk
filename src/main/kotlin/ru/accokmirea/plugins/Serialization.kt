package ru.accokmirea.plugins

import com.sun.xml.internal.ws.client.ContentNegotiation
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialization(){
    install(ContentNegotiation){
        json()
    }
}
