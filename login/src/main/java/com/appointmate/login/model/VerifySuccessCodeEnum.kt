package com.appointmate.login.model

enum class VerifySuccessCodeEnum(val successCode: Int) {
    UNDEFINED(-1),
    FAILED(0),
    SUCCESSFUL(1);

    companion object {
        private val map: Map<Int, VerifySuccessCodeEnum> = values().associateBy(VerifySuccessCodeEnum::successCode)

        @JvmStatic
        fun from(code: Int?) = map[code] ?: UNDEFINED
    }
}