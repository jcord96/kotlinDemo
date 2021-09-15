package es.jco.demo

import com.google.gson.Gson
import es.jco.demo.data.server.dto.UserDTO
import es.jco.demo.data.server.mapper.toDomain
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun getRemoteUsersFake() {
        val response = "{\"id\":1,\"name\":\"Leanne Graham\",\"username\":\"Bret\",\"email\":\"Sincere@april.biz\",\"address\":{\"street\":\"Kulas Light\",\"suite\":\"Apt. 556\",\"city\":\"Gwenborough\",\"zipcode\":\"92998-3874\",\"geo\":{\"lat\":\"-37.3159\",\"lng\":\"81.1496\"}},\"phone\":\"1-770-736-8031 x56442\",\"website\":\"hildegard.org\",\"company\":{\"name\":\"Romaguera-Crona\",\"catchPhrase\":\"Multi-layered client-server neural-net\",\"bs\":\"harness real-time e-markets\"}}"
        val userDTO = Gson().fromJson(response, UserDTO::class.java);

        assertEquals(userDTO.id, 1)
        assertEquals(userDTO.name, "Leanne Graham")
        assertEquals(userDTO.username, "Bret")
        assertEquals(userDTO.email, "Sincere@april.biz")
        assertEquals(userDTO.phone, "1-770-736-8031 x56442")
        assertEquals(userDTO.website, "hildegard.org")

        assertEquals(userDTO.addressDTO?.geoDTO?.lat, "-37.3159")

        val userDomain = userDTO.toDomain()

        assertEquals(userDomain.id, 1)
        assertEquals(userDomain.name, "Leanne Graham")
        assertEquals(userDomain.username, "Bret")
        assertEquals(userDomain.email, "Sincere@april.biz")
        assertEquals(userDomain.phone, "1-770-736-8031 x56442")
        assertEquals(userDomain.website, "hildegard.org")

        assertEquals(userDomain.address?.id, null)
        assertEquals(userDomain.address?.city, "Gwenborough")
        assertEquals(userDomain.address?.zipcode, "92998-3874")
        assertEquals(userDomain.address?.street, "Kulas Light")
        assertEquals(userDomain.address?.suite, "Apt. 556")

        assertEquals(userDomain.address?.geo?.lat, -37.3159)
        assertEquals(userDomain.address?.geo?.lng, 81.1496)
    }
}