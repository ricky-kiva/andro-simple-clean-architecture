package com.rickyslash.simplecleanarchitecture.domain

interface MessageUseCase {
    fun getMessage(name: String): MessageEntity
}