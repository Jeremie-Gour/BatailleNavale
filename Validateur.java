public class Validateur {

    static boolean estTirValide(TypeCellule typeCellule) {
        return typeCellule != TypeCellule.NAVIRECOULE && typeCellule != TypeCellule.NAVIRERATE && typeCellule != TypeCellule.BOMBE && typeCellule != TypeCellule.NAVIRETOUCHE;
    }
}
