import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test



class GetUsers {

    fun getUsers(page: Int, per_page: Int): Response {
        return Given {
            queryParam("page", page)
            queryParam("per_page", per_page)
            log().all()
        } When {
            get("https://reqres.in/api/users?page=2")
        } Then {
            log().all()
        } Extract {
            response()
        }
    }

    @Test
    @DisplayName ("Buscar 1 usuário com nome de George")
    fun getUsersTest() {
        val response =  getUsers(1,1)
        assertAll("valida busca de 1 usuário com nome de George",
        { assertNotNull(response)},
        { assertEquals(200,response.statusCode)},
        {assertEquals(1,response.jsonPath().getInt("page"))},
        {assertEquals("George",response.jsonPath().getString("data[0].first_name"))},
        {assertEquals(1,response.jsonPath().getInt("data[0].id"))}

        )






    }
    @Test
    @DisplayName ("Valida posição de ID")
    fun getFiveUsersTest() {
       val response = getUsers(1,5)
       val count = response.jsonPath().getInt("data.size()")

        for(i in 0 .. (count-1)){
            assertEquals(i+1,response.jsonPath().getInt("data[$i].id"))
        }

    }
}