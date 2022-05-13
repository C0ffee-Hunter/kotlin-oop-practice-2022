package main.kotlin.lab3

import java.time.LocalDateTime

class Note : NoteService {
    private var noteList: MutableList<Notes> = mutableListOf()
    fun addToList(note: Notes) {
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

    override fun createTextNote(title: String, content: String): Notes.TextNote {
        return Notes.TextNote(title, content, LocalDateTime.now())
    }

    override fun createTask(title: String, content: String, deadline: String): Notes.Task {
        return Notes.Task(title, content, LocalDateTime.now(), deadline)
    }

    override fun createUrl(title: String, content: String, url: String): Notes.Url {
        return Notes.Url(title, content, LocalDateTime.now(), url)
    }

    override fun removeNote(note: Notes) {
        noteList.remove(note)
    }

    override fun findToTitle(title: String): List<Notes> {
        return noteList.filter { it.title == title }
    }

    override fun findToType(type: Class<Any>): List<Notes> {
        return noteList.filter { it.javaClass == type }
    }

    override fun getSortedByTitle(): List<Notes> {
        return noteList.sortedBy { it.title }.toMutableList()
    }

    override fun getSortedByDate(): List<Notes> {
        return noteList.sortedBy { it.date }.toMutableList()
    }
}