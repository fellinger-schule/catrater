package be.rgen.catrater.models

data class CatPost (val id: String, val url: String, val width: Int, val height: Int) {
    override fun equals(other: Any?): Boolean {
        return when(other) {
            is CatPost -> this.id == other.id
            else -> false
        }
    }
}