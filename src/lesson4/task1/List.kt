@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import kotlin.math.pow
import lesson3.task1.minDivisor

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun square(list: List<Double>): List<Double> = list.map { it * it }
fun abs(v: List<Double>): Double {
    val vector = square(v)
    return sqrt(vector.sum())
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else list.sum() / list.size

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val average = list.sum() / list.size
    for (i in list.indices) {
        list[i] -= average
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var polynomial = 0
    val xDouble = x.toDouble()
    for (i in p.indices) {
        polynomial += p[i] * xDouble.pow(i).toInt()
    }
    return polynomial
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 0
    for (i in list.indices) {
        sum += list[i]
        list[i] = sum
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */

fun factorize(n: Int): List<Int> {
    var number = n
    var e: Int
    val list: MutableList<Int> = mutableListOf()
    while (number != 1) {
        e = minDivisor(number)
        list.add(e)
        number /= e
    }
    return list.toList()
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val list = factorize(n)
    return list.joinToString(separator = "*")
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val list = mutableListOf<Int>()
    while (number > base) {
        list.add(number % base)
        number /= base
    }
    list.add(number)
    val invertedList = mutableListOf<Int>()
    for (i in list.indices) {
        invertedList.add(list[list.size - 1 - i])
    }
    return invertedList.toList()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val list = convert(n, base)
    val letters = listOf(
        "a", "b", "c", "d", "e", "f", "g", "h",
        "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
        "y", "z"
    )
    var str = ""
    var e: Int
    for (i in list.indices) {
        e = list[i]
        if (e < 10) {
            str += "$e"
            continue
        }
        str += letters[e - 10]
    }
    return str
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var decNS = 0
    val baseD = base.toDouble()
    var degree = digits.size - 1
    for (i in digits.indices) {
        decNS += digits[i] * baseD.pow(degree).toInt()
        degree -= 1
    }
    return decNS
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var decNS = 0
    val baseD = base.toDouble()
    val letters = listOf(
        "a", "b", "c", "d", "e", "f", "g", "h",
        "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
        "y", "z"
    )
    var e: Int
    for (i in str.indices) {
        e = letters.indexOf(str[i].toString())
        decNS += if (e != -1) {
            (e + 10) * baseD.pow(str.length - 1 - i).toInt()
        } else {
            (str[i].toInt() - '0'.toInt()) * baseD.pow(str.length - 1 - i).toInt()
        }
    }
    return decNS
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var number = n
    var str = ""
    while (number > 0) {
        when {
            number >= 1000 -> {
                str += "M"
                number -= 1000
            }
            number >= 900 -> {
                str += "CM"
                number -= 900
            }
            number >= 500 -> {
                str += "D"
                number -= 500
            }
            number >= 400 -> {
                str += "CD"
                number -= 400
            }
            number >= 100 -> {
                str += "C"
                number -= 100
            }
            number >= 90 -> {
                str += "XC"
                number -= 90
            }
            number >= 50 -> {
                str += "L"
                number -= 50
            }
            number >= 40 -> {
                str += "XL"
                number -= 40
            }
            number >= 10 -> {
                str += "X"
                number -= 10
            }
            number >= 9 -> {
                str += "IX"
                number -= 9
            }
            number >= 5 -> {
                str += "V"
                number -= 5
            }
            number >= 4 -> {
                str += "IV"
                number -= 4
            }
            number >= 1 -> {
                str += "I"
                number -= 1
            }
        }
    }
    return str
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val numbers = mutableListOf(
        "", "один ", "два ", "три ", "четыре ", "пять ",
        "шесть ", "семь ", "восемь ", "девять ",
        "десять ", "одиннадцать ", "двенадцать ",
        "тринадцать ", "четырнадцать ", "пятнадцать ",
        "шестнадцать ", "семнадцать ",
        "восемнадцать ", "девятнадцать "
    )
    val dozens = listOf(
        "", "", "двадцать ", "тридцать ", "сорок ",
        "пятьдесят ", "шестьдесят ",
        "семьдесят ", "восемдесят ", "девяносто "
    )
    val hundreds = listOf(
        "", "сто ", "двести ", "триста ", "четыреста ",
        "пятьсот ", "шестьсот ",
        "семьсот ", "восемьсот ", "девятьсот "
    )
    var numbr = n
    val strind: MutableList<String> = mutableListOf()
    val wer = arrayOf(numbers, dozens, hundreds)
    while (numbr != 0) {
        if (numbr % 100 < 20) {
            strind.add(numbers[numbr % 100])
            numbr /= 100
            strind.add(hundreds[numbr % 10])
            numbr /= 10
        } else {
            for (i in 0..2) {
                strind.add(wer[i][numbr % 10])
                numbr /= 10
            }
        }
        if (numbr != 0) {
            if (numbr % 100 !in 5..19) {
                when (numbr % 10) {
                    1 -> {
                        strind.add("тысяча ")
                        numbers[1] = "одна "
                    }
                    2 -> {
                        strind.add("тысячи ")
                        numbers[2] = "две "
                    }
                    in 3..4 -> strind.add("тысячи ")
                    else -> strind.add("тысяч ")
                }
            } else strind.add("тысяч ")
        }
    }
    var rus = ""
    for (i in strind.size - 1 downTo 0) {
        rus += strind[i]
    }
    return rus.trim()
}
