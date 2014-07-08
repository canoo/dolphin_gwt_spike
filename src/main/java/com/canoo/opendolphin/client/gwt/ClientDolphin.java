package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.*;
import com.google.gwt.core.client.JsArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientDolphin {

    private final ClientDolphinJS clientDolphinJS;

    public ClientDolphin(ClientDolphinJS clientDolphinJS) {
		this.clientDolphinJS = clientDolphinJS;
    }

    public void send(String commandName) {
		clientDolphinJS.send(commandName);
	}

    public void send(String commandName, final OnFinishedHandler handler) {
		clientDolphinJS.send(commandName, new OnFinishedHandlerAdapter(handler));
    }

    public void sendEmpty(final OnFinishedHandler handler) {
		clientDolphinJS.sendEmpty(new OnFinishedHandlerAdapter(handler));
    }

	/** new Attribute with tag 'VALUE' */
    public ClientAttribute attribute(String propertyName, String qualifier, String value) {
		ClientAttributeJS clientAttributeJS = clientDolphinJS.attribute(propertyName, qualifier, value);
		return new ClientAttribute(clientAttributeJS);
	}

    public ClientAttribute attribute(String propertyName, String qualifier, String value, String tag) {
		ClientAttributeJS clientAttributeJS = clientDolphinJS.attribute(propertyName, qualifier, value, tag);
		return new ClientAttribute(clientAttributeJS);
	}

    public ClientPresentationModel presentationModel(String id, String... attributeNames) {
		return this.presentationModelWithType(id, null, attributeNames);
	}
    public ClientPresentationModel presentationModelWithType(String id, String type, String... clientAttributePropertyNames) {

		List<ClientAttribute> clientAttributes = new ArrayList<ClientAttribute>();
		for (String propertyName : clientAttributePropertyNames) {
			ClientAttribute clientAttribute = this.attribute(propertyName, null, null);
			clientAttributes.add(clientAttribute);
		}
		return this.presentationModelWithType(id, type, clientAttributes.toArray(new ClientAttribute[clientAttributes.size()]));
    }
	public ClientPresentationModel presentationModelWithType(String id, String type, ClientAttribute... clientAttributes) {
		JsArray jsAttributes = ClientAttributeJS.createArray().cast();
		for (ClientAttribute clientAttribute : clientAttributes) {
			jsAttributes.push(clientAttribute.getClientAttributeJS());
		}

		ClientPresentationModelJS presentationModelJS = clientDolphinJS.presentationModel(id, type, jsAttributes);
		return new ClientPresentationModel(presentationModelJS);
	}

    public ClientModelStore getClientModelStore() {
        return new ClientModelStore(clientDolphinJS.getClientModelStore());
    }


	public List<String> listPresentationModelIds() {
		String[] pmIds = clientDolphinJS.listPresentationModelIds();
		return Arrays.asList(pmIds);
	}

	public List<ClientPresentationModel> listPresentationModels() {
		ClientPresentationModelJS[] pms = clientDolphinJS.listPresentationModels();
		return pmJSArrayAsPMList(pms);
	}
	public List<ClientPresentationModel> findAllPresentationModelsByType(String pmType) {
		ClientPresentationModelJS[] pms = clientDolphinJS.findAllPresentationModelsByType(pmType);
		return pmJSArrayAsPMList(pms);
	}

	public ClientPresentationModel getAt(String pmId) {
		ClientPresentationModelJS pmJS = clientDolphinJS.getAt(pmId);
		return pmJS == null ? null : new ClientPresentationModel(pmJS);
	}
	public ClientPresentationModel findPresentationModelById(String pmId) {
		ClientPresentationModelJS pm = clientDolphinJS.findPresentationModelById(pmId);
		return pm == null ? null : new ClientPresentationModel(pm);
	}

	public void deletePresentationModel(ClientPresentationModel pm) {
		clientDolphinJS.deletePresentationModel(pm.pmJS);
	}
	public void deleteAllPresentationModelsOfType(String pmType) {
		clientDolphinJS.deleteAllPresentationModelsOfType(pmType);
	}
	public void tag(ClientPresentationModel pm, String propertyName, Object value, String tag) {
		clientDolphinJS.tag(pm.pmJS, propertyName, value, tag);
	}
	public ClientAttribute attribute(String propertyName, String qualifier, Object value, String tag) {
		ClientAttributeJS attributeJS = clientDolphinJS.attribute(propertyName, qualifier, value, tag);
		return new ClientAttribute(attributeJS);
	}

	public ClientAttribute findAttributeById(String id) {
		return getClientModelStore().findAttributeById(id);
	}

	public void addAttributeToModel(ClientPresentationModel pm, ClientAttribute attribute) {
		clientDolphinJS.addAttributeToModel(pm.pmJS, attribute.getClientAttributeJS());
	}

	public void addModelStoreListener(ModelStoreChangeHandler handler) {
		getClientModelStore().addModelStoreListener(handler);
	}

	public void startPushListening(String pushActionName, String releaseActionName) {
		clientDolphinJS.startPushListening(pushActionName, releaseActionName);
	}
	public void stopPushListening() {
		clientDolphinJS.stopPushListening();
	}

	// --- private routines ---

	private List<ClientPresentationModel> pmJSArrayAsPMList(ClientPresentationModelJS[] pms) {
		List<ClientPresentationModel> result = new ArrayList<ClientPresentationModel>(pms.length);
		for (ClientPresentationModelJS pm : pms) {
			result.add(new ClientPresentationModel(pm));
		}
		return result;
	}

	private static class OnFinishedHandlerAdapter implements OnFinishedHandlerJS {
		private final OnFinishedHandler handler;

		public OnFinishedHandlerAdapter(OnFinishedHandler handler) {
			this.handler = handler;
		}

		@Override
		public void handlePresentationModels(final JsArray<ClientPresentationModelJS> jsPMs) {
			List<ClientPresentationModel> pms = new ArrayList<ClientPresentationModel>(jsPMs.length());
			for (int i = 0; i < jsPMs.length(); i++) {
				pms.add(new ClientPresentationModel(jsPMs.get(i)));
			}

			handler.handlePresentationModels(pms);
		}
	}
}
