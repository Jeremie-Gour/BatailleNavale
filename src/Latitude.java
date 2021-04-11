public enum Latitude {
    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    H('H'),
    I('I'),
    J('J');

    private char latitude;

    private Latitude(char _latitude) {
        this.latitude = _latitude;
    }

    public char getLatitude() {
        return latitude;
    }

}
