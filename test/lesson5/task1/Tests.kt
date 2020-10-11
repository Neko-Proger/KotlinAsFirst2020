package lesson5.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun shoppingListCostTest() {
        val itemCosts = mapOf(
            "Хлеб" to 50.0,
            "Молоко" to 100.0
        )
        assertEquals(
            150.0,
            shoppingListCost(
                listOf("Хлеб", "Молоко"),
                itemCosts
            )
        )
        assertEquals(
            150.0,
            shoppingListCost(
                listOf("Хлеб", "Молоко", "Кефир"),
                itemCosts
            )
        )
        assertEquals(
            0.0,
            shoppingListCost(
                listOf("Хлеб", "Молоко", "Кефир"),
                mapOf()
            )
        )
    }

    @Test
    @Tag("Example")
    fun filterByCountryCode() {
        val phoneBook = mutableMapOf(
            "Quagmire" to "+1-800-555-0143",
            "Adam's Ribs" to "+82-000-555-2960",
            "Pharmakon Industries" to "+1-800-555-6321"
        )

        filterByCountryCode(phoneBook, "+1")
        assertEquals(2, phoneBook.size)

        filterByCountryCode(phoneBook, "+1")
        assertEquals(2, phoneBook.size)

        filterByCountryCode(phoneBook, "+999")
        assertEquals(0, phoneBook.size)
    }

    @Test
    @Tag("Example")
    fun removeFillerWords() {
        assertEquals(
            "Я люблю Котлин".split(" "),
            removeFillerWords(
                "Я как-то люблю Котлин".split(" "),
                "как-то"
            )
        )
        assertEquals(
            "Я люблю Котлин".split(" "),
            removeFillerWords(
                "Я как-то люблю таки Котлин".split(" "),
                "как-то",
                "таки"
            )
        )
        assertEquals(
            "Я люблю Котлин".split(" "),
            removeFillerWords(
                "Я люблю Котлин".split(" "),
                "как-то",
                "таки"
            )
        )
    }

    @Test
    @Tag("Example")
    fun buildWordSet() {
        assertEquals(
            mutableSetOf("Я", "люблю", "Котлин"),
            buildWordSet("Я люблю Котлин".split(" "))
        )
        assertEquals(
            mutableSetOf("Котлин", "люблю", "Я"),
            buildWordSet("Я люблю люблю Котлин".split(" "))
        )
        assertEquals(
            mutableSetOf<String>(),
            buildWordSet(listOf())
        )
    }

    @Test
    @Tag("2")
    fun buildGrades() {
        assertEquals(
            mapOf<Int, List<String>>(1 to listOf()),
            buildGrades(mapOf("" to 1))
        )
        assertEquals(
            mapOf<Int, List<String>>(),
            buildGrades(mapOf())
        )
        assertEquals(
            mapOf(5 to listOf("Михаил", "Семён"), 3 to listOf("Марат")),
            buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
                .mapValues { (_, v) -> v.sorted() }
        )
        assertEquals(
            mapOf(3 to listOf("Марат", "Михаил", "Семён")),
            buildGrades(mapOf("Марат" to 3, "Семён" to 3, "Михаил" to 3))
                .mapValues { (_, v) -> v.sorted() }
        )
    }

    @Test
    @Tag("2")
    fun containsIn() {
        assertTrue(containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")))
        assertFalse(containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")))
    }

    @Test
    @Tag("2")
    fun subtractOf() {
        val from = mutableMapOf("a" to "z", "b" to "c")

        subtractOf(from, mapOf())
        assertEquals(mapOf("a" to "z", "b" to "c"), from)

        subtractOf(from, mapOf("b" to "z"))
        assertEquals(mapOf("a" to "z", "b" to "c"), from)

        subtractOf(from, mapOf("a" to "z"))
        assertEquals(mapOf("b" to "c"), from)
    }

    @Test
    @Tag("2")
    fun whoAreInBoth() {
        assertEquals(
            emptyList<String>(),
            whoAreInBoth(emptyList(), emptyList())
        )
        assertEquals(
            listOf("Marat"),
            whoAreInBoth(listOf("Marat", "Mikhail"), listOf("Marat", "Kirill"))
        )
        assertEquals(
            emptyList<String>(),
            whoAreInBoth(listOf("Marat", "Mikhail"), listOf("Sveta", "Kirill"))
        )
    }

    @Test
    @Tag("3")
    fun mergePhoneBooks() {
        assertEquals(
            mapOf("Emergency" to "112"),
            mergePhoneBooks(
                mapOf("Emergency" to "112"),
                mapOf("Emergency" to "112")
            )
        )
        assertEquals(
            mapOf("Emergency" to "112", "Police" to "02"),
            mergePhoneBooks(
                mapOf("Emergency" to "112"),
                mapOf("Emergency" to "112", "Police" to "02")
            )
        )
        assertEquals(
            mapOf("Emergency" to "112, 911", "Police" to "02"),
            mergePhoneBooks(
                mapOf("Emergency" to "112"),
                mapOf("Emergency" to "911", "Police" to "02")
            )
        )
        assertEquals(
            mapOf("Emergency" to "112, 911", "Fire department" to "01", "Police" to "02"),
            mergePhoneBooks(
                mapOf("Emergency" to "112", "Fire department" to "01"),
                mapOf("Emergency" to "911", "Police" to "02")
            )
        )
    }

    @Test
    @Tag("4")
    fun averageStockPrice() {
        assertEquals(
            mapOf<String, Double>(),
            averageStockPrice(listOf())
        )
        assertEquals(
            mapOf("MSFT" to 100.0, "NFLX" to 40.0),
            averageStockPrice(listOf("MSFT" to 100.0, "NFLX" to 40.0))
        )
        assertEquals(
            mapOf("MSFT" to 150.0, "NFLX" to 40.0),
            averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
        )
        assertEquals(
            mapOf("MSFT" to 150.0, "NFLX" to 45.0),
            averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0, "NFLX" to 50.0))
        )
    }

    @Test
    @Tag("4")
    fun findCheapestStuff() {
        assertNull(
            findCheapestStuff(
                mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
                "торт"
            )
        )
        assertEquals(
            "Мария",
            findCheapestStuff(
                mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
                "печенье"
            )
        )
    }

    @Test
    @Tag("3")
    fun canBuildFrom() {
        assertFalse(canBuildFrom(listOf('a', 'b', 'o'), ","))
        assertFalse(canBuildFrom(emptyList(), "foo"))
        assertTrue(canBuildFrom(listOf('a', 'b', 'o'), "baobab"))
        assertFalse(canBuildFrom(listOf('a', 'm', 'r'), "Marat"))
    }

    @Test
    @Tag("4")
    fun extractRepeats() {
        assertEquals(
            emptyMap<String, Int>(),
            extractRepeats(emptyList())
        )
        assertEquals(
            mapOf("a" to 2),
            extractRepeats(listOf("a", "b", "a"))
        )
        assertEquals(
            emptyMap<String, Int>(),
            extractRepeats(listOf("a", "b", "c"))
        )
    }

    @Test
    @Tag("3")
    fun hasAnagrams() {
        assertTrue(hasAnagrams(listOf("", "a", "a")))
        assertFalse(hasAnagrams(emptyList()))
        assertTrue(hasAnagrams(listOf("рот", "свет", "тор")))
        assertFalse(hasAnagrams(listOf("рот", "свет", "код", "дверь")))
        assertFalse(hasAnagrams(listOf("поле", "полено")))
        assertTrue(hasAnagrams(listOf("лунь", "нуль")))
    }

    @Test
    @Tag("5")
    fun propagateHandshakes() {
        assertEquals(
            mapOf(
                "Marat" to setOf("Mikhail", "Sveta"),
                "Sveta" to setOf("Mikhail"),
                "Mikhail" to setOf()
            ),
            propagateHandshakes(
                mapOf(
                    "Marat" to setOf("Sveta"),
                    "Sveta" to setOf("Mikhail")
                )
            )
        )
        assertEquals(
            mapOf(
                "Marat" to setOf("Mikhail", "Sveta"),
                "Sveta" to setOf("Marat", "Mikhail"),
                "Mikhail" to setOf("Sveta", "Marat"),
                "Friend" to setOf("GoodGnome"),
                "EvilGnome" to setOf(),
                "GoodGnome" to setOf()
            ),
            propagateHandshakes(
                mapOf(
                    "Marat" to setOf("Mikhail", "Sveta"),
                    "Sveta" to setOf("Marat"),
                    "Mikhail" to setOf("Sveta"),
                    "Friend" to setOf("GoodGnome"),
                    "EvilGnome" to setOf()
                )
            )
        )
    }

    @Test
    @Tag("6")
    fun findSumOfTwo() {
        assertEquals(
            Pair(0, 1),
            findSumOfTwo(listOf(0, 1), 1)
        )
        assertEquals(
            Pair(0, 1),
            findSumOfTwo(listOf(1, 0), 1)
        )
        assertEquals(
            Pair(0, 2),
            findSumOfTwo(listOf(0, 1, 0, 0), 0)
        )
        assertEquals(
            Pair(-1, -1),
            findSumOfTwo(listOf(0, 0), 1)
        )
        assertEquals(
            Pair(0, 1),
            findSumOfTwo(listOf(0, 0), 0)
        )
        assertEquals(
            Pair(-1, -1),
            findSumOfTwo(emptyList(), 1)
        )
        assertEquals(
            Pair(0, 2),
            findSumOfTwo(listOf(1, 2, 3), 4)
        )
        assertEquals(
            Pair(-1, -1),
            findSumOfTwo(listOf(1, 2, 3), 6)
        )
    }

    @Test
    @Tag("8")
    fun bagPacking() {
        assertEquals(
            setOf(
                "7", "6", "4", "3", "1", "0"
            ),
            bagPacking(
                mapOf("0" to (1 to 1),
                    "1" to (1 to 1),
                    "2" to (155 to 1),
                    "3" to (1 to 1),
                    "4" to (1 to 1),
                    "5" to (152 to 2),
                    "6" to (457 to 4),
                    "7" to (1 to 1)),

                540
            )
        )
        assertEquals(
            setOf(  "23",
                "22",
                "21",
                "20",
                "19",
                "18",
                "17",
                "15",
                "14",
                "13",
                "12",
                "11",
                "10",
                "9",
                "8",
                "7",
                "6",
                "5",
                "4",
                "3",
                "2",
                "1",
                "0"),
            bagPacking(
                mapOf("0" to (1 to 1),
                    "1" to (1 to 1),
                    "2" to (1 to 1),
                    "3" to (1 to 1),
                    "4" to (1 to 1),
                    "5" to (1 to 1),
                    "6" to (1 to 1),
                    "7" to (1 to 1),
                    "8" to (1 to 1),
                    "9" to (1 to 1),
                    "10" to (229 to 229),
                    "11" to (1 to 1),
                    "12" to (2 to 1),
                    "13" to (343 to 2),
                    "14" to (148 to 386),
                    "15" to (129 to 2),
                    "16" to (210 to 1),
                    "17" to (201 to 149),
                    "18" to (436 to 349),
                    "19" to (495 to 67),
                    "20" to (149 to 212),
                    "21" to (102 to 148), "22" to (174 to 12),
                    "23" to (149 to 148),),

                2728
            )
        )
        assertEquals(
            setOf("4", "3","2","0"),
            bagPacking(
                mapOf("0" to (1 to 1),
                    "1" to (480 to 2),
                    "2" to (1 to 1),
                    "3" to (1 to 1),
                    "4" to (1 to 1), ),

                456
            )
        )
        assertEquals(
            setOf("7", "5", "4", "3", "2","1", "0"),
            bagPacking(
                mapOf("0" to (1 to 1),
                    "1" to (1 to 1),
                    "2" to (34 to 1),
                    "3" to (474 to 1),
                    "4" to (349 to 1),
                    "5" to (289 to 1),
                    "6" to (384 to 1),
                    "7" to (1 to 1)),

                1525
            )
        )
        assertEquals(
            setOf("0"),
            bagPacking(
                mapOf("0" to (2 to 3), "1" to (1 to 1), "2" to (1 to 1)),
                2
            )
        )
        assertEquals(
            setOf("1", "0"),
            bagPacking(
                mapOf("0" to (1 to 1), "1" to (1 to 1)),
                2
            )
        )
        assertEquals(
            setOf("72", "1"),
            bagPacking(
                mapOf("0" to (1 to 1), "1" to (1 to 25), "2" to (1 to 1), "3" to (1 to 1), "4" to (1 to 1),
                    "5" to (1 to 1),
                    "6" to (1 to 1),
                    "722" to (1 to 1),
                    "8" to (2 to 458),
                    "9" to (1 to 1),
                    "10" to (1 to 1),
                    "11" to (1 to 1),
                    "72" to (1 to 438),
                    "13" to (1 to 1),
                    "14" to (1 to 1),
                    "15" to (1 to 1)),

                2
            )
        )
        assertEquals(
            setOf("3", "0"),
            bagPacking(
                mapOf("0" to (1 to 1), "1" to (2 to 2), "2" to (1 to 1), "3" to (1 to 2)),
                2
            )
        )
        assertEquals(
            setOf("2", "1"),
            bagPacking(
                mapOf("0" to (1 to 1), "1" to (1 to 2), "2" to (1 to 2)),
                2
            )
        )
        assertEquals(
            setOf("Кубок"),
            bagPacking(
                mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
                850
            )
        )
        assertEquals(
            emptySet<String>(),
            bagPacking(
                mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
                450
            )
        )
    }

}
