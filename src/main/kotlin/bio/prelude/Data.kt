package bio.prelude

typealias AllComparable = Comparable<*>

interface Repository<Id : AllComparable, Row : RowClass<Id>> {
    fun create(row: Row): Row?

    fun delete(id: Id): Boolean
}

interface RowClass<Id : AllComparable> {
    fun id(): Id

    fun tableName(): String
}
