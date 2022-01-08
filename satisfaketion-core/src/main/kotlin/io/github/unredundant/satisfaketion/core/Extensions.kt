package io.github.unredundant.satisfaketion.core

import kotlin.random.Random

object Extensions {
  private object Alphabet {
    const val source = "abcdefghijklmnopqrstuvwxyz"
  }

  /**
   * Replaces every `#` char for this [String] receiver with a random int from 0 to 9 inclusive
   * and returns the modified [String].
   */
  @Suppress("MagicNumber")
  fun String.numerify(random: Random = Random.Default): String {
    return map { if (it == '#') random.nextInt(10).toString() else "$it" }
      .joinToString("")
  }

  /**
   * Replaces every `?` char for this [String] receiver with a random upper-case letter from the English alphabet
   * and returns the modified [String].
   */
  fun String.letterify(random: Random = Random.Default, isUpper: Boolean = true): String {
    return map { if (it == '?') random.nextLetter(upper = isUpper).toString() else "$it" }
      .joinToString("")
  }

  /**
   * Returns a random letter from [Alphabet.source]
   */
  fun Random.nextLetter(upper: Boolean): Char {
    val source = if (upper) Alphabet.source.uppercase() else Alphabet.source
    return source[nextInt(source.length)]
  }

  /**
   * Returns a random element of a list
   */
  fun <T> List<T>.nextItem(random: Random): T = get(random.nextInt(size))

  // Allows for direct mutation on an existing generator
  fun <T, TT> Generator<T>.mutate(mut: Mutator<T, TT>): Generator<TT> {
    return mut.mutate(this)
  }
}