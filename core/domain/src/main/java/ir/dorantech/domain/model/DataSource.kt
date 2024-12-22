package ir.dorantech.domain.model

sealed interface DataSource {
    data object Remote : DataSource
    data object Local : DataSource
    data object Combination : DataSource
}