import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetUsers : Base() {

  private val QUERY_PAGE = "page"
  private val QUERY_PER_PAGE = "per_page"
    private val URL_GET_USERS = "users"

    fun getUsers(page: Int, per_page: Int): Response {
        return Given {
            spec(specBase())
            queryParam(QUERY_PAGE,page)
            queryParam(QUERY_PER_PAGE, per_page)
            log().all()
        } When {
            get("https://reqres.in/api/users?page=2")
        } Then {
            log().all()
        } Extract {
            response()
        }
    }
    @ParameterizedTest
    @ValueSource(ints = [1,2,3])
    fun getMultipleUsersTest1(page: Int) {
        getUsers(page, per_page = 1)


    }

    @ParameterizedTest (name = "Buscar usuário da página {0} sendo {1} por página ")

    @MethodSource("exemploUsers")
        fun getMultipleUsersTest2(page: Int, per_page: Int) {
        getUsers(page, per_page)

    }

    companion object {

        @JvmStatic
        fun exemploUsers(): Stream<Arguments>{
return Stream.of (
    Arguments.arguments(1,1),
    Arguments.arguments(2,1),
    Arguments.arguments(3,1),
        )

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