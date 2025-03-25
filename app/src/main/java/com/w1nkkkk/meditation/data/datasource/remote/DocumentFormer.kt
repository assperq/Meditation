package com.w1nkkkk.meditation.data.datasource.remote

object DocumentFormer {
    fun formUserDocument(name : String) : HashMap<String, Any> {
        return hashMapOf(
            "name" to name,
            "day_count" to 0,
            "achievements" to hashMapOf(
                "name" to ""
            )
        )
    }
}

object FirestoreCollections {
    val users = "users"
}