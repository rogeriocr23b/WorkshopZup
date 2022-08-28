class LoginFactory {
    var loginPojo = LoginPojo()

    fun loginSuccess(): LoginPojo {
        loginPojo.email = "eve.holt@reqres.in"
        loginPojo.password = "cityslicka"
        return loginPojo

    }

    fun loginUnsuccess(): LoginPojo {
        loginPojo.email = "eve.holt@reqres.in"
        return loginPojo

    }

    fun loginUserNotFound(): LoginPojo {
        loginPojo.email = "teste@reqres.in"
        return loginPojo
    }

    fun loginIncorrectPassword(): LoginPojo {
        loginPojo.email = "eve.holt@reqres.in"
        loginPojo.password = "xxxxxx"
        return loginPojo
    }
}