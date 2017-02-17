package example.belly;

/**
 * @author : ABDUL SAIF
 * @date   : 10/11/2016
 */

import net.serenitybdd.core.pages.PageObject;

public class Belly extends PageObject{

    private int cukes;

        protected void eat(int cukes) {
            this.cukes = cukes;
        }

        protected String getSound(int waitingTime)
        {
            if (cukes >= 40 && waitingTime >= 1)
            {
                return "silent";
            } else
                {
                    return "growl";
                }
        }

}
