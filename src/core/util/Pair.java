package core.util;

/**
 * Created by jonathanbrodie on 12/27/15.
 */
public class Pair {
    private int first;
    private int second;

    public Pair(int firstparam, int secondparam) {
        this.first=firstparam;
        this.second=secondparam;
    }
    public int getFirst() {
        return this.first;
    }
    public int getSecond() {
        return this.second;
    }
}
