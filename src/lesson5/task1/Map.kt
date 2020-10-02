@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1
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
    costs: Map<String, Double>
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
    countryCode: String
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
    vararg fillerWords: String
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
    val mutableList2 = mutableListOf<String>()
    val mutableList3 = mutableListOf<String>()
    val mutableList4 = mutableListOf<String>()
    val mutableList5 = mutableListOf<String>()
    for ((name, appraisal) in grades) {
        when (appraisal) {
            2 -> mutableList2.add(name)
            3 -> mutableList3.add(name)
            4 -> mutableList4.add(name)
            5 -> mutableList5.add(name)
        }
    }
    val mutableMap = mutableMapOf<Int, List<String>>()
    val array = arrayOf(
        mutableList2, mutableList3, mutableList4, mutableList5
    )
    for (i in 3 downTo 0) {
        if (array[i].isNotEmpty()) mutableMap[i + 2] = array[i]
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
    for ((key, _) in a) {
        if (b[key] != a[key]) return false
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
    for ((key, _) in b) {
        if (b[key] == a[key]) a.remove(key)
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
    val mutableC = mutableListOf<String>()
    for (i in a) {
        if (i !in mutableC && i in b) mutableC.add(i)
    }
    return mutableC
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
    for ((key, _) in mapB) {
        if (mapA[key] != mapB[key] && mapA[key] != null && mapB[key] != null) {
            phoneBook[key] = "${mapA[key]}, ${mapB[key]}"
            continue
        }
        phoneBook[key] = mapB[key] ?: error("")
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
//среднее значение вычисляется только для двух элементов исправить
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val stockMap = mutableMapOf<String, Int>()
    val averageCost = mutableMapOf<String, Double>()
    for ((stock, price) in stockPrices) {
        if (stock !in stockMap && stock in averageCost) stockMap[stock] = 1
        if (stock in stockMap && stock in averageCost) stockMap[stock] =
            stockMap[stock]!! + 1
        if (averageCost[stock] != null) {
            averageCost[stock] = averageCost[stock]!! + price
            continue
        }
        averageCost[stock] = price
    }
    for ((stock, price) in averageCost) {
        if (stock in stockMap) averageCost[stock] = price / stockMap[stock]!!
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
    var min = -1.0
    var product = ""
    for ((Name, couple) in stuff) {
        val (view, price) = couple
        if ((price < min || min == -1.0) && view == kind) {
            min = price
            product = Name
        }
    }
    if (product == "") return null
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
    for (i in word) {
        if (i !in chars) return false
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
    for (i in words.indices - 1) {
        for (i1 in i + 1 until words.size) {
            if (words[i].length != words[i1].length) continue
            val str1 = words[i].toCharArray().toList().sorted().joinToString()
            val str2 = words[i1].toCharArray().toList().sorted().joinToString()
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
    val li = list.sorted()
    for (i in li.indices) {
        if (i > number) break
        for (i2 in i until li.size) {
            when {
                i + li[i2] > number -> break
                i + li[i2] == number -> return Pair(li.indexOf(i), i2)
            }
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
fun sortMap(map: Map<String, Pair<Int, Int>>): Map<String, Pair<Int, Int>> {
    val map1 = mutableMapOf<String, Pair<Int, Int>>()
    val nameList = mutableListOf<String>()
    val weightList = mutableListOf<Int>()
    val priceList = mutableListOf<Int>()
    for ((str, i) in map) {
        val (weight, price) = i
        nameList.add(str)
        weightList.add(weight)
        priceList.add(price)
    }
    var interName: String
    var interWeight: Int
    var interPrice: Int
    for (i in 0 until nameList.size) {
        for (i2 in 0 until nameList.size - i) {
            if (weightList[i] > weightList[i2] || priceList[i] > priceList[i2]) {
                interName = nameList[i]
                interWeight = weightList[i]
                interPrice = priceList[i]
                nameList[i] = nameList[i2]
                weightList[i] = weightList[i2]
                priceList[i] = priceList[i2]
                nameList[i2] = interName
                weightList[i2] = interWeight
                priceList[i2] = interPrice
            }

        }
    }
    var index = 0
    for (i in nameList) {
        map1[i] = Pair(weightList[index], priceList[index])
        index += 1
    }
    return map1
}

fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val map = sortMap(treasures)
    val sumSet = mutableSetOf<String>()
    var sum = 0
    var interSum = 0
    for ((str, par) in map) {
        val (weight, price) = par
        if (weight > capacity) continue
        if (interSum + weight >= capacity) break
        sum += price
        interSum += weight
        sumSet.add(str)
    }
    return sumSet
}
