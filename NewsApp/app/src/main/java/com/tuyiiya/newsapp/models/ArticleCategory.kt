package com.tuyiiya.newsapp.models

import com.tuyiiya.newsapp.models.ArticleCategory.*

enum class ArticleCategory(val categoryName: String) {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology")
}

fun getAllArticleCategories(): List<ArticleCategory>{
    return listOf(
        ArticleCategory.BUSINESS,
        ArticleCategory.ENTERTAINMENT,
        ArticleCategory.GENERAL,
        ArticleCategory.HEALTH,
        ArticleCategory.SCIENCE,
        ArticleCategory.SPORTS,
        ArticleCategory.TECHNOLOGY,
    )
}

fun getArticleCategory(category: String): ArticleCategory? {
    val map = ArticleCategory.entries.associateBy(ArticleCategory::categoryName)

    return map[category]
}