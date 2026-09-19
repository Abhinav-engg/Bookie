package com.abhinav.bookie.data

class BookRepo {

    fun getBooksById(id: Int): Book? {
        return MockBookData.books.find { it.id == id }
    }

    fun getBooksByCategory(category: String): List<Book> {

        if (category == "All") {
            return MockBookData.books
        }
        return MockBookData.books.filter { it.category == category }
    }

    fun getPopularBooks(): List<Book> {
        return MockBookData.books.filter { !it.isFeatured }

    }

    fun getFeaturedBooks(): List<Book> {
        return MockBookData.books.filter { it.isFeatured }
    }

    fun getFavoriteBooks(): List<Book> {
        return MockBookData.books.filter { it.isFavorite }
    }
}
