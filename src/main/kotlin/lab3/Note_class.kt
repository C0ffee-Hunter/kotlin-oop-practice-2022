package main.kotlin.lab3

import java.time.LocalDateTime

sealed class Notes(open var title: String, open var content: String, open val date: LocalDateTime) {
    data class TextNote(override var title: String, override var content: String, override var date: LocalDateTime) : Notes(title, content, date) {
        override fun toString(): String {
            return "title: '$title'\ncontent: '$content'\n date: $date)"
        }
    }

    data class Task(override var title: String, override var content: String, override var date: LocalDateTime, var deadline: String) :
        Notes(title, content, date) {
        override fun toString(): String {
            return "title: '$title'\n content: '$content'\n date: '$date'\n deadline: '$deadline'"
        }
    }

    data class Url(override var title: String, override var content: String, override var date: LocalDateTime, var url: String) :
        Notes(title, content, date) {
        override fun toString(): String {
            return "title: '$title'\n content: '$content'\n date: '$date'\n url: '$url'"
        }
    }
}