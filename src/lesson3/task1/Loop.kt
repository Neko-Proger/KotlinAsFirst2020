@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.sqrt
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.abs

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var numbOfDigits = 0
    var number = n
    if (n == 0) return 1
    while (number > 0) {
        number /= 10
        numbOfDigits += 1
    }
    return numbOfDigits
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var number1 = 1
    var number2 = 1
    var number3 = 2
    return when (n) {
        1 -> 1
        2 -> 1
        3 -> 2
        else -> {
            for (i in 4..n) {
                number1 = number2
                number2 = number3
                number3 = number1 + number2
            }
            number3
        }
    }
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var minDivider = 2
    while (n % minDivider != 0) {
        minDivider++
    }
    return minDivider
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var maxDivider = n - 1
    while (n % maxDivider != 0 && maxDivider > 1) {
        maxDivider--
    }
    return maxDivider
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var step = 0
    var number = x
    while (number != 1) {
        if (number % 2 == 0) number /= 2 else number = 3 * number + 1
        step += 1
    }
    return step
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val max = if (m > n) m else n
    val min = if (m > n) n else m
    for (i in 1..max) {
        if (max * i % min == 0) return max * i
    }
    return max * min
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val max = if (m > n) m else n
    val min = if (m > n) n else m
    for (i in 2..max) {
        if (max % i == 0 && min % i == 0) return false
    }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k: Double
    for (i in m..n) {
        k = sqrt(i.toDouble())
        if (floor(k) == k) return true
    }
    return false
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var back = 0
    var number = n
    while (number > 0) {
        back = back * 10 + number % 10
        number /= 10
    }
    return back
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var counter: Long = 1
    var number = n.toLong()
    while (number > 0) {
        number /= 10
        counter *= 10
    }
    counter /= 10
    number = n.toLong()
    while (number > 9) {
        if (number % 10 != number / counter) return false
        number %= counter
        number /= 10
        counter /= 100
    }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    val numeral = number % 10
    number /= 10
    while (number > 0) {
        if (number % 10 != numeral) return true
        number /= 10
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var n = 0
    var d = x
    var sn = (-1.0).pow(n) / factorial(2 * n + 1) * d.pow(2 * n + 1)
    if (x > 2 * kotlin.math.PI) {
        d = (x / kotlin.math.PI) % 2 * kotlin.math.PI
    }
    var sum = d
    n++
    while (abs(sn) >= eps) {
        sn = (-1.0).pow(n) / factorial(2 * n + 1) * d.pow(2 * n + 1)
        sum += sn
        n += 1
    }
    return sum
}


/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var n = 0
    var d = x
    var sn = (-1.0).pow(n) / factorial(2 * n) * d.pow(2 * n)
    if (x > 2 * kotlin.math.PI) {
        d = (x / kotlin.math.PI) % 2 * kotlin.math.PI
    }
    var sum = sn
    n++
    while (abs(sn) >= eps) {
        sn = (-1.0).pow(n) / factorial(2 * n) * d.pow(2 * n)
        sum += sn
        n += 1
    }
    return sum
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun numberOfDigits(n: Int): Int {
    var x = 0
    var number = n
    while (number > 0) {
        number /= 10
        x += 1
    }
    return x
}

fun squareSequenceDigit(n: Int): Int {
    var number = 1
    var numeral = n
    var numberDigits = 0
    while (true) {
        numberDigits = numberOfDigits(number * number)
        if (numeral - numberDigits <= 0) {
            numeral -= numberDigits
            break
        }
        numeral -= numberDigits
        number += 1

    }
    number *= number
    while (numeral != 0) {
        numeral += 1
        number /= 10
    }
    return number % 10
}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var number1 = 1
    var number2 = 1
    var numeral = n - 2
    var numberDigits = 0
    var number3 = 0
    when (n) {
        1 -> return 1
        2 -> return 1
        else -> {
            while (true) {
                number3 = number1 + number2
                numberDigits = numberOfDigits(number3)
                numeral -= numberDigits
                if (numeral <= 0) {
                    break
                }
                number1 = number2
                number2 = number3
            }
            while (numeral != 0) {
                numeral += 1
                number3 /= 10
            }
            return number3 % 10
        }
    }
}
