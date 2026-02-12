package org.coffeebreak.data.repository

import io.github.jan.supabase.postgrest.postgrest
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.dto.CafeModelDto
import org.coffeebreak.data.dto.toDomain
import org.coffeebreak.domain.model.CafeModel
import org.coffeebreak.domain.repository.CoffeeRepository

class CoffeeRepositoryImpl() : CoffeeRepository {
    override suspend fun getCafes(): Result<List<CafeModel>> {
        return try {
            val res =
                client.postgrest["cafes"].select().decodeList<CafeModelDto>().map { it.toDomain() }
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}