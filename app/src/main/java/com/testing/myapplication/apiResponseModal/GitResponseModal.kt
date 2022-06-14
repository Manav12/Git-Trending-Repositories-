package com.testing.myapplication.apiResponseModal

data class GitResponseModal(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int,
)