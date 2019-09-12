package tech.kavi.cms.dao

import tech.kavi.cms.entity.User

/**
 * 使用XML映射文件查询SQL
 * */
interface UserDao{
    fun add(user: User): Int
    fun listUser(): List<User>
}