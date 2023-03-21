package view

import domain.stone.Color
import domain.stone.Point
import domain.stone.Stone

object InputView {

    private const val ERROR_INPUT_FORM = "[ERROR] 입력 형태가 올바르지 않습니다."
    private const val TURN_MESSAGE = "%s의 차례입니다. %s"
    private const val REQUEST_POINT_MESSAGE = "위치를 입력하세요: "
    private const val EMPTY_STRING = ""
    private const val LAST_STONE_POINT_MESSAGE = "(마지막 돌의 위치:%c%d)"
    private const val CONVERTING_BASE_NUMBER = 64

    private val pointRegex = """^[A-Z]((1)[0-5]|[1-9])""".toRegex()

    private fun Char.toCoordinate(): Int = this.code - CONVERTING_BASE_NUMBER

    private fun Int.toCoordinateString(): Char = (CONVERTING_BASE_NUMBER + this).toChar()

    private fun Point?.toLatestPointString() = when (this) {
        null -> EMPTY_STRING
        else -> LAST_STONE_POINT_MESSAGE.format((this.x).toCoordinateString(), this.y)
    }

    private fun String.matchesOrNull(regex: Regex): String? {
        if (this.matches(regex)) {
            return this
        }
        return null
    }

    fun requestPoint(latestStone: Stone?, currentColor: Color): Point {
        println(TURN_MESSAGE.format(currentColor.toColorName(), latestStone?.point.toLatestPointString()))

        while (true) {
            print(REQUEST_POINT_MESSAGE)
            readln().matchesOrNull(pointRegex)?.let { input ->
                return Point(
                    x = input[0].toCoordinate(),
                    y = input.substring(1).toInt()
                )
            }
            println(ERROR_INPUT_FORM)
        }
    }
}
