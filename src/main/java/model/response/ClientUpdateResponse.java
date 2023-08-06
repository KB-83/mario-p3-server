package model.response;


public class ClientUpdateResponse extends Response{
    private int coin;
    private int diamond;
    public ClientUpdateResponse() {
    }

    public ClientUpdateResponse(int coin, int diamond) {
        this.coin = coin;
        this.diamond = diamond;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }
}
