package global;

public class GlobalStats {
    int str;
    int dex;
    int wis;
    int it;

    public GlobalStats(int s, int d, int w, int i) {
        str = s;
        dex = d;
        wis = w;
        it = i;
    }

    public int getSTR() {
        return str;
    }

    public int getDEX() {
        return dex;
    }

    public int getWIS() {
        return wis;
    }

    public int getINT() {
        return it;
    }

    public void setSTR(int i) {
        str = i;
    }

    public void setDEX(int i) {
        dex = i;
    }

    public void setWIS(int i) {
        wis = i;
    }

    public void setINT(int i) {
        it = i;
    }

    public void boostSTR(int i) {
        str += i;
    }

    public void boostDEX(int i) {
        dex += i;
    }

    public void boostWIS(int i) {
        wis += i;
    }

    public void boostINT(int i) {
        it += i;
    }

    public void boostAll(int i) {
        str += i;
        dex += i;
        wis += i;
        it += i;
    }

    public void boostAll(GlobalStats boost) {
        str += boost.str;
        dex += boost.dex;
        wis += boost.wis;
        it += boost.it;
    }
}
