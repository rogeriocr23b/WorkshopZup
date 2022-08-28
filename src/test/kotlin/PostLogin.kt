import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PostLogin {
    fun login(loginPojo: LoginPojo) {
        Given {
            contentType(ContentType.JSON)
            body(loginPojo)
            log().all()
        } When {
            post("https://reqres.in/api/login")
        } Then {
            log().all()
        }

    }

    val loginFactory = LoginFactory()

    @Test
    fun loginSucccess() {
        login(loginFactory.loginSuccess())
    }

    @Test
    fun loginUnsucccess() {
        login(loginFactory.loginUnsuccess())

         }

    @Test
    fun loginUserNotFound() {
        login(loginFactory.loginUserNotFound())
  }
    @Test
    fun loginIncorretPassword() {
        login(loginFactory.loginIncorrectPassword())
    }}
