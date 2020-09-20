package ChessPack;

import java.util.Scanner;
import java.util.stream.Stream;

public class LocalTurnDecider implements TurnDecider
{
    @Override
    public void Initialization()
    {
        //Do nothing
    }

    private boolean isNumber(char ch)
    {
        return Stream.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9').anyMatch(c -> c == ch);
    }

    private int NumberToInt(char Ch)
    {
        if(isNumber(Ch))
        {
            switch(Ch)
            {
                case '1':
                    return(0);
                case '2':
                    return(1);
                case '3':
                    return(2);
                case '4':
                    return(3);
                case '5':
                    return(4);
                case '6':
                    return(5);
                case '7':
                    return(6);
                case '8':
                    return(7);
            }
        }
        return(-1);
    }

    private int CharToInt(char Ch)
    {
        switch(Ch)
        {
            case 'A':
            case 'a':
                return(0);
            case 'B':
            case 'b':
                return(1);
            case 'C':
            case 'c':
                return(2);
            case 'D':
            case 'd':
                return(3);
            case 'E':
            case 'e':
                return(4);
            case 'F':
            case 'f':
                return(5);
            case 'G':
            case 'g':
                return(6);
            case 'H':
            case 'h':
                return(7);
        }
        return(-1);
    }

    private Turn StringToTurn(String S)
    {
        Turn CurrentTurn = new Turn();
        if(S.length() == 5)
        {
            CurrentTurn.SetFromX(CharToInt(S.charAt(0)));
            CurrentTurn.SetFromY(NumberToInt(S.charAt(1)));
            CurrentTurn.SetToX(CharToInt(S.charAt(3)));
            CurrentTurn.SetToY(NumberToInt(S.charAt(4)));
        }
        return(CurrentTurn);
    }

    private int ColorDirection(PieceColor Color)
    {
        if(Color == PieceColor.White)
            return(1);
        else
            return(-1);
    }

    private boolean PawnCanDoThis(Board CurrentBoard, Turn CurrentTurn)
    {
        Piece CurrentPiece = CurrentBoard.CheckPiece(CurrentTurn.GetFrom());
        if(CurrentTurn.IsVertical())// Move
        {
            if((CurrentTurn.GetVerticalMove()) * ColorDirection(CurrentPiece.GetColor()) == 1)
                return(CurrentBoard.CheckFieldEmpty(CurrentTurn.GetTo()));
            else if((CurrentTurn.GetVerticalMove()) * ColorDirection(CurrentPiece.GetColor()) == 2)
            {
                if(CurrentBoard.CheckClearVertical(CurrentTurn))
                    return (CurrentPiece.CheckStillness() & CurrentBoard.CheckFieldEmpty(CurrentTurn.GetTo()));
                else
                    return(false);
            }
            else
                return(false);
        }
        else// Attack
        {
            if(Math.abs(CurrentTurn.GetHorizontalMove()) == 1)
            {
                if((CurrentTurn.GetVerticalMove()) * ColorDirection(CurrentPiece.GetColor()) == 1)
                    return(!CurrentBoard.CheckFieldEmpty(CurrentTurn.GetTo()));
                else
                    return(false);
            }
            else
                return(false);
        }
    }

    private boolean KnightCanDoThis(Turn CurrentTurn)
    {
        if((Math.abs(CurrentTurn.GetVerticalMove()) == 2) & (Math.abs(CurrentTurn.GetHorizontalMove()) == 1))
            return(true);
        else if((Math.abs(CurrentTurn.GetVerticalMove()) == 1) & (Math.abs(CurrentTurn.GetHorizontalMove()) == 2))
            return(true);
        else
            return(false);
    }

    private boolean BishopCanDoThis(Board CurrentBoard, Turn CurrentTurn)
    {
        if(CurrentTurn.IsDiagonal())
            return(CurrentBoard.CheckClearDiagonal(CurrentTurn));
        else
            return(false);
    }

    private boolean RookCanDoThis(Board CurrentBoard, Turn CurrentTurn)
    {
        if(CurrentTurn.IsVertical())
            return(CurrentBoard.CheckClearVertical(CurrentTurn));
        else if(CurrentTurn.IsHorizontal())
            return(CurrentBoard.CheckClearHorizontal(CurrentTurn));
        else
            return(false);
    }

    private boolean KingCanDoThis(Board CurrentBoard, Turn CurrentTurn)
    {
        if(Math.abs(CurrentTurn.GetVerticalMove()) < 2)
        {
            if(Math.abs(CurrentTurn.GetHorizontalMove()) < 2)
                return(true);
            else //Castling check
            {
                if(CurrentTurn.IsCastling())
                    return(CurrentBoard.CastlingAvailable(CurrentTurn));
                else
                    return(false);
            }
        }
        else
            return(false);
    }

    private boolean PieceCanDoThis(Board CurrentBoard, Turn CurrentTurn)
    {
        switch (CurrentBoard.CheckPiece(CurrentTurn.GetFromX(), CurrentTurn.GetFromY()).GetType())
        {
            case Pawn:
                return(PawnCanDoThis(CurrentBoard, CurrentTurn));
            case Knight:
                return(KnightCanDoThis(CurrentTurn));
            case Bishop:
                return(BishopCanDoThis(CurrentBoard, CurrentTurn));
            case Rook:
                return(RookCanDoThis(CurrentBoard, CurrentTurn));
            case Queen:
                return(RookCanDoThis(CurrentBoard, CurrentTurn)|BishopCanDoThis(CurrentBoard, CurrentTurn));
            case King:
                return(KingCanDoThis(CurrentBoard, CurrentTurn));
            default:
                return(false);
        }
    }

    private boolean AcceptableTurn(Board CurrentBoard, PieceColor PlayerColor, Turn CurrentTurn)
    {
        if(CurrentTurn.GetFrom().IsOnBoard() & CurrentTurn.GetTo().IsOnBoard() & (!CurrentTurn.IsFake()))
        {
            if (CurrentBoard.CheckOwnership(CurrentTurn.GetFrom(), PlayerColor))
            {
                if (!CurrentBoard.CheckOwnership(CurrentTurn.GetTo(), PlayerColor))
                    return (PieceCanDoThis(CurrentBoard, CurrentTurn));
                else
                    return (false);
            }
            else
                return (false);
        }
        else
            return (false);
    }

    private String ColorToString(PieceColor Color)
    {
        switch (Color)
        {
            case White:
                return("White");
            case Black:
                return("Black");
            default:
                return("Error");
        }
    }

    @Override
    public void DoTurn(Board CurrentBoard, PieceColor PlayerColor)
    {
        Scanner sc = new Scanner(System.in);
        Turn CurrentTurn;
        do
        {
            System.out.println(ColorToString(PlayerColor));
            CurrentTurn = StringToTurn(sc.nextLine());
        }
        while(!AcceptableTurn(CurrentBoard, PlayerColor, CurrentTurn));
        CurrentBoard.Move(CurrentTurn);
    }
}
