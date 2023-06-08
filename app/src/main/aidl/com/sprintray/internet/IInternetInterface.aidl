// IRoCInterface.aidl
package com.sprintray.internet;




interface IInternetInterface {
   /* Request to update resin data from the client */


       void registerReceiveListener(IRocListener listener);

       void unregisterReceiveListener(IRocListener listener);
}