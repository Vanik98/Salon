package com.appointmate.home.menu

import com.example.salon.R

enum class MenuItemEnum(
    val key: String,
    val stringResId: Int,
    val iconResId: Int,
) {
    SETTINGS("settings", R.string.navigationMenu_settings, R.drawable.ic_delete),
    ADMINISTRATOR("administrator", R.string.navigationMenu_administrator, R.drawable.ic_delete);

    companion object {
        private val MAP: Map<String, MenuItemEnum> = values().associateBy(MenuItemEnum::key)

        @JvmStatic
        fun from(key: String) = MAP[key]
    }
}