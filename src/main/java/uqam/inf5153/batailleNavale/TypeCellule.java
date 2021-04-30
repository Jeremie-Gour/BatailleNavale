package uqam.inf5153.batailleNavale;

public enum TypeCellule {
    NAVIREINTACT('N'),
    NAVIRETOUCHE('o'),
    NAVIRECOULE('#'),
    NAVIRERATE('x'),
    EAU('~'),
    BOMBE('!');

    private final char icone;
    private boolean tiree;

    TypeCellule(char icone) {
        this.icone = icone;
    }

    public char getIcone() {
        return icone;
    }

    public void setTiree(boolean tiree) {
        this.tiree = tiree;
    }

    public boolean isTiree() {
        return tiree;
    }
}
