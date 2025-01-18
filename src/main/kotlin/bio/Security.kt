package bio

object Security {
    fun sanitazeSql(input: String): String {
        return input.replace("'", "''")
    }
}
