import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ru.netology.chatService.Chat
import ru.netology.chatService.ChatService
import ru.netology.chatService.Message
import ru.netology.chatService.NoEntityException
import java.util.Stack
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ChatServiceTest {


    @Before
    fun setUp() {
        addMockData()
    }

    @After
    fun tearDown() {
        ChatService.clear()
    }

    @Test
    fun getUnreadChatsCount() {
        assertEquals(1, ChatService.getUnreadChatsCount())
    }

    @Test
    fun getChats() {
        val list = listOf(
            Chat(
                1,
                mutableListOf(
                    Message(
                        0,
                        1,
                        "qqq",
                        true
                    )
                )
            ),
            Chat(
                2,
                mutableListOf(
                    Message(
                        1,
                        2,
                        "www",
                        true
                    )
                )
            ),
            Chat(
                3,
                mutableListOf(
                    Message(
                        2,
                        1,
                        "aaa",
                        false
                    )
                )
            )
        )
        print(list)
        print(ChatService.getChats())
        Assert.assertEquals(list, ChatService.getChats())
    }

    @Test
    fun getLastMessagesIn() {
        val list = listOf("qqq", "www", "aaa")
        assertEquals(list, ChatService.getLastMessagesIn())
    }

    @Test(expected = NoEntityException::class)
    fun errorGetLastMessagesIn() {
        ChatService.clear()
        ChatService.getLastMessagesIn()
    }

    @Test(expected = NoEntityException::class)
    fun throwLastMessagesIn() {
        ChatService.clear()
        ChatService.getLastMessagesIn()
    }

    @Test
    fun getLastMessagesOfAutor() {
        val list = listOf("www")
        assertEquals(list, ChatService.getLastMessagesOfAutor(2))
    }

    @Test(expected = NoEntityException::class)
    fun mapErrorGetLastMessagesOfAutor() {
        ChatService.clear()
        ChatService.getLastMessagesOfAutor(2)
    }
    @Test(expected = NoEntityException::class)
    fun filterErrorGetLastMessagesOfAutor() {
        ChatService.getLastMessagesOfAutor(5)
    }

    @Test
    fun createNewMessageInChat() {
        val message = Message(0,1,"qwe",false)
        assertEquals(message, ChatService.createNewMessageInChat(0, message))
    }

    @Test
    fun deleteMessageinChat() {
        assertTrue(ChatService.deleteMessageinChat(1, 0))
    }

    @Test(expected = NoEntityException::class)
    fun filrerErrorDeleteMessageInChat() {
        ChatService.clear()
        ChatService.deleteMessageinChat(1, 1)
    }

    @Test(expected = NoEntityException::class)
    fun falseChatIdDeleteMessageInChat() {
        ChatService.deleteMessageinChat(14, 1)
    }

    @Test
    fun addChat() {
        val message = Message(0,1,"111", false)
        val chat = Chat(4, mutableListOf())
        assertEquals(chat, ChatService.addChat(message))
    }

    @Test
    fun removeChat() {
        assertTrue(ChatService.removeChat(1))
    }

    @Test(expected = NoEntityException::class)
    fun errorRemoveChat() {
        ChatService.removeChat(6)
    }


    fun addMockData() {
        ChatService.createNewMessageInChat(0, Message(0, 1, "qqq", true))
        ChatService.createNewMessageInChat(0, Message(1, 2, "www", true))
        ChatService.createNewMessageInChat(0, Message(2, 1, "aaa", false))
    }
}

