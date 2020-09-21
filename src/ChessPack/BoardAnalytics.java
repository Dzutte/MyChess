package ChessPack;

public class BoardAnalytics
{
    private static Coordinates FindKing(Board CurrentBoard, PieceColor CurrentPlayer)
    {
        Coordinates KingCoordinates = new Coordinates(-1,-1);
        for(int X = 0; X < 8; X++)
            for(int Y = 0; Y < 8; Y++)
                if(!CurrentBoard.CheckFieldEmpty(X, Y))
                    if(CurrentBoard.CheckPiece(X, Y).GetType() == PieceType.King)
                        if(CurrentBoard.CheckPiece(X, Y).GetColor() == CurrentPlayer)
                        {
                            KingCoordinates.SetX(X);
                            KingCoordinates.SetY(Y);
                            return(KingCoordinates);
                        }
        return(KingCoordinates);
    }

    private static boolean CheckEnemyAt(Board CurrentBoard, int X, int Y, PieceType RequiredType, PieceColor AllyColor)
    {
        if(Coordinates.IsOnBoard(X, Y))
            if(!CurrentBoard.CheckFieldEmpty(X, Y))
                if(CurrentBoard.CheckPiece(X, Y).GetType() == RequiredType)
                    if(CurrentBoard.CheckPiece(X + 1, Y).GetColor() != AllyColor)
                        return(true);
        return(false);
    }

    private static boolean CheckEnemyAt(Board CurrentBoard, int X, int Y, PieceType RequiredType1, PieceType RequiredType2, PieceColor AllyColor)
    {
        if(!CurrentBoard.CheckFieldEmpty(X, Y))
            if((CurrentBoard.CheckPiece(X, Y).GetType() == RequiredType1) | (CurrentBoard.CheckPiece(X, Y).GetType() == RequiredType2))
                if(CurrentBoard.CheckPiece(X + 1, Y).GetColor() != AllyColor)
                    return(true);
        return(false);
    }

    private static boolean CheckPawnAttack(Board CurrentBoard, int PieceX, int PieceY)
    {
        if(CurrentBoard.CheckPiece(PieceX, PieceY).GetColor() == PieceColor.White)
        {
            if(CheckEnemyAt(CurrentBoard, PieceX + 1, PieceY + 1, PieceType.Pawn, PieceColor.White))
                return(true);
            if(CheckEnemyAt(CurrentBoard, PieceX - 1, PieceY + 1, PieceType.Pawn, PieceColor.White))
                return(true);
        }
        else
        {
            if(CheckEnemyAt(CurrentBoard, PieceX + 1, PieceY - 1, PieceType.Pawn, PieceColor.Black))
                return(true);
            if(CheckEnemyAt(CurrentBoard, PieceX - 1, PieceY - 1, PieceType.Pawn, PieceColor.Black))
                return(true);
        }
        return(false);
    }

    private static boolean CheckKnightAttack(Board CurrentBoard, int PieceX, int PieceY)
    {
        if(CheckEnemyAt(CurrentBoard, PieceX - 1, PieceY - 2, PieceType.Knight, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX - 2, PieceY - 1, PieceType.Knight, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX + 1, PieceY - 2, PieceType.Knight, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX + 2, PieceY - 1, PieceType.Knight, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX - 1, PieceY + 2, PieceType.Knight, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX - 2, PieceY + 1, PieceType.Knight, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX + 1, PieceY + 2, PieceType.Knight, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX + 2, PieceY + 1, PieceType.Knight, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        return(false);
    }

    private static boolean CheckBishopAttack(Board CurrentBoard, int PieceX, int PieceY)
    {
        int Move;
        for(Move = 1; (PieceX + Move < 8) & (PieceY + Move < 8); Move++)
            if(!CurrentBoard.CheckFieldEmpty(PieceX + Move, PieceY + Move))
                break;
        if(Coordinates.IsOnBoard(PieceX + Move, PieceY + Move))
            if(CheckEnemyAt(CurrentBoard, PieceX + Move, PieceY + Move, PieceType.Bishop, PieceType.Queen, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; (PieceX - Move > -1) & (PieceY + Move < 8); Move++)
            if(!CurrentBoard.CheckFieldEmpty(PieceX - Move, PieceY + Move))
                break;
        if(Coordinates.IsOnBoard(PieceX - Move, PieceY + Move))
            if(CheckEnemyAt(CurrentBoard, PieceX - Move, PieceY + Move, PieceType.Bishop, PieceType.Queen, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; (PieceX + Move < 8) & (PieceY - Move > -1); Move++)
            if(!CurrentBoard.CheckFieldEmpty(PieceX + Move, PieceY - Move))
                break;
        if(Coordinates.IsOnBoard(PieceX + Move, PieceY - Move))
            if(CheckEnemyAt(CurrentBoard, PieceX + Move, PieceY - Move, PieceType.Bishop, PieceType.Queen, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; (PieceX - Move > -1) & (PieceY - Move > -1); Move++)
            if(!CurrentBoard.CheckFieldEmpty(PieceX - Move, PieceY - Move))
                break;
        if(Coordinates.IsOnBoard(PieceX - Move, PieceY - Move))
            if(CheckEnemyAt(CurrentBoard, PieceX - Move, PieceY - Move, PieceType.Bishop, PieceType.Queen, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        return(false);
    }

    private static boolean CheckRookAttack(Board CurrentBoard, int PieceX, int PieceY)
    {
        int Move;
        for(Move = 1; PieceX + Move < 8; Move++)
            if(!CurrentBoard.CheckFieldEmpty(PieceX + Move, PieceY))
                break;
        if(Coordinates.IsOnBoard(PieceX + Move, PieceY))
            if(CheckEnemyAt(CurrentBoard, PieceX + Move, PieceY, PieceType.Rook, PieceType.Queen, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; PieceX - Move > -1; Move++)
            if(!CurrentBoard.CheckFieldEmpty(PieceX - Move, PieceY))
                break;
        if(Coordinates.IsOnBoard(PieceX - Move, PieceY))
            if(CheckEnemyAt(CurrentBoard, PieceX - Move, PieceY, PieceType.Rook, PieceType.Queen, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; PieceY + Move < 8; Move++)
            if(!CurrentBoard.CheckFieldEmpty(PieceX, PieceY + Move))
                break;
        if(Coordinates.IsOnBoard(PieceX, PieceY + Move))
            if(CheckEnemyAt(CurrentBoard, PieceX, PieceY + Move, PieceType.Rook, PieceType.Queen, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; PieceY - Move > -1; Move++)
            if(!CurrentBoard.CheckFieldEmpty(PieceX, PieceY - Move))
                break;
        if(Coordinates.IsOnBoard(PieceX, PieceY - Move))
            if(CheckEnemyAt(CurrentBoard, PieceX, PieceY - Move, PieceType.Rook, PieceType.Queen, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        return(false);
    }

    private static boolean CheckKingAttack(Board CurrentBoard, int PieceX, int PieceY)
    {
        if(CheckEnemyAt(CurrentBoard, PieceX - 1, PieceY, PieceType.King, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX - 1, PieceY - 1, PieceType.King, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX, PieceY - 1, PieceType.King, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX + 1, PieceY - 1, PieceType.King, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX + 1, PieceY, PieceType.King, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX + 1, PieceY + 1, PieceType.King, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX, PieceY + 1, PieceType.King, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(CurrentBoard, PieceX - 1, PieceY + 1, PieceType.King, CurrentBoard.CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        return(false);
    }

    private static boolean UnderAttack(Board CurrentBoard, int PieceX, int PieceY)
    {
        if(CheckPawnAttack(CurrentBoard, PieceX, PieceY))
            return(true);
        if(CheckKnightAttack(CurrentBoard, PieceX, PieceY))
            return(true);
        if(CheckBishopAttack(CurrentBoard, PieceX, PieceY))
            return(true);
        if(CheckRookAttack(CurrentBoard, PieceX, PieceY))
            return(true);
        if(CheckKingAttack(CurrentBoard, PieceX, PieceY))
            return(true);
        return(false);
    }

    private static boolean UnderAttack(Board CurrentBoard, Coordinates PieceCoordinates)
    {
        return(UnderAttack(CurrentBoard, PieceCoordinates.GetX(), PieceCoordinates.GetY()));
    }

    private static boolean CheckTurnOpensKing(Board CurrentBoard, Turn CurrentTurn)
    {
        Board FutureBoard = new Board(CurrentBoard);
        FutureBoard.Move(CurrentTurn);
        return(UnderAttack(FutureBoard, FindKing(FutureBoard, CurrentBoard.CheckPiece(CurrentTurn.GetFrom()).GetColor())));
    }

    private static int ColorDirection(PieceColor Color)
    {
        if(Color == PieceColor.White)
            return(1);
        else
            return(-1);
    }

    public static boolean PawnCanDoThis(Board CurrentBoard, Turn CurrentTurn)
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

    public static boolean KnightCanDoThis(Turn CurrentTurn)
    {
        if((Math.abs(CurrentTurn.GetVerticalMove()) == 2) & (Math.abs(CurrentTurn.GetHorizontalMove()) == 1))
            return(true);
        else if((Math.abs(CurrentTurn.GetVerticalMove()) == 1) & (Math.abs(CurrentTurn.GetHorizontalMove()) == 2))
            return(true);
        else
            return(false);
    }

    public static boolean BishopCanDoThis(Board CurrentBoard, Turn CurrentTurn)
    {
        if(CurrentTurn.IsDiagonal())
            return(CurrentBoard.CheckClearDiagonal(CurrentTurn));
        else
            return(false);
    }

    public static boolean RookCanDoThis(Board CurrentBoard, Turn CurrentTurn)
    {
        if(CurrentTurn.IsVertical())
            return(CurrentBoard.CheckClearVertical(CurrentTurn));
        else if(CurrentTurn.IsHorizontal())
            return(CurrentBoard.CheckClearHorizontal(CurrentTurn));
        else
            return(false);
    }

    public static boolean KingCanDoThis(Board CurrentBoard, Turn CurrentTurn)
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

    public static boolean PieceCanDoThis(Board CurrentBoard, Turn CurrentTurn)
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
                return(RookCanDoThis(CurrentBoard, CurrentTurn) | BoardAnalytics.BishopCanDoThis(CurrentBoard, CurrentTurn));
            case King:
                return(KingCanDoThis(CurrentBoard, CurrentTurn));
            default:
                return(false);
        }
    }

    private static boolean CanMovePawn(Board CurrentBoard, int X, int Y)
    {
        if(CurrentBoard.CheckPiece(X, Y).GetColor() == PieceColor.White)
        {
            if(Coordinates.IsOnBoard(X + 1, Y + 1))
                if(PawnCanDoThis(CurrentBoard, new Turn(X, Y, X + 1, Y + 1)))
                    if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X + 1, Y + 1)))
                        return(true);
            if(Coordinates.IsOnBoard(X - 1, Y + 1))
                if(PawnCanDoThis(CurrentBoard, new Turn(X, Y, X - 1, Y + 1)))
                    if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X - 1, Y + 1)))
                        return(true);
            if(CurrentBoard.CheckFieldEmpty(X, Y + 1))
            {
                if (!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X, Y + 1)))
                    return (true);
                if(CurrentBoard.CheckFieldEmpty(X, Y + 2))
                    if (!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X, Y + 2)))
                        return (true);
            }
            return(false);
        }
        else
        {
            if(Coordinates.IsOnBoard(X + 1, Y - 1))
                if(PawnCanDoThis(CurrentBoard, new Turn(X, Y, X + 1, Y - 1)))
                    if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X + 1, Y - 1)))
                        return(true);
            if(Coordinates.IsOnBoard(X - 1, Y - 1))
                if(PawnCanDoThis(CurrentBoard, new Turn(X, Y, X - 1, Y - 1)))
                    if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X - 1, Y - 1)))
                        return(true);
            if(CurrentBoard.CheckFieldEmpty(X, Y - 1))
            {
                if (!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X, Y - 1)))
                    return (true);
                if(CurrentBoard.CheckFieldEmpty(X, Y - 2))
                    if (!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X, Y - 2)))
                        return (true);
            }
            return(false);
        }
    }

    private static boolean CheckFieldAccess(Board CurrentBoard, int FromX, int FromY, int ToX, int ToY)
    {
        if(Coordinates.IsOnBoard(ToX, ToY))
            if(CurrentBoard.CheckPiece(FromX, FromY).IsEnemy(CurrentBoard.CheckPiece(ToX, ToY)))
                if(!CheckTurnOpensKing(CurrentBoard, new Turn(FromX, FromY, ToX, ToY)))
                    return(true);
        return(false);
    }

    private static boolean CanMoveKnight(Board CurrentBoard, int X, int Y)
    {
        if(CheckFieldAccess(CurrentBoard, X, Y, X + 1, Y + 2))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X + 2, Y + 1))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X - 1, Y + 2))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X + 2, Y - 1))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X + 1, Y - 2))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X - 2, Y + 1))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X - 1, Y - 2))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X - 2, Y - 1))
            return(true);
        return(false);
    }

    private static boolean CanMoveBishop(Board CurrentBoard, int X, int Y)
    {
        for(int Move = 1; (X + Move < 8) & (Y + Move < 8); Move++)
            if(RookCanDoThis(CurrentBoard, new Turn(X, Y, X + Move, Y + Move)))
                if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X + Move, Y + Move)))
                    return(true);
        for(int Move = 1; (X - Move > -1) & (Y + Move < 8); Move++)
            if(RookCanDoThis(CurrentBoard, new Turn(X, Y, X - Move, Y + Move)))
                if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X - Move, Y + Move)))
                    return(true);
        for(int Move = 1; (X + Move < 8) & (Y - Move > -1); Move++)
            if(RookCanDoThis(CurrentBoard, new Turn(X, Y, X + Move, Y - Move)))
                if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X + Move, Y - Move)))
                    return(true);
        for(int Move = 1; (X - Move > -1) & (Y - Move > -1); Move++)
            if(RookCanDoThis(CurrentBoard, new Turn(X, Y, X - Move, Y - Move)))
                if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X - Move, Y - Move)))
                    return(true);
        return(false);
    }

    private static boolean CanMoveRook(Board CurrentBoard, int X, int Y)
    {
        for(int Move = 1; X + Move < 8; Move++)
            if(RookCanDoThis(CurrentBoard, new Turn(X, Y, X + Move, Y)))
                if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X + Move, Y)))
                    return(true);
        for(int Move = 1; X - Move > -1; Move++)
            if(RookCanDoThis(CurrentBoard, new Turn(X, Y, X - Move, Y)))
                if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X - Move, Y)))
                    return(true);
        for(int Move = 1; Y + Move < 8; Move++)
            if(RookCanDoThis(CurrentBoard, new Turn(X, Y, X, Y + Move)))
                if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X, Y + Move)))
                    return(true);
        for(int Move = 1; Y - Move > -1; Move++)
            if(RookCanDoThis(CurrentBoard, new Turn(X, Y, X, Y - Move)))
                if(!CheckTurnOpensKing(CurrentBoard, new Turn(X, Y, X, Y - Move)))
                    return(true);
        return(false);
    }

    private static boolean CanMoveQueen(Board CurrentBoard, int X, int Y)
    {
        return(CanMoveBishop(CurrentBoard, X, Y) | CanMoveRook(CurrentBoard, X, Y));
    }

    private static boolean CanMoveKing(Board CurrentBoard, int X, int Y)
    {
        if(CheckFieldAccess(CurrentBoard, X, Y, X + 1, Y))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X + 1, Y + 1))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X, Y + 1))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X - 1, Y + 1))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X - 1, Y))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X - 1, Y - 1))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X, Y - 1))
            return(true);
        if(CheckFieldAccess(CurrentBoard, X, Y, X + 1, Y - 1))
            return(true);
        return(false);
    }

    private static boolean CanMove(Board CurrentBoard, int PieceX, int PieceY)
    {
        switch(CurrentBoard.CheckPiece(PieceX, PieceY).GetType())
        {
            case Pawn:
                return(CanMovePawn(CurrentBoard, PieceX, PieceY));
            case Knight:
                return(CanMoveKnight(CurrentBoard, PieceX, PieceY));
            case Bishop:
                return(CanMoveBishop(CurrentBoard, PieceX, PieceY));
            case Rook:
                return(CanMoveRook(CurrentBoard, PieceX, PieceY));
            case Queen:
                return(CanMoveQueen(CurrentBoard, PieceX, PieceY));
            case King:
                return(CanMoveKing(CurrentBoard, PieceX, PieceY));
        }
        return(false);
    }

    private static boolean CanDoTurn(Board CurrentBoard, PieceColor CurrentPlayer)
    {
        for(int X = 0; X < 8; X++)
            for(int Y = 0; Y < 8; Y++)
                if(!CurrentBoard.CheckFieldEmpty(X, Y))
                    if(CurrentBoard.CheckPiece(X, Y).GetColor() == CurrentPlayer)
                        if(CanMove(CurrentBoard, X, Y))
                            return(true);
        return(false);
    }

    public static GameStatus CheckGameStatus(Board CurrentBoard, PieceColor CurrentPlayer)
    {
        if(CanDoTurn(CurrentBoard, CurrentPlayer))
            return(GameStatus.PlayOn);
        else
        {
            if(UnderAttack(CurrentBoard, FindKing(CurrentBoard, CurrentPlayer)))
                return(GameStatus.Checkmate);
            else
                return(GameStatus.Stalemate);
        }

    }
}
