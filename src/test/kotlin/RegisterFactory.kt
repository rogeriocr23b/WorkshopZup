class RegisterFactory {
    var registerPojo = RegisterPojo()


    fun registerSuccess(): RegisterPojo {
        registerPojo.email = "eve.holt@reqres.in"
        registerPojo.password = "pistol"
        return registerPojo

    }

    fun registerUnsuccess(): RegisterPojo {
        registerPojo.email = "eve.holt@reqres.in"
        return registerPojo

    }

    fun registerUserNotFound(): RegisterPojo {
        registerPojo.email = "teste@reqres.in"
        return registerPojo
    }

}