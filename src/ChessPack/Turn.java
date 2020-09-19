package ChessPack;

public class Turn
{
    private int FromX;
    private int FromY;
    private int ToX;
    private int ToY;

    Turn()
    {
        FromX = -1;
        FromY = -1;
        ToX = -1;
        ToY = -1;
    }

    public void SetFromX(int X)
    {
        FromX = X;
    }

    public int GetFromX()
    {
        return(FromX);
    }

    public void SetFromY(int Y)
    {
        FromY = Y;
    }

    public int GetFromY()
    {
        return(FromY);
    }

    public void SetToX(int X)
    {
        ToX = X;
    }

    public int GetToX()
    {
        return(ToX);
    }

    public void SetToY(int Y)
    {
        ToY = Y;
    }

    public int GetToY()
    {
        return(ToY);
    }
}
