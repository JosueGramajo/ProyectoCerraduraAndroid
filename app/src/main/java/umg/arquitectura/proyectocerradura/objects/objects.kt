package umg.arquitectura.proyectocerradura.objects

data class User(
    val name : String,
    val biometricID : String
){
    constructor() : this("","")
}

data class RoleAndState(
    val id : Int,
    val user_state : String,
    val name : String
){
    constructor() : this(0, "", "")
}

data class LogData(
    val name : String,
    val time : String,
    val date : String,
    val event_status : String
){
    constructor() : this("","","","")
}

data class LogList(
    val list : ArrayList<LogData>
){
    constructor() : this(arrayListOf())
}

data class LocalUser(
    val id: Int,
    val name : String,
    val biometricID : String,
    val user_state: String,
    val roleID : Int
){
    constructor() : this(0, "", "", "" , 0)
}

data class UserList(
    val list : ArrayList<LocalUser>
){
    constructor() : this(arrayListOf())
}


data class ChangeState(
    val biometricID : String,
    val user_state : String
){
    constructor() : this("", "")
}