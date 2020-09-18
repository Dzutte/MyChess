package ChessPack;

public class MainClass
{
    public static void main(String[] args)
    {
        Board MainBoard = new Board();
        Piece TestPiece = new Piece(PieceColor.Black, PieceType.King);
        MainBoard.PlacePiece(4,4,TestPiece);
        MainBoard.Draw();
    }
}
