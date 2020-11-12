package code;
public class Character extends Tile {

    public Character(int a) {
        arithmos = a;
        diathesimo = true;
        eleuthero = false;
    }

    
    @Override
    public int getArithmos() {//synarthsh pou epistrefei ton arithmo plakidiou
        return arithmos;
    }

    @Override
    public boolean getDiathesimo() {//synarthsh pou blepoume an einai diathesimo
        return diathesimo;
    }

    @Override
    public void setDiathesimo(Boolean diathesimo) {//synarthsh pou allazei th diathesimothta
        this.diathesimo = diathesimo;
    }

    @Override
    public boolean getEleuthero() {
        return eleuthero;
    }

    @Override
    public void setEleuthero(Boolean eleuthero) {
        this.eleuthero = eleuthero;
    }

}