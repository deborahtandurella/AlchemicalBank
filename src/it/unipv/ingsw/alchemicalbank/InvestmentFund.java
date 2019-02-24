package it.unipv.ingsw.alchemicalbank;


import java.util.logging.Logger;

/**
 * An investment fund.
 * */
public class InvestmentFund {

    /// The logger for this class.
    private final static Logger LOGGER = Logger.getLogger(InvestmentFund.class.getName());

    /// Maximum number of months for the fund
    private static final int MAX_DURATION = 12;

    /// Percentage of revenue due to the wizard who closes the fund
    private static final int LIQUIDATOR_SHARE = 80;

    /// Initial value of the fund
    private int startingValue;

    /// Current value of the fund
    private int value;

    /// Elapsed months since the opening of the fund
    private int timespan;

    /// The two owners of the fund
    private Wizard[] owners;

    /// The owner who closed the fund, or null if it is still open
    private Wizard liquidator;

    /**
     * Create a new fund.
     * @param startingValue starting value of the fund
     * @param firstOwner one of the two owners of the fund
     * @param secondOwner the other owner
     */
    public InvestmentFund(int startingValue, Wizard firstOwner, Wizard secondOwner) {
        this.startingValue = startingValue;
        value = startingValue;
        timespan = 0;
        liquidator = null;
        owners = new Wizard[2];
        owners[0] = firstOwner;
        owners[1] = secondOwner;
    }

    /// Advance the fund by one month
    public void nextMonth() {
        if (!isClosed()) {
            int active = timespan % 2;
            value *= 2;
            timespan += 1;
            Decision decision = owners[active].askKeepOrLiquidate(value, timespan, owners[1 - active].getCoins());
            if (timespan >= MAX_DURATION || decision == Decision.LIQUIDATE_FUND)
                liquidator = owners[active];
        }
    }

    /// Compute the coins due to the holders
    public int[] computeRevenues() {
        int revenue = value - startingValue;
        int share = revenue * LIQUIDATOR_SHARE / 100;
        if (owners[0] == liquidator)
            return new int[] {share, revenue - share};
        else if (owners[1] == liquidator)
            return new int[] {revenue - share, share};
        else
            return new int[] {0, 0};
//        String fmt = "%16s closes the fund after %2d months: %-16s (%+6d) / %-16s (%+6d)";
//        String msg = String.format(fmt,  owners[active].getName(), timespan, owners[0].getName(), v0, owners[1].getName(), v1);
//        LOGGER.info(msg);
    }

    /// Tell if the account has been closed or not
    public boolean isClosed() {
        return (liquidator != null);
    }

    /// Return the owner who closed the fund (or null if the fund is still open)
    public Wizard getLiquidator() {
        return liquidator;
    }
}
