package ru.accokmirea.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.accokmirea.database.tables.TableDTO

object Tables: Table("tables") {
    private val tableId = Tables.varchar("id", 50)
    private val name = Tables.varchar("name", 30)
    private val description = Tables.varchar("description", 150)
    private val isBooked = Tables.varchar("isBooked", 5)

    fun insert(tableDTO: TableDTO){
        transaction {
            Tables.insert {
                it[tableId] = tableDTO.tableId
                it[name] = tableDTO.name
                it[description] = tableDTO.description
                it[isBooked] = tableDTO.isBooked
            }
        }
    }

    fun fetchTables(): List<TableDTO> {
        return try {
            transaction {
                Tables.selectAll().toList()
                    .map {
                        TableDTO(
                            tableId = it[Tables.tableId],
                            name = it[Tables.name],
                            description = it[Tables.description],
                            isBooked = it[Tables.isBooked]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}