package com.rickyslash.simplecleanarchitecture.data

import com.rickyslash.simplecleanarchitecture.domain.MessageEntity

class MessageDataSource: IMessageDataSource {
    override fun getMessageFromSource(name: String) = MessageEntity("Hello $name! This is Clean Architecture")
}