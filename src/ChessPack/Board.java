package ChessPack;

public class Board
{
    private Field[][] Fields;

    Board()
    {
        Fields = new Field[8][8];
        for(int I = 0; I < 8; I++)
            for (int J = 0; J < 8; J++)
                Fields[I][J] = new Field();
    }

    public boolean PlacePiece(int X, int Y, Piece NewPiece)
    {
        if((X < 8)&(X > -1)&(Y < 8)&(Y > -1))
        {
            Fields[X][Y].PutPiece(NewPiece);
            return(true);
        }
        else
            return(false);
    }

    public boolean CheckField(int X, int Y)
    {
        return(Fields[X][Y].Check());
    }

    public Piece CheckPiece(int X, int Y)
    {
        if((X < 8)&(X > -1)&(Y < 8)&(Y > -1))
            return(Fields[X][Y].CheckPiece());
        else
        {
            Piece NoPiece = new Piece();
            return(NoPiece);
        }
    }

    public boolean CheckOwnership(int X, int Y, PieceColor PlayerColor)
    {
        if((X < 8)&(X > -1)&(Y < 8)&(Y > -1))
        {
            if(Fields[X][Y].CheckPiece().GetColor() == PlayerColor)
                return(true);
            else
                return(false);
        }
        else
            return(false);
    }

    public boolean Move(int FromX, int FromY, int ToX, int ToY)
    {
        if((FromX >7)|(FromX < 0)|(FromY > 7)|(FromY < 0))
            return(false);
        else if((ToX >7)|(ToX < 0)|(ToY > 7)|(ToY < 0))
            return(false);
        else
        {
            Fields[ToX][ToY].PutPiece(Fields[FromX][FromY].TakePiece());
            Fields[ToX][ToY].CheckPiece().SetMoved();
            return(true);
        }
    }

    public boolean Move(Turn T)
    {
        return(Move(T.GetFromX(), T.GetFromY(), T.GetToX(), T.GetToY()));
    }

    public boolean Remove(int X, int Y, Piece NewPiece)
    {
        if((X < 8)&(X > -1)&(Y < 8)&(Y > -1))
        {
            Fields[X][Y].TakePiece();
            return(true);
        }
        else
            return(false);
    }

    public void PlaceCommonPieces()
    {
        for(int X = 0; X < 8; X++)
        {
            PlacePiece(X, 1, new Piece(PieceColor.White, PieceType.Pawn));
            PlacePiece(X, 6, new Piece(PieceColor.Black, PieceType.Pawn));
        }
        PlacePiece(0,0, new Piece(PieceColor.White, PieceType.Rook));
        PlacePiece(7,0, new Piece(PieceColor.White, PieceType.Rook));
        PlacePiece(0,7, new Piece(PieceColor.Black, PieceType.Rook));
        PlacePiece(7,7, new Piece(PieceColor.Black, PieceType.Rook));
        PlacePiece(1,0, new Piece(PieceColor.White, PieceType.Knight));
        PlacePiece(6,0, new Piece(PieceColor.White, PieceType.Knight));
        PlacePiece(1,7, new Piece(PieceColor.Black, PieceType.Knight));
        PlacePiece(6,7, new Piece(PieceColor.Black, PieceType.Knight));
        PlacePiece(2,0, new Piece(PieceColor.White, PieceType.Bishop));
        PlacePiece(5,0, new Piece(PieceColor.White, PieceType.Bishop));
        PlacePiece(2,7, new Piece(PieceColor.Black, PieceType.Bishop));
        PlacePiece(5,7, new Piece(PieceColor.Black, PieceType.Bishop));
        PlacePiece(3,0, new Piece(PieceColor.White, PieceType.Queen));
        PlacePiece(4,0, new Piece(PieceColor.White, PieceType.King));
        PlacePiece(3,7, new Piece(PieceColor.Black, PieceType.Queen));
        PlacePiece(4,7, new Piece(PieceColor.Black, PieceType.King));
    }

    private void DrawLine()
    {
        System.out.print("   ");
        for(int I = 0; I < 8; I++)
            System.out.print("+---");
        System.out.println("+");
    }

    public void Draw()
    {
        System.out.println("     A   B   C   D   E   F   G   H");
        DrawLine();
        for(int Y = 7; Y > -1; Y--)
        {
            System.out.print(" " + (Y + 1) + " ");
            for(int X = 0; X < 8; X++)
                System.out.print("|"+Fields[X][Y].ShowField());
            System.out.println("| " + (Y + 1));
            DrawLine();
        }
        System.out.println("     A   B   C   D   E   F   G   H");
    }
}
