package br.edu.fiap.crud.core.data

data class Game(
    var name: String,
    var producer: String,
    var platform: String,
    var note: String,
    var createAt: Long,
    var updateAt: Long,
    var id: Long = 0
)