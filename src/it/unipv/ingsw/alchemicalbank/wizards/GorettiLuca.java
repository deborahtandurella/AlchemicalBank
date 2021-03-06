package it.unipv.ingsw.alchemicalbank.wizards;

import it.unipv.ingsw.alchemicalbank.Decision;
import it.unipv.ingsw.alchemicalbank.Wizard;

public class GorettiLuca extends Wizard {
    //Luca Goretti

    @Override
    public Decision askKeepOrLiquidate(int fundValue, int timespan) {
        if (timespan%2==0) {
            if (timespan>=10)
                return Decision.LIQUIDATE_FUND;
            else
                return Decision.KEEP_FUND;
        }
        else {
            if (timespan==11)
                return Decision.LIQUIDATE_FUND;
            else
                return Decision.KEEP_FUND;
        }
    }
}
