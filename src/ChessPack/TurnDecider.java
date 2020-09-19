package ChessPack;

public interface TurnDecider
{
    void Initialization();
    void DoTurn(Board CurrentBoard, PieceColor PlayerColor);
}
