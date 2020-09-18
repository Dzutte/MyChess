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

    private void DrawLine()
    {
        for(int I = 0; I < 8; I++)
            System.out.print("+---");
        System.out.println("+");
    }

    public void Draw()
    {
        DrawLine();
        for(int Y = 0; Y < 8; Y++)
        {
            for(int X = 0; X < 8; X++)
                System.out.print("|"+Fields[X][Y].ShowField());
            System.out.println("|");
            DrawLine();
        }
    }
}
