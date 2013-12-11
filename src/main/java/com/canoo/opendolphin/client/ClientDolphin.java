package com.canoo.opendolphin.client;

public class ClientDolphin {

    public void presentationModel(Dolphin dolphin, String id, String type, ClientAttribute...clientAttributes){
        DolphinMain2.newPresentationModel(dolphin.getDolphin(), id,
                DolphinMain2.attributesJS(clientAttributes[0].getAttribute(),
                clientAttributes[1].getAttribute()));
    }


}
