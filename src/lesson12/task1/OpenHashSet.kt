@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

/**
 * Класс "хеш-таблица с открытой адресацией"
 *
 * Общая сложность задания -- сложная, общая ценность в баллах -- 20.
 * Объект класса хранит данные типа T в виде хеш-таблицы.
 * Хеш-таблица не может содержать равные по equals элементы.
 * Подробности по организации см. статью википедии "Хеш-таблица", раздел "Открытая адресация".
 * Методы: добавление элемента, проверка вхождения элемента, сравнение двух таблиц на равенство.
 * В этом задании не разрешается использовать библиотечные классы HashSet, HashMap и им подобные,
 * а также любые функции, создающие множества (mutableSetOf и пр.).
 *
 * В конструктор хеш-таблицы передаётся её вместимость (максимальное количество элементов)
 */
class OpenHashSet<T>(val capacity: Int) {// time parametr jave generic //tipe t

    /**
     * Массив для хранения элементов хеш-таблицы
     */
    internal val elements = Array<Pair<Any?, Boolean>>(capacity) { Pair(null, false) }

    /**
     * Число элементов в хеш-таблице
     */
    val size: Int
        get() {
            var sum = 0
            for (i in 0 until capacity) {
                if (!elements[i].second) break
                if (elements[i].first != null) sum += 1
            }
            return sum
        }

    fun element(): Set<Any?> {
        val e = mutableSetOf<Any?>()
        for (i in 0 until capacity){
            e.add(elements[i].first)
        }
        return e
    }


    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean {
        for (lib in elements) {
            if (lib.first != null) return false
        }
        return true
    }

    /**
     * Добавление элемента.
     * Вернуть true, если элемент был успешно добавлен,
     * или false, если такой элемент уже был в таблице, или превышена вместимость таблицы.
     */
    fun add(element: T): Boolean {
        for (i in 0 until capacity) {
            if (elements[i].first == element) return false
            if (elements[i].first == null) {
                elements[i] = Pair(element, true)
                return true
            }
        }
        return false
    }


    /**
     * Проверка, входит ли заданный элемент в хеш-таблицу
     */
    operator fun contains(element: T): Boolean {
        for (i in 0 until capacity) {
            if (!elements[i].second) return false
            if (elements[i].first == element) return true
        }
        return false
    }

    /**
     * Таблицы равны, если в них одинаковое количество элементов,
     * и любой элемент из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean {
        other as OpenHashSet<*>
        for (i in other.elements) {
            if (i !in elements && i.first != null) return false
        }
        for (i in elements) {
            if (i !in other.elements && i.first != null) return false
        }
        return true
    }


    fun delete(element: T): Boolean {
        for (i in 0 until capacity) {
            if (!elements[i].second) return false
            if (elements[i].first == element) {
                elements[i] = Pair(null, true)
                gdr()
                return true
            }
        }
        return false
    }

    private fun gdr() {
        var sum = 0
        for (i in 0 until capacity) {
            if (elements[i] == Pair(null, true)) sum += 1
        }
        if (sum > capacity / 2) {
            val hasList1: MutableList<Any?> = mutableListOf()
            for (i in 0 until capacity) {
                if (elements[i].first != null && elements[i].second) hasList1.add(elements[i].first)
            }
            for (i in 0 until capacity) {
                elements[i] = Pair(null, false)
            }
            for (i in 0 until hasList1.size) {
                elements[i] = Pair(hasList1[i], true)
            }
        }
    }
}