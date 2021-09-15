package com.codingtroops.profilecardlayout

import androidx.compose.ui.graphics.Color
import java.lang.IllegalStateException

data class TopPickForYou constructor(val subject: Subject?, val title: String?,
                                     val teacherName: String?, val groupName: String?, val date: String?,
                                     val memberList: ArrayList<User>? = null, val bgImage: String? = null)

data class Subject(val icon: String?, val name: String?, val color: String?)

data class User (val profileUrl: String?, val borderColor: String)

val discoveryList = arrayListOf(
    TopPickForYou(Subject("https://cdn.non.sa/product_pic/1568144517184_20609_english_product_pic_7.png", "English", "#EF3A61"),
        "Session2", "Teacher1","Group1", "Date 1", arrayListOf(User("https://cdn.non.sa/product_pic/1568144517184_20609_english_product_pic_7.png", "#EF3A61"),
            User("https://cdn.non.sa/product_pic/1568144395059_20609_maths_product_pic_1.png", "#3582D2"),
            User("https://cdn.non.sa/product_pic/1568144414190_20609_physics_product_pic_2.png", "#FA9C35")),
        "https://cdn.non.sa/product_pic/1568144395059_20609_maths_product_pic_1.png"),
    TopPickForYou(null,
        "Session2", "Teacher2","Group2", "Date 2"),
    TopPickForYou(Subject(null, "Physics", "#FA9C35"),
        "Session3", "Teacher3","Group3", "Date 3"),
    TopPickForYou(Subject("https://cdn.non.sa/product_pic/1568144517184_20609_english_product_pic_7.png", "English", null),
        "Session4", "Teacher4","Group4", "Date 4"),
    TopPickForYou(Subject("https://cdn.non.sa/product_pic/1568144395059_20609_maths_product_pic_1.png", "Maths", "#3582D2"),
        "Session5", null,"Group5", "Date 5"),
    TopPickForYou(Subject("https://cdn.non.sa/product_pic/1568144414190_20609_physics_product_pic_2.png", "Physics", "#FA9C35"),
        "Session6", "Teacher6",null, "Date 6"),
    TopPickForYou(Subject("https://cdn.non.sa/product_pic/1568144517184_20609_english_product_pic_7.png", "English", "#EF3A61"),
        "Session7", "Teacher7","Group7", null),
    TopPickForYou(Subject("https://cdn.non.sa/product_pic/1568144395059_20609_maths_product_pic_1.png", "Maths", "#3582D2"),
        "Session8", null,null, null),
    TopPickForYou(null,
        "Session9", null,null, null),
    TopPickForYou(Subject("https://cdn.non.sa/product_pic/1568144517184_20609_english_product_pic_7.png", "English", "#EF3A61"),
        "Session10", "Teacher10","Group10", "Date 10"),
    TopPickForYou(Subject("https://cdn.non.sa/product_pic/1568144395059_20609_maths_product_pic_1.png", "Maths", "#3582D2"),
        "Session11", "Teacher11","Group11", "Date 11"),
    TopPickForYou(Subject("https://cdn.non.sa/product_pic/1568144414190_20609_physics_product_pic_2.png", "Physics", "#FA9C35"),
        "Session12", "Teacher12","Group12", "Date 12"),
)

fun parseColor(color: String, defaultColor: Int = android.graphics.Color.BLACK): Color {
    return try {
        if (color.isNullOrEmpty()) {
            Color(defaultColor)
        } else {
            Color(android.graphics.Color.parseColor(color))
        }
    } catch (ex: IllegalStateException) {
        Color(defaultColor)
    }
}

