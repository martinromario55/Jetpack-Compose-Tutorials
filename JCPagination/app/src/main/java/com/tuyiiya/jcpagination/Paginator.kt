package com.tuyiiya.jcpagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}