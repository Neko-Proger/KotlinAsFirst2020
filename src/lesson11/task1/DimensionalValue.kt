@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import kotlin.RuntimeException as RuntimeException1

/**
 * Класс "Величина с размерностью".
 *
 * Предназначен для представления величин вроде "6 метров" или "3 килограмма"
 * Общая сложность задания - средняя, общая ценность в баллах -- 18
 * Величины с размерностью можно складывать, вычитать, делить, менять им знак.
 * Их также можно умножать и делить на число.
 *
 * В конструктор передаётся вещественное значение и строковая размерность.
 * Строковая размерность может:
 * - либо строго соответствовать одной из abbreviation класса Dimension (m, g)
 * - либо соответствовать одной из приставок, к которой приписана сама размерность (Km, Kg, mm, mg)
 * - во всех остальных случаях следует бросить IllegalArgumentException
, , value: Double*/
class DimensionalValue(value: Double, dimension: String) : Comparable<DimensionalValue> {
    var vvalue: Double
    var ddimension: String

    init {
        vvalue = value
        ddimension = dimension
    }

    /**
     * Величина с БАЗОВОЙ размерностью (например для 1.0Kg следует вернуть результат в граммах -- 1000.0)
     */
    val value: Double
        get() {
            for (dis in Dimension.values()) {
                if (ddimension == dis.abbreviation) {
                    return vvalue
                }
            }
            var e: String = ""
            if (ddimension.length - 1 > 0) {
                for (i in 0..ddimension.length - 2) {
                    e += ddimension[i]
                }
            }
            for (dis in DimensionPrefix.values()) {
                if (e == dis.abbreviation) {//поправить
                    return vvalue * dis.multiplier
                }
            }
            throw IllegalArgumentException()
        }


    /**
     * БАЗОВАЯ размерность (опять-таки для 1.0Kg следует вернуть GRAM)
     */
    val dimension: Dimension
        get() {
            val e = ddimension[ddimension.length - 1].toString()
            for (dis in Dimension.values()) {
                if (ddimension[ddimension.length - 1].toString() == dis.abbreviation) {
                    return dis
                }
            }
            throw IllegalArgumentException()
        }

    /**
     * Конструктор из строки. Формат строки: значение пробел размерность (1 Kg, 3 mm, 100 g и так далее).
     */

    constructor(s: String) : this(s.split(" ")[0].toDouble(), s.split(" ")[1]) {
        vvalue = value
        ddimension = Dimension.valueOf(dimension.toString()).abbreviation

    }

    /**
     * Сложение с другой величиной. Если базовая размерность разная, бросить IllegalArgumentException
     * (нельзя складывать метры и килограммы)
     */
    operator fun plus(other: DimensionalValue): DimensionalValue {//
        if (ddimension == other.ddimension) {
            return DimensionalValue(vvalue + other.vvalue, ddimension)
        }
        throw IllegalArgumentException()
    }

    /**
     * Смена знака величины
     */
    operator fun unaryMinus(): DimensionalValue = DimensionalValue(-vvalue, ddimension)

    /**
     * Вычитание другой величины. Если базовая размерность разная, бросить IllegalArgumentException
     */
    operator fun minus(other: DimensionalValue): DimensionalValue {
        if (dimension == other.dimension) {
            val r1 = Dimension.valueOf(dimension.toString())
            val r = r1.abbreviation
            return DimensionalValue(value - other.value, r)
        }
        throw IllegalArgumentException()
    }

    /**
     * Умножение на число
     */
    operator fun times(other: Double): DimensionalValue = DimensionalValue(vvalue * other, ddimension)

    /**
     * Деление на число
     */
    operator fun div(other: Double): DimensionalValue = DimensionalValue(vvalue / other, ddimension)

    /**
     * Деление на другую величину. Если базовая размерность разная, бросить IllegalArgumentException
     */
    operator fun div(other: DimensionalValue): Double {
        if (dimension == other.dimension) {
            val r1 = Dimension.valueOf(dimension.toString())
            val r = r1.abbreviation
            return value / other.value
        }
        throw IllegalArgumentException()
    }

    /**
     * Сравнение на равенство
     */


    override fun equals(other: Any?): Boolean {
        other as DimensionalValue
        if (other.vvalue == vvalue) return true
        return false
    }

    /**
     * Сравнение на больше/меньше. Если базовая размерность разная, бросить IllegalArgumentException
     */
    override fun compareTo(other: DimensionalValue): Int {
        val e = ddimension
        val t = other.ddimension
        if (ddimension == other.ddimension) {
            return vvalue.toInt() - other.vvalue.toInt()
        }
        throw IllegalArgumentException()
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

/**
 * Размерность. В этот класс можно добавлять новые варианты (секунды, амперы, прочие), но нельзя убирать
 */
enum class Dimension(val abbreviation: String) {
    METER("m"),
    GRAM("g");
}

/**
 * Приставка размерности. Опять-таки можно добавить новые варианты (деци-, санти-, мега-, ...), но нельзя убирать
 */
enum class DimensionPrefix(val abbreviation: String, val multiplier: Double) {
    KILO("K", 1000.0),
    MILLI("m", 0.001),
    SANTI("s", 0.01),
    DECI("d", 0.1);
}