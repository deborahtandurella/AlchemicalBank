package it.unipv.ingsw.alchemicalbank.wizards;

import it.unipv.ingsw.alchemicalbank.Decision;
import it.unipv.ingsw.alchemicalbank.Wizard;

/**
 *  Filicicchia Ennio 440532
 */
public class SmartWizardFilicicchia extends Wizard {

    @Override
    public Decision askKeepOrLiquidate(int fundValue, int timespan) {
        if(timespan >= 10) {
            return  Decision.LIQUIDATE_FUND;
        }

        return Decision.KEEP_FUND;
    }

}