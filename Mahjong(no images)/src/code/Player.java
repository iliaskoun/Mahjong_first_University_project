package code;
public class Player {
    private String onoma;
    private String duskolia;

    public Player(String onoma, String duskolia) {//constructor  arxikopoiei onoma kai epipedo diskolias
        this.onoma = onoma;
        this.duskolia = duskolia;
    }

    public String getOnoma() {//epistrefei onoma
        return onoma;
    }

    public String getDuskolia() {//epistrefei ep duskolias
        return duskolia;
    }
    
}