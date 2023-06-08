package com.sprintray.internet.key;

import androidx.annotation.StringDef;

public class HttpSpKeys {

    @StringDef({
            SPKey.SP_NAME,
            SPKey.SP_ASSESS_TOKEN,

            SPKey.SP_REFRESH_TOKEN,
            SPKey.SP_NAME_ACCOUNT,
            SPKey.SP_ACCOUNT_EMAIL,
            SPKey.SP_DEVICE_NAME,
            SPKey.SP_DEVICE_MODEL,
            SPKey.SP_DEVICE_SERIAL,
            SPKey.SP_PRINTER_STATUS,
            SPKey.SP_PRINTER_RESIN_TANK_SRC_TEMP,
            SPKey.SP_PRINTER_HEAT_COMPONENT,
            SPKey.SP_PRINTER_HEAT_PLATFORM,
            SPKey.SP_ACCOUNT_PASSWORD,
            SPKey.SP_CURRENT_MATERIALS,
    })
    public @interface SPKey{

        String SP_NAME = "SPRINT_NAME";


        String SP_NAME_ACCOUNT = "SP_NAME_ACCOUNT";
        String SP_ASSESS_TOKEN = "SP_ASSESS_TOKEN";
        String SP_REFRESH_TOKEN = "SP_REFRESH_TOKEN";
        String SP_ACCOUNT_EMAIL = "SP_ACCOUNT_EMAIL";
        String SP_DEVICE_NAME ="SP_DEVICE_NAME";
        String SP_DEVICE_MODEL="SP_DEVICE_MODEL";
        String SP_DEVICE_SERIAL="SP_DEVICE_SERIAL";
        String SP_PRINTER_STATUS="SP_PRINTER_STATUS";
        String SP_PRINTER_RESIN_TANK_SRC_TEMP="SP_PRINTER_RESIN_TANK_SRC_TEMP";
        String SP_PRINTER_HEAT_COMPONENT="SP_PRINTER_HEAT_COMPONENT";
        String SP_PRINTER_HEAT_PLATFORM="SP_PRINTER_HEAT_PLATFORM";
        String SP_ACCOUNT_PASSWORD="SP_ACCOUNT_PASSWORD";
        String SP_CURRENT_MATERIALS="SP_CURRENT_MATERIALS";
    }


}
