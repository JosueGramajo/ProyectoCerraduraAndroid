package umg.arquitectura.proyectocerradura.objects

data class User(
    val name : String,
    val id : String
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
    val status : String
){
    constructor() : this("","","","")
}