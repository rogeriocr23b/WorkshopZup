import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll as orgJunitJupiterApiAssertAll


class GetSingleUser {
    fun getSingleUser(id_users: String): Response {
        return Given {
            pathParam("id_users", id_users)
        } When {
            get("https://reqres.in/api/users/{id_users}")

        } Then {
            log().all()
        } Extract {
            response()
        }
    }


    @Test
    fun getUserNotFoundTest() {

        val response = getSingleUser("23")
        assertNotNull(response)
        assertEquals(404, response.statusCode)
        assertEquals(null, response.jsonPath().getString("data.id"))


    }

    @Test
    fun getSingleUserTest() {

        val response = getSingleUser("1")
        assertNotNull(response)
        assertEquals(200, response.statusCode)
        assertEquals("1", response.jsonPath().getString("data.id"))

    }
}




