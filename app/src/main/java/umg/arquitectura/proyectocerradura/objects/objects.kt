package umg.arquitectura.proyectocerradura.objects

data class User(
    val name : String,
    val id : String
)

data class RoleAndState(
    val id : Int,
    val user_state : String
)