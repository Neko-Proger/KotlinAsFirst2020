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
class OpenHashSet<T>(val capacity: Int) {

    /**
     * Массив для хранения элементов хеш-таблицы
     */
    internal val elements = Array<Any?>(capacity) { null }
    private val liberty = Array<Boolean>(capacity) { false }

    /**
     * Число элементов в хеш-таблице
     */
    val size: Int
        get() {
            var sum = 0
            for (i in 0 until capacity) {
                if (!liberty[i]) break
                if (elements[i] != null) sum += 1
            }
            return sum
        }

    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean {
        for (lib in elements) {
            if (lib != null) return false
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
            if (elements[i] == element) return false
            if (elements[i] == null) {
                elements[i] = element
                liberty[i] = true
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
            if (!liberty[i]) return false
            if (elements[i] == element) return true
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
            if (i !in elements && i != null) return false
        }
        for (i in elements) {
            if (i !in other.elements && i != null) return false
        }
        return true
    }


    fun delete(element: T): Boolean {
        for (i in 0 until capacity) {
            if (!liberty[i]) return false
            if (elements[i] == element) {
                elements[i] = null
                gdr()
                return true
            }
        }
        return false
    }

    private fun gdr() {
        var sum = 0
        for (i in 0 until capacity) {
            if (liberty[i] && elements[i] == null) sum += 1
        }
        if (sum > capacity / 2) {
            val hasList1: MutableList<Any?> = mutableListOf()
            for (i in 0 until capacity) {
                if (elements[i] != null && liberty[i]) hasList1.add(elements[i])
            }
            for (i in 0 until capacity) {
                elements[i] = null
                liberty[i] = false
            }
            for (i in 0 until hasList1.size) {
                elements[i] = hasList1[i]
                liberty[i] = true
            }
        }
    }
}