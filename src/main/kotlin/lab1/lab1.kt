package lab1

fun main() {
    val text = """
            1.War and peace// L.N.Tolstoy//1867 
            2.The Witcher// A.Sapkovskiy//1986
            3.The Great Gatsby// F.ScottFitzgerald// 1925
            4.The Invisible Man//H.Wells// 1897
            """
    val bookList: ArrayList<Book> = parsebook(text)
    println(
        """${bookList[0].BookName} ${bookList[0].Author} ${bookList[0].YearOfIssue}
    ${bookList[1].BookName} ${bookList[1].Author} ${bookList[1].YearOfIssue}
    ${bookList[2].BookName} ${bookList[2].Author} ${bookList[2].YearOfIssue}
    ${bookList[3].BookName} ${bookList[3].Author} ${bookList[3].YearOfIssue}"""
    )
    var maxYearOfIssue = Int.MIN_VALUE
    var minYearOfIssue = Int.MAX_VALUE
    var longNameBook = bookList[0].BookName
    var shortNameBook = bookList[0].BookName
    for (i in bookList.indices) {
        val year: Int = bookList[i].YearOfIssue.toInt()
        if (maxYearOfIssue <= year) maxYearOfIssue = year
        if (minYearOfIssue >= year) minYearOfIssue = year
        val length: Int = bookList[i].BookName.length
        if (longNameBook.length <= length) longNameBook = bookList[i].BookName
        if (longNameBook.length >= length) shortNameBook = bookList[i].BookName
    }
    println("The oldest book: $maxYearOfIssue, the newest book: $minYearOfIssue")
    println("The shortest book name: $shortNameBook, the longest book name $longNameBook")
}