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

    fun createNewMessageInChat(chatId: Int, message: Message) = chats
        .getFirstOrAdd(predicate = {it.id == chatId}, newValue = message, add = { addChat(message)})
            .messages.addAndReturnAddedValue(message)


    fun deleteMessageinChat(chatID: Int, messageId: Int) = chats
        .noEmptyFilter { it.id == chatID }
        .toMutableList()
        .deleteMessage(messageId)

    fun addChat(message: Message): Chat {
        chatId += 1
        val chat = Chat(chatId, mutableListOf())
        chats.add(chat)
        return chat
    }

    fun removeChat(id: Int) = chats.removeWithCheck { it.id == id }

    fun clear() {
        chats.clear()
        chatId = 0
    }

}
