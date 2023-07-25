package com.example.newsapp

class NewsModel {

    lateinit var key: String
    lateinit var title: String
    lateinit var description: String
    lateinit var image: String

    constructor() {

    }
    constructor(key: String, title: String, description: String, image: String) {
        this.key = key
        this.title = title
        this.description = description
        this.image = image
    }

}