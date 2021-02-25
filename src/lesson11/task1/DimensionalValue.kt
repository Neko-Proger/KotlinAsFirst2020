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
            val x = ddimension.split("")
            for (dis in DimensionPrefix.values()) {
                if (x[1] == dis.abbreviation) {
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
            val x = ddimension.trim().split("")
            for (dis in Dimension.values()) {
                if (x[x.size - 2] == dis.abbreviation) {
                    return dis
                }
            }
            throw IllegalArgumentException()
        }

    /**
     * Конструктор из строки. Формат строки: значение пробел размерность (1 Kg, 3 mm, 100 g и так далее).
     */
    constructor(s: String) : this(0.0, s) {
        val x = s.split(" ")
        val dimen = x[1].toList()
        if (dimen.size == 2) {
            for (dis in DimensionPrefix.values()) {
                if (dimen[0].toString() == dis.abbreviation) {
                    vvalue = x[0].toDouble() * dis.multiplier
                    ddimension = dimen[1].toString()
                }
            }
        } else {
            vvalue = x[0].toDouble()
            ddimension = x[1]
        }

    }


    /**
     * Сложение с другой величиной. Если базовая размерность разная, бросить IllegalArgumentException
     * (нельзя складывать метры и килограммы)
     */
    operator fun plus(other: DimensionalValue): DimensionalValue {
        val x = DimensionalValue(vvalue, ddimension)
        val otherX = DimensionalValue(other.vvalue, other.ddimension)
        if (x.dimension == otherX.dimension) {
            val r1 = Dimension.valueOf(x.dimension.toString())
            val r = r1.abbreviation
            return DimensionalValue(x.value + otherX.value, r)
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
        val x = DimensionalValue(vvalue, ddimension)
        val otherX = DimensionalValue(other.vvalue, other.ddimension)
        if (x.dimension == otherX.dimension) {
            val r1 = Dimension.valueOf(x.dimension.toString())
            val r = r1.abbreviation
            return DimensionalValue(x.value - otherX.value, r)
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
        val x = DimensionalValue(vvalue, ddimension)
        val otherX = DimensionalValue(other.vvalue, other.ddimension)
        if (x.dimension == otherX.dimension) {
            val r1 = Dimension.valueOf(x.dimension.toString())
            val r = r1.abbreviation
            return x.value / otherX.value
        }
        throw IllegalArgumentException()
    }

    /**
     * Сравнение на равенство
     */


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as DimensionalValue
        if (other.vvalue == vvalue) return true
        return false
    }

    /**
     * Сравнение на больше/меньше. Если базовая размерность разная, бросить IllegalArgumentException
     */
    override fun compareTo(other: DimensionalValue): Int {
        val x = DimensionalValue(vvalue, ddimension)
        val otherX = DimensionalValue(other.vvalue, other.ddimension)
        if (x.dimension == otherX.dimension) {
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

/***
 * Приставка размерности. Опять-таки можно добавить новые варианты (деци-, санти-, мега-, ...), но нельзя убирать
 */
enum class DimensionPrefix(val abbreviation: String, val multiplier: Double) {
    KILO("K", 1000.0),
    MILLI("m", 0.001),
    SANTI("s",0.01),
    DECI("d", 0.1);
}