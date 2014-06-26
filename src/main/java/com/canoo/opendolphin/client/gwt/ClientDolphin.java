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

    public PresentationModel presentationModel(String id, String type, String... clientAttributePropertyNames) {

		List<ClientAttribute> clientAttributes = new ArrayList<ClientAttribute>();
		for (String propertyName : clientAttributePropertyNames) {
			ClientAttribute clientAttribute = this.attribute(propertyName, null, null);
			clientAttributes.add(clientAttribute);
		}

        JsArray jsAttributes = ClientAttributeJS.createArray().cast();
		for (ClientAttribute clientAttribute : clientAttributes) {
            jsAttributes.push(clientAttribute.getClientAttributeJS());
		}

		PresentationModelJS presentationModelJS = clientDolphinJS.presentationModel(id, type, jsAttributes);
		return new PresentationModel(presentationModelJS);
    }

    public ClientModelStore getClientModelStore() {
        return new ClientModelStore(clientDolphinJS.getClientModelStore());
    }


	public List<String> listPresentationModelIds() {
		String[] pmIds = clientDolphinJS.listPresentationModelIds();
		return Arrays.asList(pmIds);
	}

	public List<PresentationModel> listPresentationModels() {
		PresentationModelJS[] pms = clientDolphinJS.listPresentationModels();
		return pmJSArrayAsPMList(pms);
	}
	public List<PresentationModel> findAllPresentationModelByType(String pmType) {
		PresentationModelJS[] pms = clientDolphinJS.findAllPresentationModelByType(pmType);
		return pmJSArrayAsPMList(pms);
	}

	public PresentationModel getAt(String pmId) {
		PresentationModelJS pmJS = clientDolphinJS.getAt(pmId);
		return pmJS == null ? null : new PresentationModel(pmJS);
	}
	public PresentationModel findPresentationModelById(String pmId) {
		PresentationModelJS pm = clientDolphinJS.findPresentationModelById(pmId);
		return pm == null ? null : new PresentationModel(pm);
	}

	public void deletePresentationModel(PresentationModel pm) {
		clientDolphinJS.deletePresentationModel(pm.pmJS);
	}
	public void deleteAllPresentationModelsOfType(String pmType) {
		clientDolphinJS.deleteAllPresentationModelsOfType(pmType);
	}
	public void tag(PresentationModel pm, String propertyName, Object value, String tag) {
		clientDolphinJS.tag(pm.pmJS, propertyName, value, tag);
	}
	public ClientAttribute attribute(String propertyName, String qualifier, Object value, String tag) {
		ClientAttributeJS attributeJS = clientDolphinJS.attribute(propertyName, qualifier, value, tag);
		return new ClientAttribute(attributeJS);
	}

	// --- private routines ---

	private List<PresentationModel> pmJSArrayAsPMList(PresentationModelJS[] pms) {
		List<PresentationModel> result = new ArrayList<PresentationModel>(pms.length);
		for (PresentationModelJS pm : pms) {
			result.add(new PresentationModel(pm));
		}
		return result;
	}

	private static class OnFinishedHandlerAdapter implements OnFinishedHandlerJS {
		private final OnFinishedHandler handler;

		public OnFinishedHandlerAdapter(OnFinishedHandler handler) {
			this.handler = handler;
		}

		@Override
		public void handlePresentationModels(final JsArray<PresentationModelJS> jsPMs) {
			List<PresentationModel> pms = new ArrayList<PresentationModel>(jsPMs.length());
			for (int i = 0; i < jsPMs.length(); i++) {
				pms.add(new PresentationModel(jsPMs.get(i)));
			}

			handler.handlePresentationModels(pms);
		}
	}
}
