package ru.netology.chatService

object ChatService {
    private val chats: MutableList<Chat> = mutableListOf()
    private var chatId = 0

    fun getUnreadChatsCount() = chats.
    count { it.messages.any { !it.isReadIt } }

    fun getChats(): List<Chat> = chats

    fun getLastMessagesIn() = chats
        .noEmptyMap { it.messages.listOfMessages()}
        .flatMap { it }

    fun getLastMessagesOfAutor(autorId: Int) = chats
        .noEmptyFlatMap { it.messages }
        .noEmptyFilter { it.autorId == autorId }
        .readAll()
        .listOfMessages()

    fun createNewMessageInChat(chatId: Int, message: Message): Message {
        val index = chats.indexOfFirst { it.id == chatId }
        if (index != -1) {
            chats[index].messages.add(message)
        } else {
            addChat(message)
        }
        return message
    }

    fun deleteMessageinChat(chatID: Int, messageId: Int) = chats
        .noEmptyFilter { it.id == chatID }
        .toMutableList()
        .deleteMessage(messageId)

    fun addChat(message: Message): Chat {
        chatId += 1
        val chat = Chat(chatId, mutableListOf())
        chat.messages.add(message)
        chats.add(chat)
        return chat
    }

    fun removeChat(id: Int):Boolean {
        return if (chats.removeIf { it.id == id }) true else throw NoEntityException("Чат с указзанным ID не найден")
    }

    fun clear() {
        chats.clear()
        chatId = 0
    }

}
