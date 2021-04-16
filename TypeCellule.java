public enum TypeCellule {
    NAVIREINTACT('N'),
    NAVIRETOUCHE('o'),
    NAVIRECOULE('#'),
    NAVIRERATE('x'),
    EAU('~'),
    BOMBE('!'),
    ;

    private final char icone;

    TypeCellule(char icone) {
        this.icone = icone;
    }

    public char getIcone() {
        return icone;
    }
}
