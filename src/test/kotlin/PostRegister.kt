import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test

class PostRegister {
    fun register01() {
        Given {
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
    fun registerSuccsessTest() {
        register05(registerFactory.registerSuccess())
    }

    @Test
    fun registerFailTest() {
        register05(registerFactory.registerUnsuccess())
    }

    @Test
    fun registerUserNotFound() {
        register05(registerFactory.registerUserNotFound())
    }
}


