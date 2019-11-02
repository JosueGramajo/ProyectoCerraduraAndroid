package umg.arquitectura.proyectocerradura.firebase

import com.google.firebase.firestore.FirebaseFirestore
import umg.arquitectura.proyectocerradura.objects.LogData

object FirestoreData{
    fun getLogs(onSuccess : (ArrayList<LogData>) -> Unit, onFail : (String) -> Unit){
        val db = FirebaseFirestore.getInstance()
        val list = arrayListOf<LogData>()

        db.collection("logs")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    list.add(document.toObject(LogData::class.java))
                }

                onSuccess(list)
            }
            .addOnFailureListener { exception ->
                onFail("fail")
            }
    }
}