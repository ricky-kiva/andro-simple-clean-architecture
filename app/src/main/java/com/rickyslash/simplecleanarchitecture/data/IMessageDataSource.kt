package com.rickyslash.simplecleanarchitecture.data

import com.rickyslash.simplecleanarchitecture.domain.MessageEntity

interface IMessageDataSource {
    fun getMessageFromSource(name: String): MessageEntity
}