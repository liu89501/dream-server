package com.dream.server.service.component;

import com.dream.container.anno.Component;
import com.dream.container.anno.ToContainer;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;

@Component(proxy = false)
public class CCurves
{
    @ToContainer(uid = "upgrade_curve")
    public UnivariateFunction upgradeGearsCurve()
    {
        LinearInterpolator inter = new LinearInterpolator();
        return inter.interpolate(new double[]{0, 5, 10, 20, 100}, new double[]{1, 1, 0.8, 0.1, 0.001});
    }
}
