package main.kotlin.lab3

fun main() {
    val service = Note()

    val textNote = service.createTextNote("Sudden Ideas", "Sell disposable underpants")
    val task = service.createTask("Today task", "go to class", "19.02.2018")
    val url = service.createUrl(
        "github link",
        "Our link",
        "https://github.com/C0ffee-Hunter/kotlin-oop-practice-2022/tree/master/src/main/kotlin"
    )

    service.addToList(url)
    service.addToList(textNote)
    service.addToList(task)

    println(service.getAllNotes())
    println(service.findToTitle("github link"))
    println(service.getSortedByDate())
}