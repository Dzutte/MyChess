package ChessPack;

public class MainClass
{
    private static void GameProcess(Board GameBoard, Player Player1,Player Player2)
    {
        Player CurrentPlayer = Player1;
        do
        {
            GameBoard.Draw();
            CurrentPlayer.DoTurn(GameBoard);
            if(CurrentPlayer == Player1)
                CurrentPlayer = Player2;
            else
                CurrentPlayer = Player1;
        }
        while(BoardAnalytics.CheckGameStatus(GameBoard, CurrentPlayer.GetColor()) == GameStatus.PlayOn);
    }

    private static void GameInitialization()
    {
        boolean GameIsStarted = true;
        Board GameBoard = new Board();
        GameBoard.PlaceCommonPieces();
        Player Player1 = new Player(PieceColor.White, ControlType.LocalHumanControl);;
        Player Player2 = new Player(PieceColor.Black, ControlType.LocalHumanControl);
        GameProcess(GameBoard, Player1, Player2);
    }

    public static void main(String[] args)
    {
        GameInitialization();
    }
}
