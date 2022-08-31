import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRegister : Base() {

    private val URL_POST_REGISTER = "register"
    fun register01() {
        Given {
            spec(specBase())
            contentType(ContentType.JSON)
            body(
                "{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}"
            )
        } When {
            post("https://reqres.in/api/register")
        } Then {
            log().all()
        }
    }

    fun register02() {

        val hashMap: HashMap<String, String> = HashMap()
        hashMap.put("email", "eve.holt@reqres.in")
        hashMap.put("password", "pistol")
        Given {
            spec(specBase())
            contentType(ContentType.JSON)
            body(hashMap)
        } When {
            post("https://reqres.in/api/register")
        } Then {
            log().all()
        }
    }

    fun register03() {
        val registerPojo = RegisterPojo()
        registerPojo.email = "eve.holt@reqres.in"
        registerPojo.password = "pistol"

        Given {
            spec(specBase())
            contentType(ContentType.JSON)
            body(registerPojo)
        } When {
            post("https://reqres.in/api/register")
        } Then {
            log().all()
        }

    }

    fun register04() {
        val registerPojo = RegisterPojo()
        registerPojo.email = "eve.holt@reqres.in"

        Given {
            spec(specBase())
            contentType(ContentType.JSON)
            body(registerPojo)
        } When {
            post("https://reqres.in/api/register")
        } Then {
            log().all()
        }

    }

    fun register05(registerPojo: RegisterPojo) {
        Given {
            spec(specBaseToken())
            contentType(ContentType.JSON)
            body(registerPojo)
            log().all()
        } When {
            post("https://reqres.in/api/register")
        } Then {
            log().all()
        }

    }

    val registerFactory = RegisterFactory()

    @Test
    @Tag("success")
    fun registerSuccsessTest() {
        register05(registerFactory.registerSuccess())
    }

    @Test
    @Tag("fail")
    fun registerFailTest() {
        register05(registerFactory.registerUnsuccess())
    }

    @Test
    @Tag("fail")
    fun registerUserNotFound() {
        register05(registerFactory.registerUserNotFound())
    }
}


