package it.unipv.ingsw.alchemicalbank.wizards;

import it.unipv.ingsw.alchemicalbank.Decision;
import it.unipv.ingsw.alchemicalbank.Wizard;
//Sara Macri 446087
public class MacriSara extends Wizard {
    @Override
    public Decision askKeepOrLiquidate(int fundValue, int timespan) {
        if (timespan>9) {
            return Decision.LIQUIDATE_FUND;
        }
        else{
            return Decision.KEEP_FUND;
        }
    }
}
