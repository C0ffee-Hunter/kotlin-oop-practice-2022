package main.kotlin.lab3

import java.time.LocalDateTime

fun main()
{
    val service = Note()

    val textNote = service.createTextNote("Sudden Ideas", "Sell disposable underpants")
    val task = service.createTask("Today task", "go to class", "19.02.2018")
    val url = service.createUrl("github link", "Our link", "https://github.com/C0ffee-Hunter/kotlin-oop-practice-2022/tree/master/src/main/kotlin")

    service.addToList(url)
    service.addToList(textNote)
    service.addToList(textNote)

    println(service.getAllNotes())
    println(service.findToTitle("github link"))
    println(service.getSortedByDate())
}

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

class Note : NoteService
{
    private var noteList: MutableList<Notes> = mutableListOf()
    fun addToList(note: Notes)
    {
        noteList.add(note)
    }

    override fun getAllNotes(): List<Notes> {
        return noteList
    }

    override fun getAllTextNotes(): List<Notes.TextNote> {
        return noteList.filterIsInstance<Notes.TextNote>()
    }

    override fun getAllTasks(): List<Notes.Task> {
        return noteList.filterIsInstance<Notes.Task>()
    }

    override fun getAllLinks(): List<Notes.Url> {
        return noteList.filterIsInstance<Notes.Url>()
    }

    override fun createTextNote (title: String, content: String): Notes.TextNote
    {
        return Notes.TextNote(title, content, LocalDateTime.now())
    }

    override fun createTask (title: String, content: String, deadline: String): Notes.Task
    {
        return Notes.Task(title, content, LocalDateTime.now(), deadline)
    }

    override fun createUrl (title: String, content: String, url: String): Notes.Url
    {
        return Notes.Url(title, content, LocalDateTime.now(), url)
    }

    override fun removeNote(note: Notes)
    {
        noteList.remove(note)
    }

    override fun findToTitle(title: String): List<Notes>
    {
        return noteList.filter{it.title == title}
    }

    override fun findToType(type: Class<Any>): List<Notes>
    {
        return noteList.filter{it.javaClass == type}
    }

    override fun getSortedByTitle(): List<Notes> {
        return noteList.sortedBy { it.title }.toMutableList()
    }

    override fun getSortedByDate(): List<Notes> {
        return noteList.sortedBy { it.date }.toMutableList()
    }
}

sealed class Notes(var title: String, var content: String, val date: LocalDateTime)
{
    class TextNote(title_1: String, content_1: String, date_1: LocalDateTime): Notes(title_1, content_1, date_1)
    {
        override fun toString(): String {
            return "title: '$title'\ncontent: '$content'\n date: $date)"
        }
    }
    class Task(title_2: String, content_2: String, date_2: LocalDateTime, private var deadline: String): Notes(title_2, content_2, date_2)
    {
        override fun toString(): String {
            return "title: '$title'\n content: '$content'\n date: '$date'\n deadline: '$deadline'"
        }
    }
    class Url(title_3: String, content_3: String, date_3: LocalDateTime, private var url: String): Notes(title_3, content_3, date_3)
    {
        override fun toString(): String {
            return "title: '$title'\n content: '$content'\n date: '$date'\n url: '$url'"
        }
    }
}