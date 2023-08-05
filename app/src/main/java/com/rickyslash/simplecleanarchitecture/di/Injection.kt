package com.rickyslash.simplecleanarchitecture.di

import com.rickyslash.simplecleanarchitecture.data.IMessageDataSource
import com.rickyslash.simplecleanarchitecture.data.MessageDataSource
import com.rickyslash.simplecleanarchitecture.data.MessageRepository
import com.rickyslash.simplecleanarchitecture.domain.IMessageRepository
import com.rickyslash.simplecleanarchitecture.domain.MessageInteractor
import com.rickyslash.simplecleanarchitecture.domain.MessageUseCase

object Injection {
    fun provideUseCase(): MessageUseCase {
        val messageRepository = provideRepository()
        return MessageInteractor(messageRepository)
    }

    private fun provideRepository(): IMessageRepository {
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource(): IMessageDataSource {
        return MessageDataSource()
    }
}