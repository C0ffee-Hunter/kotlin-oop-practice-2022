package main.kotlin.lab1

class Book(val bookName: String, val author: String, val yearOfIssue: String)

fun parsebook(books: String): List<Book> {
    val booksList: ArrayList<Book> = arrayListOf()
    var bookName: String
    var author: String
    var yearOfIssue: String
    var isPoint = true
    var i = 0
    while (i != books.length) {
        if (isPoint && books[i] == '.') {
            isPoint = false
            i++
            continue
        } else if (!isPoint && books[i] == '\n') {
            isPoint = true
            i++
            continue
        } else if (isPoint) {
            i++; continue
        }
        bookName = books.substring(i, books.indexOf('/', i))
        i += bookName.length + 1
        i++
        author = books.substring(i, books.indexOf('/', i))
        i += author.length + 1
        i++
        yearOfIssue = if (books.indexOf('\n', i) != -1) books.substring(i, books.indexOf('\n', i))
        else books.substring(i)
        i += yearOfIssue.length
        yearOfIssue = yearOfIssue.substring(yearOfIssue.indexOf('/') + 1)
        booksList.add(Book(bookName, author, yearOfIssue))
    }
    return booksList
}