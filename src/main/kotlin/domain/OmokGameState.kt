package domain

import domain.board.Board
import domain.stone.Color
import domain.stone.Point
import domain.stone.Stone

sealed class OmokGameState {

    object Running : OmokGameState()

    data class End(val winningColor: Color) : OmokGameState()

    companion object {

        fun valueOf(currentBoard: Board, color: Color): OmokGameState {
            if (checkWin(currentBoard.placedStones, color)) {
                return End(color)
            }
            return Running
        }

        // TODO
        private fun checkWin(placedStones: List<Stone>, color: Color): Boolean {
            val dx = intArrayOf(1, 1, 0, -1)
            val dy = intArrayOf(0, 1, 1, 1)

            for (i in 0 until N) {
                for (j in 0 until N) {
                    if (placedStones.contains(Stone(Point(i, j), color)).not()) continue

                    for (k in 0 until 4) {
                        var cnt = 1
                        var nx = i + dx[k]
                        var ny = j + dy[k]

                        while (nx in 0 until N && ny in 0 until N && placedStones.contains(
                                Stone(
                                        Point(nx, ny),
                                        color
                                    )
                            )
                        ) {
                            cnt++
                            nx += dx[k]
                            ny += dy[k]

                            if (cnt >= 5) return true
                        }
                    }
                }
            }
            return false
        }

        private const val N = 15
    }
}
