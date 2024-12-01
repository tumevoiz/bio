package bio.prelude

typealias HashedString = String

interface Hashable {
    val value: String
}

inline fun <reified F : HashingAlgorithm> Hashable.hash(): HashedString {
    val self = this.value
    val algo = F::class.java.getDeclaredConstructor().newInstance()
    return algo.hash(self)
}

inline fun <reified F : HashingAlgorithm> Hashable.verifyWithHashedString(hashedString: HashedString): Boolean {
    val self = this.value
    val algo = F::class.java.getDeclaredConstructor().newInstance()
    return algo.check(self, hashedString)
}
