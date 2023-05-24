package uk.pallas.systems.typr.services;

import si.uom.NonSI;
import si.uom.SI;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import javax.measure.quantity.Frequency;
import java.util.HashMap;
import java.util.Map;

public class UnitsService {
    private static final SI SI_UNITS = SI.getInstance();

    private static final NonSI NON_SI_UNITS = NonSI.getInstance();

    protected final Map<String, Unit<?>> units;

    public UnitsService() {
        super();

        this.units = new HashMap();

        final Unit rateOfTurn =  NonSI.DEGREE_ANGLE.divide(Units.MINUTE).asType(Frequency.class);
        this.units.put("Rate of Turn", rateOfTurn);
    }

    public Unit<?> getUnit(final String unitName) {
        Unit<?> unit = null;
        if (null == unitName || unitName.isBlank()) {
            //unit = null;

        } else {
            unit = SI_UNITS.getUnit(unitName);
            if (null == unit) {
                unit = NON_SI_UNITS.getUnit(unitName);
                if (null == unit) {
                    unit = this.units.get(unitName);
                }
            }
        }

        return unit;
    }

    public boolean isValid(final String unitName) {
        return null != this.getUnit(unitName);
    }
}
