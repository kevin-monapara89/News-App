package com.example.newsapp

class NewsModel {

    lateinit var key: String
    lateinit var title: String
    lateinit var description: String
    lateinit var category: String
    lateinit var date: String
    lateinit var image: String
    var visibility: Boolean = false

    constructor()

    constructor(
        key: String,
        title: String,
        description: String,
        category: String,
        date: String,
        image: String,
        visibility: Boolean,
    ) {
        this.key = key
        this.title = title
        this.description = description
        this.category = category
        this.date = date
        this.image = image
        this.visibility = visibility
    }

}