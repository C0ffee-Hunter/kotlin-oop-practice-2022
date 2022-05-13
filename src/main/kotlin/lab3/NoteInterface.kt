package main.kotlin.lab3

interface NoteService {
    fun getAllNotes(): List<Notes>
    fun getAllTextNotes(): List<Notes.TextNote>
    fun getAllTasks(): List<Notes.Task>
    fun getAllLinks(): List<Notes.Url>

    fun createTextNote(title: String, content: String): Notes.TextNote

    fun createTask(title: String, content: String, deadline: String): Notes.Task

    fun createUrl(title: String, content: String, url: String): Notes.Url

    fun removeNote(note: Notes)

    fun findToTitle(title: String): List<Notes>
    fun findToType(type: Class<Any>): List<Notes>

    fun getSortedByTitle(): List<Notes>
    fun getSortedByDate(): List<Notes>
}
