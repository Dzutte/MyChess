package ChessPack;

public class Player
{
    private final PieceColor Color;
    private TurnDecider TurnController;

    Player(PieceColor NewColor, ControlType Control)
    {
        Color = NewColor;
        switch(Control)
        {
            case LocalHumanControl:
                TurnController = new LocalTurnDecider();
                break;
            case RemoteHumanControl:
                System.out.println("Remote");//WIP
                break;
            case AIControl:
                System.out.println("AI");//WIP
                break;
            default:
                System.out.println("Unselected control");
        }
        TurnController.Initialization();
    }

    public PieceColor GetColor()
    {
        return(Color);
    }

    public void DoTurn(Board CurrentBoard)
    {
        TurnController.DoTurn(CurrentBoard, Color);
    }
}
