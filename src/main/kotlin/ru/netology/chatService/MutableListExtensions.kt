package ru.netology.chatService


public fun List<Message>.listOfMessages(): List<String> {
    val result: MutableList<String> = mutableListOf()

    this
        .forEach { result.add(it.text) }

    return if (result.isNotEmpty()) result else throw NoEntityException("Нет сообщений")
}

public fun <Value> List<Value>.noEmptyFilter(predicate: (Value) -> Boolean): List<Value> {
    if (this.filter(predicate).isNotEmpty()) {
        return this.filter(predicate)
    } else {
        throw NoEntityException("Объект с указзанным ID не найден")
    }
}

public fun <Value> List<Chat>.noEmptyMap(predicate: (Chat) -> Value): List<Value> {
    if (this.map(predicate).isNotEmpty()) {
        return this.map(predicate)
    } else {
        throw NoEntityException("Чат с указзанным ID не найден")
    }
}

public fun List<Message>.readAll(): List<Message> {
    this.forEach { it.isReadIt = true }
    return this
}

public fun <R, V> List<V>.noEmptyFlatMap(predicate: (V) -> Iterable<R>): List<R> {
    return if (this.isNotEmpty()) this.flatMap(predicate)  else throw  NoEntityException("Список чатов пуст")
}

public fun MutableList<Chat>.deleteMessage(messageId: Int): Boolean {
    val index = this.indexOfFirst { it.messages.any { it.id == messageId } }
    if (index != -1) {
        return this[index].messages.removeIf { it.id == messageId }
    } else {
        throw NoEntityException("Не найден чат с таким ID")
    }
}