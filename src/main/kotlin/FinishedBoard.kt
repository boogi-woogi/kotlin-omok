class FinishedBoard(
    placedStones: List<Stone>,
    override val winningColor: Color
) : BasedBoard(placedStones.toList()) {
    override val isFinished: Boolean = true

    override fun isPossiblePut(point: Point): Boolean = false

    override fun putStone(stone: Stone): Board = this
}
