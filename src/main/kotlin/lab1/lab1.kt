package main.kotlin.lab1

fun main() {
    val text = """
            1.War and peace// L.N.Tolstoy//1867 
            2.The Witcher// A.Sapkovskiy//1986
            3.The Great Gatsby// F.ScottFitzgerald// 1925
            4.The Invisible Man//H.Wells// 1897
            """
    val bookList: ArrayList<Book> = parsebook(text)
    println(
        """${bookList[0].bookName} ${bookList[0].author} ${bookList[0].yearOfIssue}
    ${bookList[1].bookName} ${bookList[1].author} ${bookList[1].yearOfIssue}
    ${bookList[2].bookName} ${bookList[2].author} ${bookList[2].yearOfIssue}
    ${bookList[3].bookName} ${bookList[3].author} ${bookList[3].yearOfIssue}"""
    )
    var maxYearOfIssue = Int.MIN_VALUE
    var minYearOfIssue = Int.MAX_VALUE
    var longNameBook = bookList[0].bookName
    var shortNameBook = bookList[0].bookName
    for (i in bookList.indices) {
        val year: Int = bookList[i].yearOfIssue.toInt()
        if (maxYearOfIssue <= year) maxYearOfIssue = year
        if (minYearOfIssue >= year) minYearOfIssue = year
        val length: Int = bookList[i].bookName.length
        if (longNameBook.length <= length) longNameBook = bookList[i].bookName
        if (longNameBook.length >= length) shortNameBook = bookList[i].bookName
    }
    println("The oldest book: $maxYearOfIssue, the newest book: $minYearOfIssue")
    println("The shortest book name: $shortNameBook, the longest book name $longNameBook")
}