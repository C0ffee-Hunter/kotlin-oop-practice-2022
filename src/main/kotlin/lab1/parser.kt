package lab1

class Book(val BookName: String, val Author: String, val YearOfIssue: String)

fun parsebook(Books: String): ArrayList<Book> {
    val bookslist: ArrayList<Book> = arrayListOf()
    var bookName: String
    var author: String
    var yearOfIssue: String
    var isPoint = true
    var i = 0
    while (i != Books.length) {
        if (isPoint && Books[i] == '.') {
            isPoint = false
            i++
            continue
        } else if (!isPoint && Books[i] == '\n') {
            isPoint = true
            i++
            continue
        } else if (isPoint) {
            i++; continue
        }
        bookName = Books.substring(i, Books.indexOf('/', i))
        i += bookName.length + 1
        i++
        author = Books.substring(i, Books.indexOf('/', i))
        i += author.length + 1
        i++
        yearOfIssue = if (Books.indexOf('\n', i) != -1) Books.substring(i, Books.indexOf('\n', i))
        else Books.substring(i)
        i += yearOfIssue.length
        yearOfIssue = yearOfIssue.substring(yearOfIssue.indexOf('/') + 1)
        bookslist.add(Book(bookName, author, yearOfIssue))
    }
    return bookslist
}
