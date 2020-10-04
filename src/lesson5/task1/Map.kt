@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import ru.spbstu.wheels.toMap

// Урок 5: ассоциативные массивы и множества
// Максимальное количество баллов = 14
// Рекомендуемое количество баллов = 9
// Вместе с предыдущими уроками = 33/47

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>,
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String,
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String,
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая (2 балла)
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val mutableMap = mutableMapOf<Int, MutableList<String>>()
    for ((name, appraisal) in grades) {
        if (appraisal !in mutableMap) {
            mutableMap[appraisal] = mutableListOf()
        }
        val e = mutableMap[appraisal]!!
        e.add(name)
    }
    return mutableMap
}

/**
 * Простая (2 балла)
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for ((key, value) in a) {
        if (b[key] != value) return false
    }
    return true
}

/**
 * Простая (2 балла)
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): MutableMap<String, String> {
    for ((key, value) in b) {
        if (value == a[key]) a.remove(key)
    }
    return a
}

/**
 * Простая (2 балла)
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val mutableC = mutableSetOf<String>()
    for (i in a) {
        if (i !in mutableC && i in b) mutableC.add(i)
    }
    return mutableC.toList()
}

/**
 * Средняя (3 балла)
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val phoneBook = mapA.toMutableMap()
    for ((key, value) in mapB) {
        if (mapA[key] != value && mapA[key] != null) {
            phoneBook[key] = "${mapA[key]}, $value"
            continue
        }
        if (mapB[key] != null) phoneBook[key] = mapB[key].toString()
    }
    return phoneBook
}

/**
 * Средняя (4 балла)
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val averageString = mutableListOf<String>()
    val averageDouble = mutableListOf<Double>()
    val averageIndex = mutableListOf<Double>()
    val averageCost = mutableMapOf<String, Double>()
    for ((stock, price) in stockPrices) {
        if (stock !in averageString) {
            averageString.add(stock)
            averageDouble.add(price)
            averageIndex.add(1.0)
        } else {
            averageDouble[averageString.indexOf(stock)] += price
            averageIndex[averageString.indexOf(stock)] += 1.0
        }
    }
    for (i in averageString.indices) {
        if (averageIndex[i] > 1.0) averageDouble[i] /= averageIndex[i]
        averageCost[averageString[i]] = averageDouble[i]
    }
    return averageCost
}

/**
 * Средняя (4 балла)
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var min = Double.POSITIVE_INFINITY
    var product: String? = null
    for ((name, couple) in stuff) {
        val (view, price) = couple
        if ((price < min || min == -1.0) && view == kind) {
            min = price
            product = name
        }
    }
    return product
}

/**
 * Средняя (3 балла)
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    val char = chars.joinToString(separator = "", postfix = "").toLowerCase().toList()
    for (i in word) {
        if (i.toLowerCase() !in char) return false
    }
    return true
}

/**
 * Средняя (4 балла)
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val mutableMap = mutableMapOf<String, Int>()
    for (i in list) {
        if (i in mutableMap) mutableMap[i] = mutableMap[i]!! + 1
        else mutableMap[i] = 1
    }
    val repeatLetters = mutableMapOf<String, Int>()
    for ((letters, repeat) in mutableMap) {
        if (repeat > 1) repeatLetters[letters] = repeat
    }

    return repeatLetters
}

/**
 * Средняя (3 балла)
 *
 * Для заданного списка слов определить, содержит ли он анаграммы.
 * Два слова здесь считаются анаграммами, если они имеют одинаковую длину
 * и одно можно составить из второго перестановкой его букв.
 * Скажем, тор и рот или роза и азор это анаграммы,
 * а поле и полено -- нет.
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    for (i in 0 until words.size) {
        for (i1 in i + 1 until words.size) {
            if (words[i].length != words[i1].length) continue
            val str1 = words[i].toList().sorted()
            val str2 = words[i1].toList().sorted()
            if (str1 == str2) return true
        }
    }
    return false
}

/**
 * Сложная (5 баллов)
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 *
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Оставлять пустой список знакомых для людей, которые их не имеют (см. EvilGnome ниже),
 * в том числе для случая, когда данного человека нет в ключах, но он есть в значениях
 * (см. GoodGnome ниже).
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta"),
 *       "Friend" to setOf("GoodGnome"),
 *       "EvilGnome" to setOf()
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat"),
 *          "Friend" to setOf("GoodGnome"),
 *          "EvilGnome" to setOf(),
 *          "GoodGnome" to setOf()
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> = TODO()

/**
 * Сложная (6 баллов)
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    for (i in 0 until list.size - 1) {
        for (i1 in i + 1 until list.size) {
            if (list[i] + list[i1] == number) return Pair(i, i1)
        }
    }
    return Pair(-1, -1)
}

/**
 * Очень сложная (8 баллов)
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bag(
    max: Int,
    weight: List<Int>,
    name: List<String>,
    price: List<Int>,
    arr: Int,
    maxE: Int, //максимальный элемент
    capacity: Int,
    maxWeight: Int,
): List<Any?> {
    var interPrice = 0
    var interWeight = 0
    val nameMax = mutableListOf<String>()
    val interName = mutableListOf<String>()
    val r = mutableListOf<Int>()
    val d = mutableListOf<Int>()
    var maximum = max
    var reductionWeight = 0
    var indexPrice = 0
    var maximumWeight = maxWeight
    for (i in 0 until arr) {
        r.add(maxE - i)
        d.add(maxE - i)
    }
    lim@ while (d[0] != d[1]) {
        for (i in 0 until arr) {
            interPrice += price[d[i]]
            interWeight += weight[d[i]]
            interName.add(name[d[i]])
        }
        if (capacity >= interWeight && maximum <= interPrice) { //|
            nameMax.clear()
            for (i in interName) {
                nameMax.add(i)
            }
            maximum = interPrice
            maximumWeight = interWeight
            reductionWeight = interPrice
            indexPrice = 0
        } else if (reductionWeight != 0 && reductionWeight > interPrice) {
            if (indexPrice < maxE * 2) indexPrice += 1
            else break@lim
        }
        interPrice = 0
        interWeight = 0
        interName.clear()
        for (i in r.size - 1 downTo 0) {
            when (i) {
                r.size - 1 -> {
                    if (d[i] - 1 > -1) {
                        d[i] -= 1
                        break
                    } else {
                        if (d[i - 1] - 1 != d[i]) {
                            d[i] = d[i - 1] - 2
                        } else d[i] = d[i - 1] - 1
                    }
                }
                0 -> {
                    if (d[i] - 1 > d[i + 1]) d[i] -= 1
                    else break@lim
                }
                else -> {
                    if (d[i] - 1 > d[i + 1]) {
                        d[i] -= 1
                        break
                    } else {
                        if (d[i - 1] - 1 != d[i]) {
                            d[i] = d[i - 1] - 2
                        } else d[i] = d[i - 1] - 1
                    }
                }
            }
        }
    }
    val str = nameMax.joinToString(separator = " ", postfix = " ")
    return listOf(maximum, str, maximumWeight)
}

fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val map = treasures
        .entries
        .sortedBy { (_, value) -> value.second }.toMap()
    val weight = mutableListOf<Int>()
    val price = mutableListOf<Int>()
    val name = mutableListOf<String>()
    for ((nam, priceWeight) in map) {
        name.add(nam)
        val (paraWeight, paraPrice) = priceWeight
        price.add(paraPrice)
        weight.add(paraWeight)
    }
    var max = 0
    var minWeight = Int.MAX_VALUE
    var nameMaximum = ""
    if (weight.sum() <= capacity) {
        return name.toSet()
    }
    for (i in 2..price.size) {
        val inter = bag(max, weight, name, price, i, price.size - 1, capacity, minWeight)
        val maxw = inter[0].toString().toInt()
        val maxName = inter[1].toString()
        val minWei = inter[2].toString().toInt()
        if (maxName == " ") break
        if (maxw >= max && (minWeight >= minWei || capacity >= minWei)) {
            max = maxw
            nameMaximum = maxName
            minWeight = minWei
        }
    }
    for (i in price.size - 1 downTo 0) {
        if (price[i] >= max && weight[i] <= capacity) {
            max = price[i]
            nameMaximum = name[i]
        }
    }
    if (nameMaximum.trim() == "") return emptySet()
    if (" " !in nameMaximum) return setOf(nameMaximum)
    val nameToList = nameMaximum.toList()
    val nameSet = mutableSetOf<String>()
    var terem = ""
    for (i in nameToList) {
        if (i.toString() != " ") terem += i.toString()
        else {
            nameSet.add(terem)
            terem = ""
        }
    }
    return nameSet
}
