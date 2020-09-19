package ChessPack;

public class Field
{
    private boolean Empty;
    private Piece LocalPiece;

    Field()
    {
        Empty = true;
    }

    public void PutPiece(Piece NewPiece)
    {
        Empty = false;
        LocalPiece = NewPiece;
    }

    public boolean Check()
    {
        return(Empty);
    }

    public Piece CheckPiece()
    {
        return(LocalPiece);
    }

    public Piece TakePiece()
    {
        Piece TMPPiece = LocalPiece;
        LocalPiece = null;
        return(TMPPiece);
    }

    public String ShowField()
    {
        if(Empty)
            return("   ");
        else
            return(LocalPiece.GetDesignation());
    }
}
