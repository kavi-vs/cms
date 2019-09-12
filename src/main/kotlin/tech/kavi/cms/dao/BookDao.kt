package tech.kavi.cms.dao

import tech.kavi.cms.entity.Book

/**
 * 使用XML映射文件查询SQL
 * */
interface BookDao{
    fun add(book: Book): Int
    fun listBook(): List<Book>
}