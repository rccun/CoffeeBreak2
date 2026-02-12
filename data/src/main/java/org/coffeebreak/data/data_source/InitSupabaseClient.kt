package org.coffeebreak.data.data_source

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage

object InitSupabaseClient {
    val client = createSupabaseClient(
        "https://qplfqiubtfsebxkudgjp.supabase.co",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFwbGZxaXVidGZzZWJ4a3VkZ2pwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzAyMDkwNTUsImV4cCI6MjA4NTc4NTA1NX0.Dsdy4JrpRosMiuUgqQM6wb9f6Apt25EL4lWDVrghVZw"
    ) {
        install(Realtime)
        install(Auth)
        install(Postgrest)
        install(Storage)
        install(ComposeAuth) {
            googleNativeLogin(
                "415271182284-t788vitbsl3gqkkqpnbt6o8giak2i1ps.apps.googleusercontent.com"
            )
        }
    }
}