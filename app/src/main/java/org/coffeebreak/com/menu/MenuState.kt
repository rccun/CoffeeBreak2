package org.coffeebreak.com.menu

import org.coffeebreak.domain.model.CoffeeModel

data class MenuState(
    val userName: String = "",
    val coffees: List<CoffeeModel> = emptyList(),
    val isLoading: Boolean = true
)
