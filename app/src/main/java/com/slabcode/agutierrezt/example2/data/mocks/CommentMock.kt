package com.slabcode.agutierrezt.example2.data.mocks

import com.slabcode.agutierrezt.example2.data.models.Comment

class CommentMock {
    fun loadComments(): List<Comment> {
        return listOf(
            Comment(1,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Pepito Perez", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            Comment(2,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Juanito Perez", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            Comment(3,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Andres Gutierrez", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            Comment(4,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Camila Becerra", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            Comment(5,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Ivan Gonzales", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            Comment(6,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Pepito Perez", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
        )
    }
}