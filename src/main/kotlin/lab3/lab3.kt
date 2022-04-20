package main.kotlin.lab3

/*interface NoteService {
    fun getAllNotes(): List<Note>
    fun getAllTextNotes(): List<Note.TextNote>
    fun getAllTasks(): List<Note.Task>
    fun getAllLinks(): List<Note.Link>

    fun createTextNote(title: String, content: String): Note.TextNote

    fun createTask(title: String, content: String, var deadline: String): Note.Task

    fun createUrl(title: String, content: String, var url: String): Note.Url

    fun removeNote(note: Note): List<Note>

    fun findToTitle(title: String): List<Note>

    fun getSortedByTitle(): List<Note>
}*/

sealed class Notes(var title: String, var content: String, val date: String)
{
    class TextNote(title_1: String, content_1: String, date_1: String): Notes(title_1, content_1, date_1)
    {
        override fun toString(): String {
            return "title: '$title'\ncontent: '$content'\n date: $date)"
        }
    }
    class Task(title_2: String, content_2: String, date_2: String, var deadline: String): Notes(title_2, content_2, date_2)
    {
        override fun toString(): String {
            return "title: '$title'\n content: '$content'\n date: '$date'\n deadline: '$deadline'"
        }
    }
    class Url(title_3: String, content_3: String, date_3: String, var url: String): Notes(title_3, content_3, date_3)
    {
        override fun toString(): String {
            return "title: '$title'\n content: '$content'\n date: '$date'\n url: '$url'"
        }
    }
}
